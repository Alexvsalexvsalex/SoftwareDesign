package ru.shishkin.sd.mvc.model;

/**
 * @author shishkin
 */
public class Task {
    private int id;
    private int listId;
    private String name;
    private boolean ready = false;

    public Task() {
    }

    public Task(int id, int listId, String name, boolean ready) {
        this.id = id;
        this.listId = listId;
        this.name = name;
        this.ready = ready;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }
}
