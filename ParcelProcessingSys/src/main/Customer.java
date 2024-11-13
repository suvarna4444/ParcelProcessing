package main;

public class Customer {
    private int queueNumber;
    private String name;
    private String parcelID;

    // Constructor
    public Customer(int queueNumber, String name, String parcelID) {
        this.queueNumber = queueNumber;
        this.name = name;
        this.parcelID = parcelID;
    }

    // Getters and Setters
    public int getQueueNumber() {
        return queueNumber;
    }

    public String getName() {
        return name;
    }

    public String getParcelID() {
        return parcelID;
    }

    // Method to represent the customer as a string
    @Override
    public String toString() {
        return "Queue Number: " + queueNumber + ", Name: " + name + ", Parcel ID: " + parcelID;
    }
}
