package ru.shishkin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class QueueMap<K, V> {
    private final Map<K, QueueNode<K, V>> map;
    private K head;
    private K tail;
    final private int limit;

    public QueueMap(int limit) {
        assert limit > 0;

        this.head = null;
        this.tail = null;
        this.limit = limit;
        this.map = new HashMap<>();
    }

    public boolean isEmpty() {
        if (map.isEmpty()) {
            assert head == null && tail == null;
            return true;
        } else {
            assert head != null && tail != null;
            return false;
        }
    }

    private void pushToHead(final K key, final QueueNode<K, V> node) {
        remove(key);
        makeHead(key, node);
    }

    private void makeHead(final K key, final QueueNode<K, V> node) {
        if (isEmpty()) {
            tail = key;
        } else {
            node.setNext(head);
            map.get(head).setPrev(key);
        }
        head = key;
        map.put(key, node);
    }

    public void addFirst(final K key, final V value) {
        assert map.size() <= limit;
        final QueueNode<K, V> node;
        if (map.containsKey(key)) {
            node = map.get(key);
            remove(key);
            node.setValue(value);
        } else {
            if (map.size() == limit) {
                deleteLast();
            }
            node = new QueueNode<>(value);
        }
        makeHead(key, node);
    }

    public Optional<V> get(final K key) {
        Optional<QueueNode<K, V>> value = Optional.ofNullable(map.get(key));
        value.ifPresent((v) -> pushToHead(key, v));
        return value.map(QueueNode::getValue);
    }

    private void deleteLast() {
        assert !isEmpty();
        remove(tail);
    }

    public void remove(final K key) {
        if (map.containsKey(key)) {
            QueueNode<K, V> node = map.remove(key);
            K prevKey = node.getPrev();
            K nextKey = node.getNext();
            if (key == head) {
                head = nextKey;
            }
            if (key == tail) {
                tail = prevKey;
            }
            if (prevKey != null) {
                map.get(prevKey).setNext(nextKey);
            }
            if (nextKey != null) {
                map.get(nextKey).setNext(prevKey);
            }
        }
    }
}
