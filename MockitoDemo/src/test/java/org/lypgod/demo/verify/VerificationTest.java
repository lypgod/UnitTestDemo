package org.lypgod.demo.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VerificationTest {
    private List<String> stringsMock;

    @Before
    public void setUp() throws Exception {
        stringsMock = mock(List.class);
    }

    @Test
    public void verificationBasicsTest() {
        when(stringsMock.get(anyInt())).then(invocationOnMock -> {
            Integer argument = invocationOnMock.getArgumentAt(0, Integer.class);
            return String.valueOf(argument);
        });

        // SUT
        String value1 = stringsMock.get(0);
        String value2 = stringsMock.get(1);

        // verify
        verify(stringsMock).get(0);
        verify(stringsMock, times(2)).get(anyInt());
        verify(stringsMock, atLeast(1)).get(anyInt());
        verify(stringsMock, atLeastOnce()).get(anyInt());
        verify(stringsMock, atMost(2)).get(anyInt());
        verify(stringsMock, never()).get(2);
    }

    @Test
    public void argumentCapturingTest() {
        // SUT
        stringsMock.add("Some String");

        // verify
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(stringsMock).add(captor.capture());
        assertThat(captor.getValue(), equalTo("Some String"));
    }

    @Test
    public void multipleArgumentCapturingTest() {
        // SUT
        stringsMock.add("Some String1");
        stringsMock.add("Some String2");

        // verify
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(stringsMock, times(2)).add(captor.capture());
        assertThat(captor.getAllValues(), equalTo(Arrays.asList("Some String1", "Some String2")));
    }

    @After
    public void tearDown() throws Exception {
        reset(stringsMock);
    }
}
