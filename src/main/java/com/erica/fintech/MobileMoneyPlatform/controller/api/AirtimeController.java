package com.erica.fintech.MobileMoneyPlatform.controller.api;

import com.erica.fintech.MobileMoneyPlatform.dto.AirtimeRequest;
import com.erica.fintech.MobileMoneyPlatform.service.AirtimeService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/airtime")
public class AirtimeController {

    private final AirtimeService airtimeService;

    public AirtimeController(AirtimeService airtimeService) {
        this.airtimeService = airtimeService;
    }

    @PostMapping("/buy")
    public String buyAirtime(@AuthenticationPrincipal UserDetails userDetails,
                             @Valid @RequestBody AirtimeRequest request) throws Exception {
        String phone = userDetails.getUsername();
        airtimeService.buyAirtimeByPhone(phone, request.amount());
        return "Airtime purchase successful";
    }

    @GetMapping("/balance")
    public double getAirtimeBalance(@AuthenticationPrincipal UserDetails userDetails) throws Exception {
        String phone = userDetails.getUsername();
        return airtimeService.getAirtimeBalanceByPhone(phone);
    }

}

