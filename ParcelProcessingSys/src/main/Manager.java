package main;

public class Manager {
    private QueueOfCustomers customerQueue;
    private ParcelMap parcelMap;
    private Worker worker;
    private AppView appView;

    public Manager() {
        this.customerQueue = new QueueOfCustomers();
        this.parcelMap = new ParcelMap();
        this.worker = new Worker(parcelMap);
        this.appView = new AppView();
    }
    public void initializeParcels(String filename) {
        
    }
    public void initializeCustomers(String filename) {
    }

    public void startProcessing() {
        appView.setVisible(true);
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.initializeParcels("parcels.txt");
        manager.initializeCustomers("customers.txt");
        manager.startProcessing();
    }
}
