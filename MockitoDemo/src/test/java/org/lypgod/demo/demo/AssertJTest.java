package org.lypgod.demo.demo;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJTest {
    @Test
    public void demo() {
        List<Integer> integers = Arrays.asList(12, 15, 45);

        assertThat(integers)
                .hasSize(3)
                .contains(12, 45)
                .allMatch(x -> x > 10)
                .allMatch(x -> x < 100)
                .noneMatch(x -> x < 0);

        assertThat("").isEmpty();
        assertThat("ABCDE")
                .contains("BC")
                .startsWith("ABC")
                .endsWith("CDE");
    }
}
