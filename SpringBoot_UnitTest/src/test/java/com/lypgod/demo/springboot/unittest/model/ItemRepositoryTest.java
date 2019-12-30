package com.lypgod.demo.springboot.unittest.model;

import com.lypgod.demo.springboot.unittest.model.entity.Item;
import com.lypgod.demo.springboot.unittest.model.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTest {
    @Resource
    private ItemRepository itemRepository;

    @Test
    public void findAllItemsTest() {
        List<Item> all = itemRepository.findAll();
        assertThat(all.size(), equalTo(3));
    }
}
