package com.sliit.project.bikemanagement.controller;

import com.sliit.project.bikemanagement.model.Bike;
import com.sliit.project.bikemanagement.model.ElectricBike;
import com.sliit.project.bikemanagement.model.ManualBike;
import com.sliit.project.bikemanagement.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BikeController {

    @Autowired
    private BikeService bikeService;

    // 1. Home Page (index.html)
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // 2. View All Bikes
    @GetMapping("/bikes")
    public String listBikes(Model model) {
        model.addAttribute("bikes", bikeService.loadBikes());
        return "bike-list";
    }

    // 3. Add Bike Page
    @GetMapping("/add-bike")
    public String showAddForm() {
        return "add-bike";
    }

    // 4. Save Bike Logic
    @PostMapping("/save-bike")
    public String saveBike(@RequestParam String bikeID,
                           @RequestParam String model,
                           @RequestParam double pricePerHour,
                           @RequestParam String type,
                           @RequestParam int extraInfo) {

        List<Bike> bikes = bikeService.loadBikes();

        if (bikeService.isIdExists(bikeID)) {
            return "redirect:/add-bike?error=ID already exists";
        }

        Bike newBike;
        if ("electric".equalsIgnoreCase(type)) {
            newBike = new ElectricBike(bikeID, model, pricePerHour, true, extraInfo);
        } else {
            newBike = new ManualBike(bikeID, model, pricePerHour, true, extraInfo);
        }

        bikes.add(newBike);
        bikeService.saveBikes(bikes);
        return "redirect:/bikes";
    }

    // 5. Search Page
    @GetMapping("/search")
    public String showSearchPage() {
        return "search-bike";
    }

    // 6. Update Bike Page 
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        List<Bike> bikes = bikeService.loadBikes();
        Bike foundBike = bikes.stream()
                .filter(b -> b.getBikeID().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);

        if (foundBike != null) {
            model.addAttribute("bike", foundBike);
            return "update-bike";
        }
        return "redirect:/bikes?error=BikeNotFound";
    }

    // 7. Update Bike Logic
    @PostMapping("/update-bike")
    public String updateBike(@RequestParam String id,
                             @RequestParam String model,
                             @RequestParam double price,
                             @RequestParam boolean isAvailable) {
        List<Bike> bikes = bikeService.loadBikes();
        bikeService.updateBikeDetails(bikes, id, model, price, isAvailable);
        return "redirect:/bikes";
    }

    // 8. Delete Bike
    @GetMapping("/delete/{id}")
    public String deleteBike(@PathVariable String id) {
        List<Bike> bikes = bikeService.loadBikes();
        bikes.removeIf(b -> b.getBikeID().equalsIgnoreCase(id));
        bikeService.saveBikes(bikes);
        return "redirect:/bikes";
    }
    // 9. Search Results Logic
    @GetMapping("/search-results")
    public String searchBike(@RequestParam String id, Model model) {
        List<Bike> bikes = bikeService.loadBikes();

        Bike foundBike = null;
        for (Bike b : bikes) {
            if (b.getBikeID().equalsIgnoreCase(id)) {
                foundBike = b;
                break;
            }
        }

        if (foundBike != null) {

            model.addAttribute("bikes", List.of(foundBike));
            return "bike-list";
        } else {

            return "redirect:/search?error=Bike ID Not Found";
        }
    }
}