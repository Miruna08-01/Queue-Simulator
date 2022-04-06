import java.util.concurrent.*;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public class Coada extends Client implements  Runnable {


    private LinkedBlockingQueue<Client> clients;
    private AtomicInteger waitingTime;
    private boolean run = false;
    private int number;
    private ExecutorService exec = Executors.newFixedThreadPool(clients.size());

    public Coada(int number) {
        this.clients = new LinkedBlockingQueue<Client>();
        this.waitingTime = new AtomicInteger(0);
        this.number = number;
        run = true;
    }

    public void addClient(Client c) {
        clients.add(c);
        waitingTime.set(waitingTime.get() + c.getServiceTime());

    }

    public LinkedBlockingQueue<Client> getClients() {
        return clients;
    }

    public void setClients(LinkedBlockingQueue<Client> clients) {
        this.clients = clients;
    }

    public AtomicInteger getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(AtomicInteger qTime) {
        this.waitingTime = qTime;
    }

    @Override


    public String toString() {
        String s = "Queue " + number + ":";
        for (Client c : clients) {
            s = s + "(" + c.toString() + ");";
        }
        if (clients.isEmpty()) {
            return s = s + "closed";
        }
        return s;
    }

    public void stop() {
        run = false;
    }

    @Override
    public void run() {
            while (run == true) {
                if (!clients.isEmpty()) {
                    Client client = clients.peek();

                    AtomicInteger a = new AtomicInteger(client.getServiceTime());
                    try {
                        for (int i = 0; i < client.getServiceTime(); i++) {
                            Thread.sleep(1000);
                            this.waitingTime.getAndDecrement();
                            a.getAndDecrement();
                            client.setServiceTime(a.intValue());
                        }
                        if (this.waitingTime.intValue()==0)
                            clients.remove(client);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
    }
}

