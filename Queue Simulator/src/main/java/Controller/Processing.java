import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Processing extends Coada {
    private ArrayList<Coada> cozi;
    private int nrCozi;
    private int nrClientsQueue;
    private Strategy strategy;
    private static ExecutorService executor = Executors.newFixedThreadPool(25);
    public Processing(int nrCozi, int nrClientsQueue, SelectionPolicy policy) {
        this.cozi = new ArrayList<Coada>();
        this.nrCozi = nrCozi;
        this.nrClientsQueue = nrClientsQueue;
        this.changeStrategy(policy);
        for (int i = 1; i <= nrCozi; i++) {
            Coada coada = new Coada(i);
            cozi.add(coada);
            executor.execute(coada);
        }
        executor.shutdown();
    }

    public int getNrClientsQueue() {
        return nrClientsQueue;
    }

    public void setNrClientsQueue(int nrClientsQueue) {
        this.nrClientsQueue = nrClientsQueue;
    }

    public ArrayList<Coada> getCozi() {
        return cozi;
    }

    public void setCozi(ArrayList<Coada> cozi) {
        this.cozi = cozi;
    }

    public int getNrCozi() {
        return nrCozi;
    }

    public void setNrCozi(int nrCozi) {
        this.nrCozi = nrCozi;
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ConcreteStrategyQueue();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
        }
    }

    public void addClientToQueue(Client client) {
        strategy.addClient(this.cozi, client);
    }

    public String toString() {
        String s = "";
        for (Coada c : cozi) {
            if (c.getClients().isEmpty())
                s = "closed";
            s = s + c.toString();
        }
        return s;
    }

    public void stop() {
        for (Coada coada : cozi) {
            coada.stop();
        }
    }
}
