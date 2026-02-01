package com.erica.fintech.MobileMoneyPlatform.controller;

import com.erica.fintech.MobileMoneyPlatform.model.User;
import com.erica.fintech.MobileMoneyPlatform.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final UserRepository userRepo;

    public GlobalControllerAdvice(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // Add firstName of logged-in user to ALL templates
    @ModelAttribute("firstName")
    public String addFirstNameToModel(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return null; // Not logged in
        return userRepo.findByPhone(userDetails.getUsername())
                .map(User::getFirstName)
                .orElse(null);
    }
}
