package ru.shishkin.sd.mvc.dao;

import ru.shishkin.sd.mvc.model.TodoList;

import java.util.List;
import java.util.Optional;

/**
 * @author shishkin
 */
public interface TodoListDao {
    int addList(TodoList list);

    List<TodoList> getLists();

    Optional<TodoList> getList(int id);
}
