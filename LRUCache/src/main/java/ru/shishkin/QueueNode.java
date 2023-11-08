package ru.shishkin;

public class QueueNode<K, V> {
    private V value;
    private K prev;
    private K next;

    public QueueNode(K prev, V value, K next) {
        this.prev = prev;
        this.value = value;
        this.next = next;
    }

    public QueueNode(V value) {
        this(null, value, null);
    }

    public void setPrev(K prev) {
        assert prev != this;
        this.prev = prev;
    }

    public void setNext(K next) {
        assert next != this;
        this.next = next;
    }

    public V getValue() {
        return value;
    }

    public K getPrev() {
        return prev;
    }

    public K getNext() {
        return next;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
