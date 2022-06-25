package com.parcel.dto;

import com.parcel.entity.ItemManagement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
    private String id;

    private String name;


    private String itemId;

    private Integer priceReduction;

    private String description;

    private Integer quantityThreshold;
}
