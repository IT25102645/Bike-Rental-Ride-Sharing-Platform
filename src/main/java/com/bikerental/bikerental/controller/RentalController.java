package com.bikerental.bikerental.controller;

import com.bikerental.bikerental.model.*;
import com.bikerental.bikerental.util.FileHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
@RequestMapping("/rental")
public class RentalController {

    // SHOW create form
    @GetMapping("/create")
    public String showCreateForm() {
        return "rentals/rent-bike";
    }

    // CREATE
    @PostMapping("/create")
    public String createRental(@RequestParam String userId,
                               @RequestParam String bikeId,
                               @RequestParam String rentalType,
                               @RequestParam String endDate,
                               Model model) {

        if (!FileHandler.isBikeAvailable(bikeId)) {
            model.addAttribute("error", "Bike is not available!");
            return "rentals/rent-bike";
        }

        String id = "R-" + UUID.randomUUID().toString().substring(0, 6);
        String startDate = LocalDate.now().toString();

        // Calculate duration in days between start and end
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.parse(endDate);
        int duration = (int) ChronoUnit.DAYS.between(start, end);
        if (duration <= 0) duration = 1; // minimum 1

        // Polymorphism: correct subclass chosen based on rental type
        Rental rental;
        if (rentalType.equals("hourly")) {
            rental = new HourlyRental(id, userId, bikeId, startDate, endDate, "ACTIVE");
        } else {
            rental = new DailyRental(id, userId, bikeId, startDate, endDate, "ACTIVE");
        }

        // Calculate fee using polymorphic method and save it
        double fee = rental.calculateFee(duration);
        rental.setTotalFee(fee);

        FileHandler.writeRental(rental.toFileString());
        return "redirect:/rental/view";
    }

    // READ - View all rentals
    @GetMapping("/view")
    public String viewRentals(Model model) {
        model.addAttribute("rentals", FileHandler.readAllRentals());
        return "rentals/rental-history";
    }

    // READ - Search rentals by User ID
    @GetMapping("/search")
    public String searchRentals(@RequestParam(required = false) String userId,
                                Model model) {
        List<String[]> allRentals = FileHandler.readAllRentals();

        if (userId != null && !userId.trim().isEmpty()) {
            List<String[]> filtered = new ArrayList<>();
            for (String[] r : allRentals) {
                if (r[1].equalsIgnoreCase(userId.trim())) {
                    filtered.add(r);
                }
            }
            model.addAttribute("rentals", filtered);
            model.addAttribute("searchId", userId);
        } else {
            model.addAttribute("rentals", allRentals);
        }

        return "rentals/rental-history";
    }

    // SHOW update form
    @GetMapping("/update")
    public String showUpdateForm(@RequestParam String rentalId, Model model) {
        model.addAttribute("rentalId", rentalId);
        return "rentals/return-bike";
    }

    // UPDATE
    @PostMapping("/update")
    public String updateRental(@RequestParam String rentalId,
                               @RequestParam String status,
                               @RequestParam String endDate) {
        List<String[]> rentals = FileHandler.readAllRentals();
        for (String[] r : rentals) {
            if (r[0].equals(rentalId)) {
                r[4] = endDate;
                r[5] = status;
            }
        }
        FileHandler.rewriteFile(rentals);
        return "redirect:/rental/view";
    }

    // DELETE
    @PostMapping("/delete")
    public String deleteRental(@RequestParam String rentalId) {
        List<String[]> rentals = FileHandler.readAllRentals();
        rentals.removeIf(r -> r[0].equals(rentalId));
        FileHandler.rewriteFile(rentals);
        return "redirect:/rental/view";
    }
}