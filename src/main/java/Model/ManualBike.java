package Model;

    public class ManualBike extends Bike {
        private int numberOfGears;

        public ManualBike(String bikeID, String model, double pricePerHour, boolean isAvailable, int numberOfGears) {
            super(bikeID, model, pricePerHour, isAvailable);
            this.numberOfGears = numberOfGears;
        }

        public double calculateRentalCost(int hours) {
            return getPricePerHour() * hours;
        }

        public int getNumberOfGears() { return numberOfGears; }
        public void setNumberOfGears(int numberOfGears) { this.numberOfGears = numberOfGears; }
    }
