package com.lypgod.demo.springboot.unittest.service;

import com.lypgod.demo.springboot.unittest.model.entity.Item;
import com.lypgod.demo.springboot.unittest.model.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ItemServiceTest {
    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void findAllItemsTest() {
        when(itemRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Item().setId(10001).setName("Item1").setPrice(10).setQuantity(21),
                        new Item().setId(10002).setName("Item2").setPrice(20).setQuantity(22),
                        new Item().setId(10003).setName("Item3").setPrice(30).setQuantity(23)
                ));
        List<Item> allItems = itemService.findAllItems();
        assertThat(allItems.get(0).getValue(), equalTo(210));
        assertThat(allItems.get(1).getValue(), equalTo(440));
        assertThat(allItems.get(2).getValue(), equalTo(690));
    }
}
