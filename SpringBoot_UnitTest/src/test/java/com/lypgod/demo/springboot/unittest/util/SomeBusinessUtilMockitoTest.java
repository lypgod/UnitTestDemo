package com.lypgod.demo.springboot.unittest.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
// @RunWith(MockitoJUnitRunner.class)
public class SomeBusinessUtilMockitoTest {
    @Mock
    private SomeDataUtil dataUtil;
    @InjectMocks
    private SomeBusinessUtil businessUtil;

    @Test
    public void calculateSumFromDataUtilTest() {
        when(dataUtil.retrieveData()).thenReturn(new int[]{1, 2, 3, 4});
        assertThat(businessUtil.calculateSumFromDataUtil(), equalTo(10));

        when(dataUtil.retrieveData()).thenReturn(new int[]{});
        assertThat(businessUtil.calculateSumFromDataUtil(), equalTo(0));

        when(dataUtil.retrieveData()).thenReturn(new int[]{5});
        assertThat(businessUtil.calculateSumFromDataUtil(), equalTo(5));
    }
}