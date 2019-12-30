package org.lypgod.demo.spy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SpyingTest {
    @Test
    public void spyingTest() {
        List<String> realList = new ArrayList<>();
        List<String> spyList = spy(realList);

        spyList.add("Mockito");
        spyList.add("PowerMock");

        assertThat(spyList.get(0), equalTo("Mockito"));
        assertThat(spyList.get(1), equalTo("PowerMock"));
        assertThat(spyList.isEmpty(), equalTo(false));
        assertThat(spyList.size(), equalTo(2));

        when(spyList.isEmpty()).thenReturn(true);
        when(spyList.size()).thenReturn(0);

        assertThat(spyList.get(0), equalTo("Mockito"));
        assertThat(spyList.get(1), equalTo("PowerMock"));
        assertThat(spyList.isEmpty(), equalTo(true));
        assertThat(spyList.size(), equalTo(0));
    }

    @Test
    public void spyingTest2() {
        List<String> listMock = mock(ArrayList.class);
        assertThat(listMock.get(0), nullValue());
        assertThat(listMock.size(), equalTo(0));
        listMock.add("Some String1");
        listMock.add("Some String2");
        assertThat(listMock.size(), equalTo(0));
        when(listMock.size()).thenReturn(5);
        assertThat(listMock.size(), equalTo(5));

        List<String> listSpy = spy(ArrayList.class);
        try {
            listSpy.get(0);
        } catch (Exception e) {
            assertThat(e, instanceOf(IndexOutOfBoundsException.class));
        }
        assertThat(listSpy.size(), equalTo(0));
        listSpy.add("Some String1");
        listSpy.add("Some String2");
        assertThat(listSpy.size(), equalTo(2));
        when(listSpy.size()).thenReturn(5);
        assertThat(listSpy.size(), equalTo(5));
    }
}
