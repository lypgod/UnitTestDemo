package com.lypgod.demo.springboot.unittest.service;

import com.lypgod.demo.springboot.unittest.model.entity.Item;
import com.lypgod.demo.springboot.unittest.model.repository.ItemRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemService {
    @Resource
    private ItemRepository itemRepository;

    public Item retrieveHardcodedItem() {
        return new Item().setId(1).setName("Ball").setPrice(10).setQuantity(100);
    }

    public List<Item> findAllItems() {
        List<Item> itemList = itemRepository.findAll();
        itemList.forEach(e -> {
            e.setValue(e.getPrice() * e.getQuantity());
        });
        return itemList;
    }
}
