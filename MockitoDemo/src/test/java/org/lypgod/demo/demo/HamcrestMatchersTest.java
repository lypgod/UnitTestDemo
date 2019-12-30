package org.lypgod.demo.demo;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersTest {
    @Test
    public void demo() {
        List<Integer> integers = Arrays.asList(12, 15, 45);

        assertThat(integers, hasSize(3));
        assertThat(integers, hasItems(12, 45));
        assertThat(integers, everyItem(greaterThan(10)));

        assertThat("", isEmptyString());
        assertThat("ABCDE", containsString("BCD"));
        assertThat("ABCDE", startsWith("AB"));
        assertThat("ABCDE", endsWith("CDE"));
    }
}
