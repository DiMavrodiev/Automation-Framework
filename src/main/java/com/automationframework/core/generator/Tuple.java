package com.automationframework.core.generator;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

public class Tuple<K,V> {
    K key;
    V val;

    public Tuple(K key, V val) {
        this.key = key;
        this.val = val;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getVal() {
        return val;
    }

    public void setVal(V val) {
        this.val = val;
    }

    public static <K, V> Tuple<K, V> from(K key, V val) {
        return new Tuple<>(key, val);
    }
}