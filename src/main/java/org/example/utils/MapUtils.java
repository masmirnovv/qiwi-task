package org.example.utils;

import java.util.Map;

public abstract class MapUtils {

    public static <K, V> V getExpected(Map<K, V> map, K key) {
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException("No key '" + key + "' provided.");
        } else {
            return map.get(key);
        }
    }

}
