package com.iisc.pav.others;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ModelMap<K,V> extends HashMap<K,V> {
    public ModelMap(int i, float v) {
        super(i, v);
    }

    public ModelMap(int i) {
        super(i);
    }

    public ModelMap() {
    }

    public ModelMap(Map<? extends K, ? extends V> map) {
        super(map);
    }
}
