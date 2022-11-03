package ro.adela.proiect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.adela.proiect.model.User;
import ro.adela.proiect.repo.UserRepository;

import java.util.UUID;


@Controller
public class LoginController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signUp")
    public String signUpPage() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUpCreate(Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("errorMessage", "User name already exists");
            return "signUp";
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User newUser = new User(UUID.randomUUID(), username, passwordEncoder.encode(password));
            userRepository.save(newUser);
            return "login";
        }
    }
}
