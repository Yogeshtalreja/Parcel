package com.parcel.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.parcel.entity.ItemManagement;
import com.parcel.repository.ItemManagementRepo;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ItemManagementService.class})
@ExtendWith(SpringExtension.class)
class ItemManagementServiceTest {
    @MockBean
    private ItemManagementRepo itemManagementRepo;

    @Autowired
    private ItemManagementService itemManagementService;

    @Test
    void testFindById() throws ObjectNotFoundException {
        ItemManagement itemManagement = new ItemManagement();
        itemManagement.setCost(1);
        itemManagement.setId("42");
        itemManagement.setQuantity(1);
        itemManagement.setName("Name");
        itemManagement.setDescription("The characteristics of someone or something");
        itemManagement.setPrice(1);
        Optional<ItemManagement> ofResult = Optional.of(itemManagement);
        when(this.itemManagementRepo.findById((String) any())).thenReturn(ofResult);
        assertSame(itemManagement, this.itemManagementService.findById("42"));
        verify(this.itemManagementRepo).findById((String) any());
    }

    @Test
    void testFindById2() throws ObjectNotFoundException {
        when(this.itemManagementRepo.findById((String) any())).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class, () -> this.itemManagementService.findById("42"));
        verify(this.itemManagementRepo).findById((String) any());
    }
}

