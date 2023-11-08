package ru.shishkin.sd.mvc.dao;

import ru.shishkin.sd.mvc.model.Task;

import java.util.List;

/**
 * @author shishkin
 */
public interface TaskDao {
    int addTask(Task task);

    List<Task> getTasks(int todoListId);

    void updateTask(int taskId, boolean taskReady);

    void deleteTask(int taskId);
}
