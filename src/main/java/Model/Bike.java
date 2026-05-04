package Model;

import java.io.Serializable;

    public class Bike implements Serializable {
        private String bikeID;
        private String model;
        private double pricePerHour;
        private boolean isAvailable;

        // Constructor
        public Bike(String bikeID, String model, double pricePerHour, boolean isAvailable) {
            this.bikeID = bikeID;
            this.model = model;
            this.pricePerHour = pricePerHour;
            this.isAvailable = isAvailable;
        }

        public double calculateRentalCost(int hours) {
            return pricePerHour * hours;
        }

        // Getters and Setters (Encapsulation)
        public String getBikeID() { return bikeID; }
        public void setBikeID(String bikeID) { this.bikeID = bikeID; }

        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }

        public double getPricePerHour() { return pricePerHour; }
        public void setPricePerHour(double pricePerHour) { this.pricePerHour = pricePerHour; }

        public boolean isAvailable() { return isAvailable; }
        public void setAvailable(boolean available) { isAvailable = available; }
    }

