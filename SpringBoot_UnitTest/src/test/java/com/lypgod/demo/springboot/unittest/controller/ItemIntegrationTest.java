package com.lypgod.demo.springboot.unittest.controller;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemIntegrationTest {
    @Resource
    private TestRestTemplate testRestTemplate;

    @Test
    public void contextLoads() throws JSONException {
        String response = testRestTemplate.getForObject("/all-items-from-database", String.class);
        JSONAssert.assertEquals("[{id: 10001}, {id: 10002}, {id: 10003}]", response, false);
    }
}
