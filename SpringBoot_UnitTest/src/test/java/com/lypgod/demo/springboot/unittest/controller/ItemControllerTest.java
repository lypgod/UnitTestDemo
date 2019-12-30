package com.lypgod.demo.springboot.unittest.controller;

import com.lypgod.demo.springboot.unittest.model.entity.Item;
import com.lypgod.demo.springboot.unittest.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {
    @Resource
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemServiceMock;

    @Test
    public void itemFromServiceTest() throws Exception {
        when(itemServiceMock.retrieveHardcodedItem()).thenCallRealMethod();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/item-from-service").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{id: 1, name: Ball, price: 10, quantity: 100}"))
                .andReturn();
    }

    @Test
    public void allItemsFromDatabaseTest() throws Exception {
        when(itemServiceMock.findAllItems()).thenReturn(
                Arrays.asList(
                        new Item().setId(10001).setName("Item1").setPrice(10).setQuantity(21).setValue(210),
                        new Item().setId(10002).setName("Item2").setPrice(20).setQuantity(22).setValue(440),
                        new Item().setId(10003).setName("Item3").setPrice(30).setQuantity(23).setValue(690)
                )
        );

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all-items-from-database").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("[{id: 10001, name: Item1, price: 10, quantity: 21, value: 210}," +
                        "{id: 10002, name: Item2, price: 20, quantity: 22, value: 440}," +
                        "{}]"))
                .andReturn();
    }
}