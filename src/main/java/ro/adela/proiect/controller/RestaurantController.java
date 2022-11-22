package ro.adela.proiect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ro.adela.proiect.model.*;
import ro.adela.proiect.repo.JpaBookingRepository;
import ro.adela.proiect.repo.JpaRestaurantRepository;
import ro.adela.proiect.repo.JpaReviewRepository;
import ro.adela.proiect.services.IAuthenticationFacade;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("restaurants")
public class RestaurantController {

    @Autowired
    private IAuthenticationFacade authenticationFacade;
    @Autowired
    public JpaRestaurantRepository jpaRestaurantRepository;
    @Autowired
    public JpaBookingRepository jpaBookingRepository;

    @Autowired
    public JpaReviewRepository jpaReviewRepository;

    @GetMapping("/{name}")
    public String restaurantPage (Model model, @PathVariable("name") String name){
        Restaurant restaurant= jpaRestaurantRepository.findByName(name).stream().findFirst().orElse(new Restaurant());
        List<WorkingHours> workingHours = restaurant.getWorkingHours();

        Authentication authentication = authenticationFacade.getAuthentication();
        User authenticatedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        Review userReview=restaurant.getReviews().stream().filter(x -> x.getUser().getUsername().equals(authenticatedUser.getUsername())).findAny().orElse(null);
        boolean hasReview=userReview != null;

        if (restaurant.getReviews().size() != 0){
            int totalReviewRating=restaurant.getReviews().stream().mapToInt(x -> x.getRating()).sum();
            double restaurantReviewRating=totalReviewRating/restaurant.getReviews().size();
            restaurant.setRating(restaurantReviewRating);
        }
        else{
            restaurant.setRating(0);
        }

        model.addAttribute("restaurant", restaurant);
        model.addAttribute("workingHours", workingHours);
        model.addAttribute("currentDate", LocalDateTime.now().truncatedTo(ChronoUnit.HOURS));
        model.addAttribute("hasReview", hasReview);
        return "restaurant";
    }

    @PostMapping("/book")
    public RedirectView restaurantBook (@RequestParam("selectedDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime selectedDate, @RequestParam ("numberOfPeople") int numberOfPeople, @RequestParam ("restaurantId") UUID restaurantId){
        Authentication authentication = authenticationFacade.getAuthentication();
        User authenticatedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();

        Booking booking = new Booking(UUID.randomUUID(),numberOfPeople,selectedDate,new Restaurant(restaurantId),authenticatedUser);
        jpaBookingRepository.save(booking);
        return new RedirectView("/bookings/myBookings");
    }

    @PostMapping("/submitReview")
    public RedirectView submitReview (@RequestParam("reviewDescription") String reviewDescription, @RequestParam ("rating") int rating, @RequestParam ("restaurantId") UUID restaurantId, @RequestParam ("restaurantName") String restaurantName){
        Authentication authentication = authenticationFacade.getAuthentication();
        User authenticatedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();

        Review review=new Review(UUID.randomUUID(),reviewDescription,rating,new Restaurant(restaurantId),authenticatedUser);

        jpaReviewRepository.save(review);
        return new RedirectView("/restaurants/"+ restaurantName);
    }
}
