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

    // 6. Update Price Page
    @GetMapping("/edit")
    public String showEditForm() {
        return "update-price";
    }

    // 7. Update Price Logic
    @PostMapping("/update-price")
    public String updatePrice(@RequestParam String id, @RequestParam double price) {
        List<Bike> bikes = bikeService.loadBikes();
        bikeService.updateBikePrice(bikes, id, price);
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
}
