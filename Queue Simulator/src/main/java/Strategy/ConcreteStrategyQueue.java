import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addClient(List<Coada> cozi, Client c) {
        int min = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        for (Coada q : cozi) {
            if (min > cozi.get(i).getClients().size()) {
                min = cozi.get(i).getClients().size();
                j = i;
            }
            i++;
        }
        cozi.get(j).addClient(c);
    }
}
