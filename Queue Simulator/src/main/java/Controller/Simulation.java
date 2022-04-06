

import javax.swing.*;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Simulation extends Client implements Runnable {
    private int timeLimit;
    private int maxProcessingTime;
    private int minProcessingTime;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int numberQueues;
    private int numberClients;
    private float averageServiceTime = 0;
    private float averageWaitingTime = 0;
    private int totalWaiting = 0;
    private int totalService = 0;
    private int peakHour = 0;
    private SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    private Processing schedule;
    private ArrayList<Client> randomClients = new ArrayList<>();
    private String input;
    private String output;
    private int currentTime = 0;
    private int WaitingTime;
    View v;

    public Simulation() {

    }

    public Simulation(int timeLimit, int maxProcessingTime, int minProcessingTime, int maxArrivalTime, int minArrivalTime, int numberQueues, int numberClients) {
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.numberQueues = numberQueues;
        this.numberClients = numberClients;
        this.schedule = new Processing(numberQueues, numberClients, SelectionPolicy.SHORTEST_TIME);

    }

    public void setPeakHour(int peakHour) {
        this.peakHour = peakHour;
    }

    public int getPeakHour() {
        return peakHour;
    }

    public ArrayList<Client> generateRandom() {
        Random r = new Random();
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 1; i <= numberClients; i++) {
            int arrivalTime = r.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
            int proccesingTime = r.nextInt(maxProcessingTime - minProcessingTime) + minProcessingTime;
            this.setAverageServiceTime(this.getAverageServiceTime() + proccesingTime);
            Client client = new Client(i, arrivalTime, proccesingTime);
            this.randomClients.add(client);
        }
        this.setAverageServiceTime(this.getAverageServiceTime() / this.getNumberClients());
        Collections.sort(randomClients);
        return this.randomClients;
    }

    public void read(String path) throws FileNotFoundException {
        File file_read = new File(path);
        Scanner s = new Scanner(file_read);
        this.numberClients = s.nextInt();
        this.numberQueues = s.nextInt();
        this.timeLimit = s.nextInt();
        String aux = s.next();
        String[] rez = aux.split(",", 2);
        this.minArrivalTime = Integer.parseInt(rez[0]);
        this.maxArrivalTime = Integer.parseInt(rez[1]);
        aux = s.next();
        rez = aux.split(",", 2);
        this.minProcessingTime = Integer.parseInt(rez[0]);

        this.maxProcessingTime = Integer.parseInt(rez[1]);
        schedule = new Processing(numberQueues, numberClients, SelectionPolicy.SHORTEST_TIME);
    }

    private void WaitingTime() {
        Coada s = schedule.getCozi().get(0);
        for (Coada se : schedule.getCozi()) {
            if (se.getWaitingTime().intValue() > s.getWaitingTime().intValue() && s.getClients().size() < schedule.getNrClientsQueue()) {
                s = se;
            }
        }
        if (s.getClients().size() < schedule.getNrClientsQueue()) {
            WaitingTime += s.getWaitingTime().intValue();
        }
    }

    @Override
    public void run() {
        ArrayList<Client> client = new ArrayList<>();
        int currenClient = 0;
        int maxClinets = 0;
        try {
            FileWriter writer = new FileWriter(output);
            this.randomClients = generateRandom();
            while (currentTime <= timeLimit) {
                System.out.println("Time:" + currentTime);
                writer.write("Time: " + currentTime + '\n');
                v.setTextArea(v.getTextArea() + "Time: " + currentTime + '\n');
                if (!randomClients.isEmpty()) {
                    for (Client c : randomClients) {
                        if (c.getArrivalTime() == currentTime) {
                            WaitingTime();
                            schedule.addClientToQueue(c);
                            client.add(c);
                            this.setAverageWaitingTime(this.getAverageWaitingTime() + c.getArrivalTime() + c.getServiceTime());
                            currenClient++;

                        }
                    }
                }
                while (!client.isEmpty()) {
                    int h = 0;
                    randomClients.remove(client.get(h));
                    client.remove(client.get(h));
                    h++;
                }
                if (maxClinets < currenClient) {
                    maxClinets = currenClient;
                    this.setPeakHour(currenClient);
                }
                if (randomClients.isEmpty()) {
                    System.out.print("Waiting clients: " + "No one");
                    writer.append("Waiting clients: " + "No one");
                    v.setTextArea(v.getTextArea() + "Waiting clients: " + "No one");

                }
                if (!randomClients.isEmpty()) {
                    System.out.print("Waiting clients: ");
                    writer.append("Waiting clients: ");
                    v.setTextArea(v.getTextArea() + "Waiting clients: ");

                }
                if (!randomClients.isEmpty()) {
                    for (int i = 1; i <= randomClients.size(); i++) {
                        System.out.print("(" + randomClients.get(i - 1) + ")" + ";");
                        writer.append("(" + randomClients.get(i - 1) + ")" + ";");
                        v.setTextArea(v.getTextArea() + "(" + randomClients.get(i - 1) + ")" + ";");
                    }
                }
                System.out.print("\n");
                writer.append("\n");
                v.setTextArea(v.getTextArea() + "\n");
                int i = 1;
                for (Coada c : schedule.getCozi()) {
                    if (c.getClients().size() == 0) {
                        System.out.println("Queue " + i + ":" + " closed");
                        writer.append("Queue ").append(String.valueOf(i)).append(":").append(" closed" + "\n");
                        v.setTextArea(v.getTextArea() + "Queue " + String.valueOf(i) + ":" + " closed" + "\n");
                        i++;
                    }
                    if (c.getClients().size() > 0) {
                        if (schedule.getCozi().get(i - 1).getClients().element().getServiceTime() == 0) {
                            Client cl = schedule.getCozi().get(i - 1).getClients().element();
                            schedule.getCozi().get(i - 1).getClients().remove(cl);
                            System.out.println(c.toString());
                            writer.append(c.toString() + "\n");
                            v.setTextArea(v.getTextArea() + c.toString() + "\n");
                        } else {
                            System.out.println(c.toString());
                            writer.append(c.toString() + "\n");
                            v.setTextArea(v.getTextArea() + c.toString() + "\n");
                            schedule.getCozi().get(i - 1).getClients().element().setServiceTime(schedule.getCozi().get(i - 1).getClients().element().getServiceTime() - 1);
                        }

                        i++;
                    }

                }
                System.out.println("\n");
                writer.append("\n");
                v.setTextArea(v.getTextArea() + "\n");

                currentTime++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            schedule.stop();
            this.setAverageWaitingTime(this.getAverageWaitingTime() / this.getNumberClients());
            writer.append("Average service time: " + this.getAverageServiceTime() + "\n");
            writer.append("Average waiting time: " + this.getAverageWaitingTime() + "\n");
            writer.append("Peak hour: " + this.getPeakHour());
            v.setTextArea(v.getTextArea() + "Average service time: " + this.getAverageServiceTime() + "\n");
            v.setTextArea(v.getTextArea() + "Average waiting time: " + this.getAverageWaitingTime() + "\n");
            v.setTextArea(v.getTextArea() + "Peak hour: " + this.getPeakHour() + "\n");
            v.setTextArea(v.getTextArea() + " Simulation Finished :)");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getMaxProcessingTime() {
        return maxProcessingTime;
    }

    public void setMaxProcessingTime(int maxProcessingTime) {
        this.maxProcessingTime = maxProcessingTime;
    }

    public int getMinProcessingTime() {
        return minProcessingTime;
    }

    public void setMinProcessingTime(int minProcessingTime) {
        this.minProcessingTime = minProcessingTime;
    }

    public int getMinArrivalTime() {
        return minArrivalTime;
    }

    public void setMinArrivalTime(int minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public int getNumberClients() {
        return numberClients;
    }


    public SelectionPolicy getSelectionPolicy() {
        return selectionPolicy;
    }

    public void setSelectionPolicy(SelectionPolicy selectionPolicy) {
        this.selectionPolicy = selectionPolicy;
    }

    public Processing getSchedule() {
        return schedule;
    }

    public void setSchedule(Processing schedule) {
        this.schedule = schedule;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public void setMaxArrivalTime(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }

    public int getNumberQueues() {
        return numberQueues;
    }


    public float getAverageServiceTime() {
        return averageServiceTime;
    }

    public void setAverageServiceTime(float averageServiceTime) {
        this.averageServiceTime = averageServiceTime;
    }

    public float getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public void setAverageWaitingTime(float averageWaitingTime) {
        this.averageWaitingTime = averageWaitingTime;
    }

    public ArrayList<Client> getRandomClients() {
        return randomClients;
    }

    public void setRandomClients(ArrayList<Client> randomClients) {
        this.randomClients = randomClients;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public View getV() {
        return v;
    }

    public void setV(View v) {
        this.v = v;
    }
}
