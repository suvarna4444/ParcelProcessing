package main;

public class Worker {
    private ParcelMap parcelMap;
    private Log log;

    public Worker(ParcelMap parcelMap) {
        this.parcelMap = parcelMap;
        this.log = Log.getInstance();
    }

    public void processCustomer(Customer customer) {
        Parcel parcel = parcelMap.getParcel(customer.getParcelID());
        if (parcel != null) {
            log.addLog("Processing customer: " + customer.getName() + " with Parcel ID: " + parcel.getParcelID());
         
        } else {
            log.addLog("Parcel not found for customer: " + customer.getName());
        }
    }
}
