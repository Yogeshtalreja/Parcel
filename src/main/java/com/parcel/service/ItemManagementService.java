package com.parcel.service;

import com.parcel.dto.ItemDto;
import com.parcel.dto.ItemIds;
import com.parcel.dto.LinesDto;
import com.parcel.dto.NewObject;
import com.parcel.entity.ItemManagement;
import com.parcel.repository.ItemManagementRepo;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemManagementService {

    private ItemManagementRepo repository;

    public LinesDto saveItemManagement(LinesDto model){


        for (NewObject object : model.getLines()){
            ItemManagement newObject = new ItemManagement();
            newObject.setId(object.getItem().getId());
            newObject.setDescription(object.getItem().getDescription());
            newObject.setName(object.getItem().getName());
            newObject.setQuantity(object.getQuantity());
            newObject.setPrice(object.getItem().getPrice());
            newObject.setCost(object.getItem().getCost());
            repository.save(newObject);

        }

        return model;
    }


    public LinesDto getItems(){
        List<ItemManagement> items = repository.findAll();
        LinesDto line = new LinesDto();
        List<NewObject> objs = new ArrayList<>();
        items.stream()
                .forEach(e -> {
                    NewObject obj = new NewObject();
                    obj.setItem(new ItemDto(e.getId()
                            ,e.getName(), e.getDescription(), e.getPrice(), e.getPrice()));
                    obj.setQuantity(e.getQuantity());
                    objs.add(obj);
                });
        line.setLines(objs);
        return line;
    }

    public ItemManagement findById(String id) throws ObjectNotFoundException {
        return repository.findById(id).orElseThrow(() -> new IllegalStateException("with this ID nor Found"));
    }
    public ItemIds removeItems(ItemIds model){

        model.getItemIds()
                .stream()
                .forEach(id -> {
                    repository.delete(this.findById(id));
                });
        return model;
    }
}
