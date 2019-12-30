package com.lypgod.demo.springboot.unittest.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SomeBusinessUtilSpringBootTest {
    @Resource
    private SomeBusinessUtil businessUtil;

    @Test
    public void calculateSumTest() {
        assertThat(businessUtil.calculateSum(new int[]{1, 2, 3, 4}), equalTo(10));
    }

    @Test
    public void calculateSumFromDataUtilTest() {
        SomeDataUtil dataUtil = mock(SomeDataUtil.class);
        businessUtil.setSomeDataUtil(dataUtil);

        when(dataUtil.retrieveData()).thenReturn(new int[]{1, 2, 3, 4});
        assertThat(businessUtil.calculateSumFromDataUtil(), equalTo(10));

        when(dataUtil.retrieveData()).thenReturn(new int[]{});
        assertThat(businessUtil.calculateSumFromDataUtil(), equalTo(0));

        when(dataUtil.retrieveData()).thenReturn(new int[]{5});
        assertThat(businessUtil.calculateSumFromDataUtil(), equalTo(5));
    }
}