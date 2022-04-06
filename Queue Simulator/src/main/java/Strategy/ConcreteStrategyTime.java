import java.util.List;

public class ConcreteStrategyTime implements Strategy {


    @Override
    public void addClient(List<Coada> cozi, Client c) {
        int min = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        for (Coada q : cozi) {
            if (min > cozi.get(i).getWaitingTime().intValue()) {
                min = cozi.get(i).getWaitingTime().get();
                j = i;
            }
            i++;
        }
        cozi.get(j).addClient(c);


    }
}
