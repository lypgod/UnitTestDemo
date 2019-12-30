package org.lypgod.demo.matcher;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

public class ArgumentMatcherTest {
    @Test
    public void basicTest() {
        List<Integer> list = mock(ArrayList.class);
        when(list.get(0)).thenReturn(100);

        assertThat(list.get(0), equalTo(100));
        assertThat(list.get(1), nullValue());
    }

    @Test
    public void isAAndAnyTest() {
        Foo mockFoo = mock(Foo.class);

        when(mockFoo.function(isA(InterfaceA.class))).thenReturn(100);
        int result = mockFoo.function(new Impl1());
        assertThat(result, equalTo(100));

        reset(mockFoo);

        when(mockFoo.function(isA(Impl1.class))).thenReturn(100);
        result = mockFoo.function(new Impl1());
        assertThat(result, equalTo(100));
        result = mockFoo.function(new Impl2());
        assertThat(result, equalTo(0));

        reset(mockFoo);

        when(mockFoo.function(any(Impl1.class))).thenReturn(100);
        result = mockFoo.function(new Impl1());
        assertThat(result, equalTo(100));
        result = mockFoo.function(new Impl2());
        assertThat(result, equalTo(100));
    }

    static class Foo {
        int function(InterfaceA interfaceA) {
            return interfaceA.work();
        }
    }

    interface InterfaceA {
        int work();
    }

    class Impl1 implements InterfaceA {
        @Override
        public int work() {
            throw new RuntimeException();
        }
    }

    class Impl2 implements InterfaceA {
        @Override
        public int work() {
            throw new RuntimeException();
        }
    }
}
