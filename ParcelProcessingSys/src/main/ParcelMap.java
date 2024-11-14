package main;

import java.util.HashMap;
import java.util.Map;

public class ParcelMap {
    // Map to store parcels with their parcelID as the key
    private Map<String, Parcel> parcels;

    // Constructor initializes the parcel map
    public ParcelMap() {
        this.parcels = new HashMap<>();
    }

    // Adds a parcel to the map
    public void addParcel(Parcel parcel) {
        parcels.put(parcel.getParcelID(), parcel);
    }

    // Retrieves a parcel by its ID
    public Parcel getParcel(String parcelID) {
        return parcels.get(parcelID);
    }

    // Removes and returns a parcel by its ID
    public Parcel removeParcel(String parcelID) {
        return parcels.remove(parcelID);
    }

    // Checks if a parcel exists in the map by its ID
    public boolean containsParcel(String parcelID) {
        return parcels.containsKey(parcelID);
    }

    // Returns the number of parcels in the map
    public int getParcelCount() {
        return parcels.size();
    }

    // Returns a string representation of the parcels in the map
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Parcel parcel : parcels.values()) {
            sb.append(parcel).append("\n");
        }
        return sb.toString();
    }
}
