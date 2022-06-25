package com.parcel.controller;

import com.parcel.dto.OfferIds;
import com.parcel.dto.Offers;
import com.parcel.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class OfferController {

    private OfferService service;

    @PostMapping("offers-management")
    public Object saveOffers(@RequestBody Offers model){
        return service.saveOffers(model);
    }

    @GetMapping("offers-management")
    public Object getOffers(){
        return service.getOffers();
    }

    @DeleteMapping("/offers-management")
    public Object deleteOffers(@RequestBody OfferIds model){
        return service.deleteOffers(model);
    }
}
