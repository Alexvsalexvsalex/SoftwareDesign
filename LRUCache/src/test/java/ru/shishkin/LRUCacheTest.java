package ru.shishkin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.IntStream;


class LRUCacheTest {
    private final int limit = 5;
    private LRUCache<Integer, Integer> cache;

    @BeforeEach
    void setUp() {
        cache = new LRUCache<>(limit);
    }

    @Test
    void addAndGet() {
        int key = 1;
        int value = 3;
        cache.add(key, value);
        Assertions.assertEquals(cache.get(key), Optional.of(value));
    }

    @Test
    void getNoElement() {
        Assertions.assertEquals(cache.get(1), Optional.empty());
    }

    @Test
    void checkCacheRecycle() {
        int start = 0;
        int end = limit + 1;
        IntStream.range(start, end).forEach((i) -> cache.add(i, i));
        Assertions.assertEquals(cache.get(start), Optional.empty());
        Assertions.assertEquals(cache.get(1), Optional.of(1));
        Assertions.assertEquals(cache.get(end - 1), Optional.of(end - 1));
    }

    @Test
    void checkCacheRule() {
        int start = 0;
        IntStream.range(start, limit).forEach((i) -> cache.add(i, i));
        cache.add(0, 0);
        cache.add(-1, -1);
        Assertions.assertEquals(cache.get(0), Optional.of(0));
        Assertions.assertEquals(cache.get(1), Optional.empty());
    }

    @Test
    void updateValueInCache() {
        cache.add(1, 1);
        cache.add(1, 2);
        Assertions.assertEquals(cache.get(1), Optional.of(2));
    }
}