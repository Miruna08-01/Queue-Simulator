public class Client implements Comparable {
    private int id;
    private int arrivalTime;
    private int serviceTime;

    public Client(int id, int arrivalTime, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return (String.valueOf(id) + " ," + String.valueOf(arrivalTime) + " ," + String.valueOf(serviceTime));
    }


    @Override
    public int compareTo(Object o) {
        Client c = (Client) o;
        if (this.arrivalTime < ((Client) o).arrivalTime)
            return -1;
        else if (this.arrivalTime == ((Client) o).arrivalTime)
            return 0;
        else
            return 1;
    }
}
