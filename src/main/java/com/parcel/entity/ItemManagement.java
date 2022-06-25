package com.parcel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class ItemManagement {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    private String name;

    private String description;

    private Integer price;

    private Integer cost;

    private Integer quantity;

    public ItemManagement() {

    }
}
