package com.lypgod.demo.springboot.unittest.util;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SomeBusinessUtil {
    public int calculateSum(int[] data) {
        return Arrays.stream(data).sum();
    }

    private SomeDataUtil someDataUtil;

    public void setSomeDataUtil(SomeDataUtil someDataUtil) {
        this.someDataUtil = someDataUtil;
    }

    public int calculateSumFromDataUtil() {
        return Arrays.stream(someDataUtil.retrieveData()).sum();
    }
}
