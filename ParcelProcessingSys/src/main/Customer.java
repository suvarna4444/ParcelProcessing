package main;

public class Customer {
    // Customer's position in the queue
    private int queueNumber;
    // Customer's name
    private String name;
    // ID of the parcel associated with the customer
    private String parcelID;

    // Constructor to initialize the customer details
    public Customer(int queueNumber, String name, String parcelID) {
        this.queueNumber = queueNumber;
        this.name = name;
        this.parcelID = parcelID;
    }

    // Returns the customer's queue number
    public int getQueueNumber() {
        return queueNumber;
    }

    // Returns the customer's name
    public String getName() {
        return name;
    }

    // Returns the ID of the customer's parcel
    public String getParcelID() {
        return parcelID;
    }

    // Returns a string representation of the customer
    @Override
    public String toString() {
        return "Queue Number: " + queueNumber + ", Name: " + name + ", Parcel ID: " + parcelID;
    }
}
