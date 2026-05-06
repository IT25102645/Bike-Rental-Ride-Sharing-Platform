
import Model.Bike;
import Model.ElectricBike;
import Model.ManualBike;
import Service.BikeService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BikeService bikeService = new BikeService();

        List<Bike> bikes = bikeService.loadBikes();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n===== Component 02: Bike Management System =====");
                System.out.println("1. Add Electric Bike");
                System.out.println("2. Add Manual Bike");
                System.out.println("3. View All Bikes");
                System.out.println("4. Search Bike by ID");
                System.out.println("5. Delete Bike");
                System.out.println("6. Update Bike Price");
                System.out.println("7. Save and Exit");
                System.out.print("Select an option: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("[ERROR] Invalid Option! Please enter a number (1-7).");
                    scanner.nextLine();
                    continue;
                }

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1: // Create: Electric Bike
                        System.out.println("\n--- Adding Electric Bike ---");
                        String eId;
                        while (true) {
                            System.out.print("Enter ID: ");
                            eId = scanner.nextLine();

                            if (bikeService.isIdExists(eId)) {
                                System.out.println("[ERROR] This ID already exists! Please enter a unique ID.");
                            } else {
                                break;
                            }
                        }

                        System.out.print("Enter Model: "); String eModel = scanner.nextLine();
                        double ePrice;
                        while (true) {
                            System.out.print("Price/hr: ");
                            if (scanner.hasNextDouble()) {
                                ePrice = scanner.nextDouble();
                                if (ePrice > 0) {
                                    break;
                                } else {
                                    System.out.println("[ERROR] Price must be a positive value!");
                                }
                            } else {
                                System.out.println("[ERROR] Invalid input! Please enter a number for the price.");
                                scanner.next();
                            }
                        }
                        System.out.print("Battery Capacity (Ah/Wh): "); int batt = scanner.nextInt();

                        bikes.add(new ElectricBike(eId, eModel, ePrice, true, batt));
                        System.out.println("Electric Bike added successfully!");
                        break;

                    case 2: // Create: Manual Bike
                        System.out.println("\n--- Adding Manual Bike ---");
                        String mId;
                        while (true) {
                            System.out.print("Enter ID: ");
                            mId = scanner.nextLine();

                            if (bikeService.isIdExists(mId)) {
                                System.out.println("[ERROR] This ID already exists! Please enter a unique ID.");
                            } else {
                                break;
                            }
                        }

                        System.out.print("Enter Model: "); String mModel = scanner.nextLine();
                        double mPrice;
                        while (true) {
                            System.out.print("Price/hr: ");
                            if (scanner.hasNextDouble()) {
                                mPrice = scanner.nextDouble();
                                if (mPrice > 0) {
                                    break;
                                } else {
                                    System.out.println("[ERROR] Price must be a positive value!");
                                }
                            } else {
                                System.out.println("[ERROR] Invalid input! Please enter a number for the price.");
                                scanner.next();
                            }
                        }
                        System.out.print("Number of Gears: "); int gears = scanner.nextInt();

                        bikes.add(new ManualBike(mId, mModel, mPrice, true, gears));
                        System.out.println("Manual Bike added successfully!");
                        break;

                    case 3: // Read: View All with Battery/Gears
                        System.out.println("\n--- All Registered Bikes ---");
                        if (bikes.isEmpty()) {
                            System.out.println("No bikes found.");
                        } else {
                            for (Bike b : bikes) {
                                String type = (b instanceof ElectricBike) ? "Electric" : "Manual";
                                String extraInfo = "";

                                // Casting
                                if (b instanceof ElectricBike) {
                                    extraInfo = " | Battery: " + ((ElectricBike) b).getBatteryCapacity() + " Ah/Wh";
                                } else if (b instanceof ManualBike) {
                                    extraInfo = " | Gears: " + ((ManualBike) b).getNumberOfGears();
                                }

                                System.out.println("Type: [" + type + "] | ID: " + b.getBikeID() +
                                        " | Model: " + b.getModel() +
                                        " | Price: Rs." + b.getPricePerHour() + extraInfo);
                            }
                        }
                        break;

                    case 4: // Read: Search by ID
                        System.out.print("Enter ID to Search: ");
                        String sId = scanner.nextLine();
                        boolean sFound = false;
                        for (Bike b : bikes) {
                            if (b.getBikeID().equalsIgnoreCase(sId)) {
                                String type = (b instanceof ElectricBike) ? "Electric Bike" : "Manual Bike";
                                String extraInfo = (b instanceof ElectricBike) ?
                                        " | Battery: " + ((ElectricBike) b).getBatteryCapacity() + " Ah/Wh" :
                                        " | Gears: " + ((ManualBike) b).getNumberOfGears();

                                System.out.println("Found: [" + type + "] " + b.getModel() +
                                        " - Rs." + b.getPricePerHour() + "/hr" + extraInfo);
                                sFound = true;
                                break;
                            }
                        }
                        if (!sFound) System.out.println("Bike ID not found!");
                        break;

                    case 5: // Delete
                        System.out.print("Enter ID to Delete: ");
                        String dId = scanner.nextLine();
                        if (bikes.removeIf(b -> b.getBikeID().equalsIgnoreCase(dId))) {
                            System.out.println("Bike deleted successfully!");
                        } else {
                            System.out.println("Bike ID not found!");
                        }
                        break;

                    case 6: // Update
                        System.out.print("Enter ID to Update Price: ");
                        String uId = scanner.nextLine();
                        boolean uFound = false;
                        for (Bike b : bikes) {
                            if (b.getBikeID().equalsIgnoreCase(uId)) {
                                double newPrice;
                                while (true) {
                                    System.out.print("Enter New Price (Current: Rs." + b.getPricePerHour() + "): ");
                                    if (scanner.hasNextDouble()) {
                                        newPrice = scanner.nextDouble();
                                        if (newPrice > 0) {
                                            b.setPricePerHour(newPrice);
                                            System.out.println("Price updated successfully!");
                                            break;
                                        } else {
                                            System.out.println("[ERROR] Price must be a positive value!");
                                        }
                                    } else {
                                        System.out.println("[ERROR] Invalid input! Please enter a number.");
                                        scanner.next();
                                    }
                                }
                                uFound = true;
                                break;
                            }
                        }
                        if (!uFound) System.out.println("Bike ID not found!");
                        break;

                    case 7: // Save and Exit
                        bikeService.saveBikes(bikes);
                        System.out.println("Data saved to bikes.txt. Exiting... Goodbye!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice! Please select 1-7.");
                }
            } catch (Exception e) {
                System.out.println("\n[ERROR] Invalid input! Please enter data in the correct format.");
                scanner.nextLine();
            }
        }
    }
}