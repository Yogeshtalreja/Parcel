package com.parcel.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.parcel.entity.ItemManagement;
import com.parcel.entity.Offer;
import com.parcel.repository.OfferRepository;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OfferService.class})
@ExtendWith(SpringExtension.class)
class OfferServiceTest {
    @MockBean
    private ItemManagementService itemManagementService;

    @MockBean
    private OfferRepository offerRepository;

    @Autowired
    private OfferService offerService;

    @Test
    void testFindById() throws ObjectNotFoundException {
        ItemManagement itemManagement = new ItemManagement();
        itemManagement.setCost(1);
        itemManagement.setId("42");
        itemManagement.setQuantity(1);
        itemManagement.setName("Name");
        itemManagement.setDescription("The characteristics of someone or something");
        itemManagement.setPrice(1);

        Offer offer = new Offer();
        offer.setItemId(itemManagement);
        offer.setId("42");
        offer.setName("Name");
        offer.setPriceReduction(1);
        offer.setDescription("The characteristics of someone or something");
        offer.setQuantityThreshold(1);
        Optional<Offer> ofResult = Optional.of(offer);
        when(this.offerRepository.findById((String) any())).thenReturn(ofResult);
        assertSame(offer, this.offerService.findById("42"));
        verify(this.offerRepository).findById((String) any());
        assertTrue(this.offerService.getOffers() instanceof com.parcel.dto.ReturnOfferAfterSave);
    }

    @Test
    void testFindById2() throws ObjectNotFoundException {
        when(this.offerRepository.findById((String) any())).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class, () -> this.offerService.findById("42"));
        verify(this.offerRepository).findById((String) any());
    }
}

