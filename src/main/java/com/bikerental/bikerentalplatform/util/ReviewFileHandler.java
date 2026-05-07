package com.bikerental.bikerentalplatform.util;

import com.bikerental.bikerentalplatform.model.*;
import java.io.*;
import java.util.*;

public class ReviewFileHandler {
    private static final String FILE_PATH = "reviews.txt";

    public static List<Review> readAll() throws IOException {
        List<Review> reviews = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return reviews;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split("\\|");
            if (parts[0].equals("BIKE") && parts.length >= 9) {
                reviews.add(new BikeReview(parts[1], parts[2], parts[3],
                        Integer.parseInt(parts[4]), parts[5], parts[6], parts[7], parts[8]));
            } else if (parts[0].equals("RIDE") && parts.length >= 9) {
                reviews.add(new RideReview(parts[1], parts[2], parts[3],
                        Integer.parseInt(parts[4]), parts[5], parts[6], parts[7], parts[8]));
            }
        }
        br.close();
        return reviews;
    }

    public static void writeAll(List<Review> reviews) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false));
        for (Review r : reviews) {
            bw.write(r.toFileString());
            bw.newLine();
        }
        bw.close();
    }

    public static void append(Review review) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
        bw.write(review.toFileString());
        bw.newLine();
        bw.close();
    }
}
