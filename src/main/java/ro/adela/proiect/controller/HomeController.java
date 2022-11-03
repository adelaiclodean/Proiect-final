package ro.adela.proiect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.adela.proiect.model.Restaurant;
import ro.adela.proiect.model.RestaurantType;
import ro.adela.proiect.repo.JpaRestaurantRepository;
import ro.adela.proiect.repo.JpaRestaurantTypeRepository;

import java.util.List;

@Controller

public class HomeController {

    @Autowired
    public JpaRestaurantRepository jpaRestaurantRepository;

    @Autowired
    public JpaRestaurantTypeRepository jpaRestaurantTypeRepository;

    @GetMapping("/")
    public String homePage (Model model){
        List<Restaurant> restaurants= jpaRestaurantRepository.findAll();
        model.addAttribute("restaurants", restaurants);

        List<RestaurantType> restaurantTypes = jpaRestaurantTypeRepository.findAll();
        model.addAttribute("restaurantTypes", restaurantTypes);

        model.addAttribute("selectedRestaurantType", "All");
        return "home";
    }


    @PostMapping("/")
    public String homePageFilterByName (Model model, @RequestParam () String filterName){
        List<Restaurant> restaurants= jpaRestaurantRepository.findByNameContainingIgnoreCase(filterName);
        model.addAttribute("restaurants", restaurants);

        List<RestaurantType> restaurantTypes = jpaRestaurantTypeRepository.findAll();
        model.addAttribute("restaurantTypes", restaurantTypes);
        return "home";
    }

    @GetMapping("/type/{name}")
    public String homePageFilteredByType (Model model, @PathVariable("name") String name){
        List<Restaurant> restaurants= jpaRestaurantRepository.findByRestaurantTypes_Name(name);
        model.addAttribute("restaurants", restaurants);
        List<RestaurantType> restaurantTypes= jpaRestaurantTypeRepository.findAll();
        model.addAttribute("restaurantTypes", restaurantTypes);
        model.addAttribute("selectedRestaurantType", name);
        return "home";
    }

}
