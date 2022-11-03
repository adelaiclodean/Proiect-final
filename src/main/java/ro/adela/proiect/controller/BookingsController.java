package ro.adela.proiect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import ro.adela.proiect.model.Booking;
import ro.adela.proiect.model.CustomUserDetails;
import ro.adela.proiect.model.User;
import ro.adela.proiect.repo.JpaBookingRepository;
import ro.adela.proiect.services.IAuthenticationFacade;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("bookings")
public class BookingsController {
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    public JpaBookingRepository jpaBookingRepository;

    @GetMapping("/myBookings")
    public String myBookingsPage (Model model){
        Authentication authentication = authenticationFacade.getAuthentication();
        User authenticatedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();

        List<Booking> bookings = jpaBookingRepository.findByUser_Username(authenticatedUser.getUsername());
        model.addAttribute("bookings", bookings);
        return "myBookings";
    }
    @GetMapping("/delete/{id}")
    public RedirectView deleteBooking (@PathVariable("id")UUID id){
        jpaBookingRepository.deleteById(id);
        return new RedirectView("/bookings/myBookings");
    }
}
