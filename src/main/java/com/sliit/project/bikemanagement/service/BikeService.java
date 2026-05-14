package com.sliit.project.bikemanagement.service;

import com.sliit.project.bikemanagement.model.Bike;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class BikeService {
    private final String FILE_NAME = "bikes.txt";

    public boolean isIdExists(String id) {
        List<Bike> bikeList = loadBikes();
        for (Bike bike : bikeList) {
            if (bike.getBikeID().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public void saveBikes(List<Bike> bikeList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(bikeList);
            System.out.println("Data saved successfully to " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public List<Bike> loadBikes() {
        List<Bike> bikeList = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return bikeList;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            bikeList = (List<Bike>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
        return bikeList;
    }

    public boolean isValidPrice(double price) {
        if (price <= 0) {
            System.out.println("[ERROR] Price must be a positive value!");
            return false;
        }
        return true;
    }


    public boolean updateBikeDetails(List<Bike> bikes, String id, String newModel, double newPrice, boolean availability) {
        if (!isValidPrice(newPrice)) {
            return false;
        }
        for (Bike b : bikes) {
            if (b.getBikeID().equalsIgnoreCase(id)) {
                b.setModel(newModel);
                b.setPricePerHour(newPrice);
                b.setAvailable(availability);
                saveBikes(bikes);
                return true;
            }
        }
        return false;
    }
}