package org.lypgod.demo.matcher;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.Serializable;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WildCardArgumentMatcherTest {
    @Mock
    private SimpleService simpleService;

    @Test
    public void wildCardTest() {
        when(simpleService.method1(anyInt(), anyString(), anyCollection(), isA(Serializable.class))).thenReturn(100);

        int result = simpleService.method1(1, "lyp", Collections.emptyList(), "Mockito");
        assertThat(result, equalTo(100));
        result = simpleService.method1(2, "god", Collections.emptySet(), "PowerMock");
        assertThat(result, equalTo(100));
    }

    @Test
    public void wildCardWithSpecialTest() {
        when(simpleService.method1(anyInt(), eq("lyp"), anyCollection(), isA(Serializable.class))).thenReturn(100);
        when(simpleService.method1(anyInt(), eq("god"), anyCollection(), isA(Serializable.class))).thenReturn(200);

        int result = simpleService.method1(1, "lyp", Collections.emptyList(), "Mockito");
        assertThat(result, equalTo(100));
        result = simpleService.method1(2, "god", Collections.emptySet(), "PowerMock");
        assertThat(result, equalTo(200));
        result = simpleService.method1(2, "lypgod", Collections.emptySet(), "PowerMock");
        assertThat(result, equalTo(0));
    }

    @After
    public void tearDown() throws Exception {
        reset(simpleService);
    }
}
