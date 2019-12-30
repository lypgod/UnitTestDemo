package org.lypgod.demo.matcher;

import java.io.Serializable;
import java.util.Collection;

public class SimpleService {
    public int method1(int i, String s, Collection<?> c, Serializable serializable) {
        throw new RuntimeException();
    }

    public int method2(int i, String s, Collection<?> c, Serializable serializable) {
        throw new RuntimeException();
    }
}
