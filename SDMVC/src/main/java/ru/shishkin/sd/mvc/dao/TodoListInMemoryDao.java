package ru.shishkin.sd.mvc.dao;

import ru.shishkin.sd.mvc.model.TodoList;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shishkin
 */
public class TodoListInMemoryDao implements TodoListDao {
    private final AtomicInteger lastId = new AtomicInteger(0);
    private final List<TodoList> lists = new CopyOnWriteArrayList<>();

    public int addList(TodoList list) {
        int id = lastId.incrementAndGet();
        list.setId(id);
        lists.add(list);
        return id;
    }

    public List<TodoList> getLists() {
        return List.copyOf(lists);
    }

    @Override
    public Optional<TodoList> getList(int id) {
        return lists.stream().filter(list -> list.getId() == id).findFirst();
    }
}
