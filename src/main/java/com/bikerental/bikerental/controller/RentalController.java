package com.bikerental.bikerental.controller;

import com.bikerental.bikerental.model.*;
import com.bikerental.bikerental.util.FileHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
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
        String start = LocalDate.now().toString();

        Rental rental = rentalType.equals("hourly")
                ? new HourlyRental(id, userId, bikeId, start, endDate, "ACTIVE")
                : new DailyRental(id, userId, bikeId, start, endDate, "ACTIVE");

        FileHandler.writeRental(rental.toFileString());
        return "redirect:/rental/view";
    }

    // READ
    @GetMapping("/view")
    public String viewRentals(Model model) {
        model.addAttribute("rentals", FileHandler.readAllRentals());
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
            if (r[0].equals(rentalId)) { r[4] = endDate; r[5] = status; }
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