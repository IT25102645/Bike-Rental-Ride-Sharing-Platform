package com.bikerental.bikerental.util;

import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String FILE_PATH = "data/rentals.txt";

    // READ: Load all rentals from file safely
    public static List<String[]> readAllRentals() {
        List<String[]> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length == 7) { // only add complete records
                        list.add(parts);
                    }
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    // CREATE: Append a new rental to file
    public static void writeRental(String data) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(FILE_PATH, true))) { // true = append mode
            bw.write(data);
            bw.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    // UPDATE/DELETE: Rewrite entire file with modified list
    public static void rewriteFile(List<String[]> rentals) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(FILE_PATH, false))) { // false = overwrite mode
            for (String[] r : rentals) {
                bw.write(String.join(",", r));
                bw.newLine();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    // CHECK: Returns true if bike has no ACTIVE rental
    public static boolean isBikeAvailable(String bikeId) {
        for (String[] r : readAllRentals()) {
            if (r[2].equals(bikeId) && r[5].equals("ACTIVE")) return false;
        }
        return true;
    }
}