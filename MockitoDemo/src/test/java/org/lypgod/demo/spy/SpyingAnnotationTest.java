package org.lypgod.demo.spy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpyingAnnotationTest {
    @Spy
    private List<String> spyList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void spyingTest() {
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
}
