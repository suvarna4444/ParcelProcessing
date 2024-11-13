package main;

import java.util.HashMap;
import java.util.Map;

public class ParcelMap {
    private Map<String, Parcel> parcels;

    // Constructor
    public ParcelMap() {
        this.parcels = new HashMap<>();
    }

    // Method to add a parcel to the map
    public void addParcel(Parcel parcel) {
        parcels.put(parcel.getParcelID(), parcel);
    }

    // Method to get a parcel by its ID
    public Parcel getParcel(String parcelID) {
        return parcels.get(parcelID);
    }

    // Method to remove a parcel by its ID
    public Parcel removeParcel(String parcelID) {
        return parcels.remove(parcelID);
    }

    // Method to check if a parcel exists by its ID
    public boolean containsParcel(String parcelID) {
        return parcels.containsKey(parcelID);
    }

    // Method to get the number of parcels in the map
    public int getParcelCount() {
        return parcels.size();
    }

    // Method to represent the map of parcels as a string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Parcel parcel : parcels.values()) {
            sb.append(parcel).append("\n");
        }
        return sb.toString();
    }
}
