package com.bikerental.bikerental.controller;

import com.bikerental.bikerental.model.*;
import com.bikerental.bikerental.util.FileHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
@RequestMapping("/rental")
public class RentalController {

    @GetMapping("/create")
    public String showCreateForm() {
        return "rentals/rent-bike";
    }

    @PostMapping("/create")
    public String createRental(@RequestParam String userId,
                               @RequestParam String bikeId,
                               @RequestParam String rentalType,
                               @RequestParam String endDate,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        if (!FileHandler.isBikeAvailable(bikeId)) {
            model.addAttribute("error", "Bike " + bikeId + " is not available!");
            return "rentals/rent-bike";
        }

        String id = "R-" + UUID.randomUUID().toString().substring(0, 6);
        String startDate = LocalDate.now().toString();

        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.parse(endDate);
        int duration = (int) ChronoUnit.DAYS.between(start, end);
        if (duration <= 0) duration = 1;

        Rental rental;
        if (rentalType.equals("hourly")) {
            rental = new HourlyRental(id, userId, bikeId, startDate, endDate, "ACTIVE");
        } else {
            rental = new DailyRental(id, userId, bikeId, startDate, endDate, "ACTIVE");
        }

        double fee = rental.calculateFee(duration);
        rental.setTotalFee(fee);

        FileHandler.writeRental(rental.toFileString());

        redirectAttributes.addFlashAttribute("success",
                "Rental created! Bike " + bikeId + " rented for LKR " + fee);
        return "redirect:/rental/view";
    }

    @GetMapping("/view")
    public String viewRentals(Model model) {
        model.addAttribute("rentals", FileHandler.readAllRentals());
        return "rentals/rental-history";
    }

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

            if (filtered.isEmpty()) {
                model.addAttribute("info", "No rentals found for User ID: " + userId);
            }
        } else {
            model.addAttribute("rentals", allRentals);
        }

        return "rentals/rental-history";
    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam String rentalId, Model model) {
        model.addAttribute("rentalId", rentalId);
        return "rentals/return-bike";
    }

    @PostMapping("/update")
    public String updateRental(@RequestParam String rentalId,
                               @RequestParam String status,
                               @RequestParam String endDate,
                               RedirectAttributes redirectAttributes) {
        List<String[]> rentals = FileHandler.readAllRentals();
        for (String[] r : rentals) {
            if (r[0].equals(rentalId)) {
                r[4] = endDate;
                r[5] = status;
            }
        }
        FileHandler.rewriteFile(rentals);

        redirectAttributes.addFlashAttribute("success",
                "Rental " + rentalId + " updated to " + status + " successfully!");
        return "redirect:/rental/view";
    }

    @PostMapping("/delete")
    public String deleteRental(@RequestParam String rentalId,
                               RedirectAttributes redirectAttributes) {
        List<String[]> rentals = FileHandler.readAllRentals();
        rentals.removeIf(r -> r[0].equals(rentalId));
        FileHandler.rewriteFile(rentals);

        redirectAttributes.addFlashAttribute("success",
                "Rental " + rentalId + " deleted successfully!");
        return "redirect:/rental/view";
    }
}