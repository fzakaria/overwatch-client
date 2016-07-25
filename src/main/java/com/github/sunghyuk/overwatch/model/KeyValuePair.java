package com.github.sunghyuk.overwatch.model;

import java.util.AbstractMap;

/**
 * Created by sunghyuk on 2016. 7. 20..
 */
public class KeyValuePair<K, V> extends AbstractMap.SimpleEntry<K, V> {

    public KeyValuePair(K key, V value) {
        super(key, value);
    }
}
