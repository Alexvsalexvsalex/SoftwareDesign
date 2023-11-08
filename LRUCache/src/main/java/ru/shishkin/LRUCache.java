package ru.shishkin;

import java.util.Optional;

public class LRUCache<K, V> {
    final private QueueMap<K, V> queueMap;

    public LRUCache(int limit) {
        queueMap = new QueueMap<>(limit);
    }

    public void add(final K key, final V value) {
        queueMap.addFirst(key, value);
    }

    public Optional<V> get(K key) {
        return queueMap.get(key);
    }
}
