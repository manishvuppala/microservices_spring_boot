package com.example.iteminfoservice.resources;

import com.example.iteminfoservice.models.Item;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemResource {
    @RequestMapping("/{itemId}")
    public Item getItemInfo(@PathVariable("itemId") String itemId){
        return new Item(itemId, "Test_Name");

    }
}
