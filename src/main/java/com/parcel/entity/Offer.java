package com.parcel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Offer {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    private String name;

    @OneToOne
    private ItemManagement itemId;

    private Integer priceReduction;

    private String description;

    private Integer quantityThreshold;

}
