package main;

public class Worker {
    private ParcelMap parcelMap;
    private Log log;

    // Constructor
    public Worker(ParcelMap parcelMap) {
        this.parcelMap = parcelMap;
        this.log = Log.getInstance();
    }

    // Method to process a customer and release their parcel
    public void processCustomer(Customer customer) {
        String parcelID = customer.getParcelID();
        Parcel parcel = parcelMap.getParcel(parcelID);

        if (parcel != null && !parcel.isCollected()) {
            double fee = calculateFee(parcel);
            parcel.setCollected(true);
            log.addEvent("Customer " + customer.getName() + " collected parcel " + parcelID + ". Fee: $" + fee);
        } else {
            log.addEvent("Parcel " + parcelID + " not found or already collected for customer " + customer.getName());
        }
    }

    // Method to calculate the fee for a parcel
    public double calculateFee(Parcel parcel) {
        double baseRate = 5.0;
        double weightFee = parcel.getWeight() * 0.1;
        double sizeFee = parcel.calculateVolume() * 0.05;
        double daysFee = parcel.getDaysInDepot() * 0.2;

        return baseRate + weightFee + sizeFee + daysFee;
    }

    // Method to represent the worker as a string
    @Override
    public String toString() {
        return "Worker processing parcels";
    }
}

