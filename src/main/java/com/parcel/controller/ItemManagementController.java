package com.parcel.controller;

import com.parcel.dto.ItemIds;
import com.parcel.dto.LinesDto;
import com.parcel.service.ItemManagementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ItemManagementController {

    private ItemManagementService service;


    @PostMapping("/item-management")
    public LinesDto saveItemsManagement(@RequestBody LinesDto model){
        return service.saveItemManagement(model);
    }


    @GetMapping("/item-management")
    public LinesDto getItems(){
        return service.getItems();
    }

    @DeleteMapping("/item-management")
    public ItemIds deleteByIds(@RequestBody ItemIds model){
        return service.removeItems(model);
    }

}
