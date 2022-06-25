package com.parcel.service;

import com.parcel.dto.OfferIds;
import com.parcel.dto.OfferIdsAfterSave;
import com.parcel.dto.Offers;
import com.parcel.dto.ReturnOfferAfterSave;
import com.parcel.entity.ItemManagement;
import com.parcel.entity.Offer;
import com.parcel.repository.ItemManagementRepo;
import com.parcel.repository.OfferRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OfferService {

    private OfferRepository repository;
    private ItemManagementService itemManagementService;

    public Object saveOffers(Offers model){


        List<Offer> offers = new ArrayList<>();
        model.getOffers()
                .stream().forEach(offer -> {
            ItemManagement itemManagement = itemManagementService.findById(offer.getItemId());
            offers.add(repository.save(new Offer(offer.getId(), offer.getName(), itemManagement, offer.getPriceReduction()
                    , offer.getDescription(), offer.getQuantityThreshold()))
);

        });
        return new ReturnOfferAfterSave("POST offers success example", model.getOffers());
    }

    public Object getOffers(){
        List<com.parcel.dto.Offer> offers = new ArrayList<>();
        repository.findAll()
                .stream().forEach(e-> {
                    offers.add(new com.parcel.dto.Offer(e.getId(),e.getName(),
                            e.getItemId().getId(),e.getPriceReduction(),e.getDescription(),e.getQuantityThreshold()));
                });
        return new ReturnOfferAfterSave("GET offers success example", offers);
    }

    public Offer findById(String id) throws ObjectNotFoundException {
        return repository.findById(id).orElseThrow(() -> new IllegalStateException("with this ID nor Found"));
    }

    public Object deleteOffers(OfferIds model){
        model.getOfferIds()
                .stream()
                .forEach(id->{
                        repository.delete(this.findById(id));
                });
        return new OfferIdsAfterSave("delete offers success example", model.getOfferIds());
    }
}
