package Model;

    public class ElectricBike extends Bike {
        private int batteryCapacity;

        public ElectricBike(String bikeID, String model, double pricePerHour, boolean isAvailable, int batteryCapacity) {
            super(bikeID, model, pricePerHour, isAvailable);
            this.batteryCapacity = batteryCapacity;
        }

        // Method Overriding
        public double calculateRentalCost(int hours) {
            return (getPricePerHour() * hours) + 50.0;
        }

        public int getBatteryCapacity() { return batteryCapacity; }
        public void setBatteryCapacity(int batteryCapacity) { this.batteryCapacity = batteryCapacity; }
    }

