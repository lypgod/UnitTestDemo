package org.lypgod.demo.stub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StubbingTest {
    private List<String> strings;

    @Before
    public void setUp() throws Exception {
        strings = mock(ArrayList.class);
    }

    @Test
    public void stubbingTest() {
        when(strings.get(0)).thenReturn("first");
        assertThat(strings.get(0), equalTo("first"));

        when(strings.get(anyInt())).thenThrow(new RuntimeException());
        try {
            strings.get(1);
            fail();
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }

//        when(strings.size()).thenReturn(1, 2, 3, 4);
        when(strings.size()).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(4);
        assertThat(strings.size(), equalTo(1));
        assertThat(strings.size(), equalTo(2));
        assertThat(strings.size(), equalTo(3));
        assertThat(strings.size(), equalTo(4));
        assertThat(strings.size(), equalTo(4));
    }

    @Test
    public void stubbingStubberTest() {
        doNothing().when(strings).clear();
        strings.clear();
        verify(strings, times(1)).clear();

        doThrow(RuntimeException.class).when(strings).clear();
        try {
            strings.clear();
            fail();
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }

        doReturn("first").when(strings).get(0);
        assertThat(strings.get(0), equalTo("first"));
    }

    @Test
    public void stubbingCustomAnswerTest() {
        when(strings.get(anyInt())).then(invocationOnMock -> {
            Integer argument = invocationOnMock.getArgumentAt(0, Integer.class);
            return String.valueOf(argument * 10);
        });

        assertThat(strings.get(0), equalTo("0"));
        assertThat(strings.get(1), equalTo("10"));
    }

    @After
    public void tearDown() throws Exception {
        reset(this.strings);
    }
}

