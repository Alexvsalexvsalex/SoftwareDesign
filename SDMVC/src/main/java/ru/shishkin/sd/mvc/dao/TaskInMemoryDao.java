package ru.shishkin.sd.mvc.dao;

import ru.shishkin.sd.mvc.model.Task;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author shishkin
 */
public class TaskInMemoryDao implements TaskDao {
    private final AtomicInteger lastId = new AtomicInteger(0);
    private final List<Task> tasks = new CopyOnWriteArrayList<>();

    public int addTask(Task task) {
        int id = lastId.incrementAndGet();
        task.setId(id);
        tasks.add(task);
        return id;
    }

    public List<Task> getTasks(int todoListId) {
        return tasks.stream().filter((task) -> task.getListId() == todoListId).collect(Collectors.toList());
    }

    @Override
    public void updateTask(int taskId, boolean taskReady) {
        for (Task savedTask : tasks) {
            if (savedTask.getId() == taskId) {
                savedTask.setReady(taskReady);
            }
        }
    }

    @Override
    public void deleteTask(int taskId) {
        List<Task> savedTasks = tasks;
        savedTasks.stream().filter(task -> task.getId() == taskId).findFirst().ifPresent(savedTasks::remove);
    }
}
