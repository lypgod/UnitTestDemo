package com.lypgod.demo.springboot.unittest.controller;

import com.lypgod.demo.springboot.unittest.model.entity.Item;
import com.lypgod.demo.springboot.unittest.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ItemController {
    @Resource
    private ItemService itemService;

    @GetMapping("/item-from-service")
    public Item itemFromService() {
        return itemService.retrieveHardcodedItem();
    }

    @GetMapping("/all-items-from-database")
    public List<Item> allItemsFromDatabase() {
        return itemService.findAllItems();
    }
}
