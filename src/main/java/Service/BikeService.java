package Service;

import Model.Bike;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

        //(Persistence)
        public void saveBikes(List<Bike> bikeList) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
                oos.writeObject(bikeList);
                System.out.println("Data saved successfully to " + FILE_NAME);
            } catch (IOException e) {
                System.err.println("Error saving data: " + e.getMessage());
            }
        }

        //(Load)
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

        public boolean updateBikePrice(List<Bike> bikes, String id, double newPrice) {
            if (!isValidPrice(newPrice)) {
                return false;
            }

            for (Bike b : bikes) {
                if (b.getBikeID().equalsIgnoreCase(id)) {
                    b.setPricePerHour(newPrice);
                    saveBikes(bikes);
                    return true;
                }
            }
            System.out.println("Bike ID not found!");
            return false;
        }
    }

