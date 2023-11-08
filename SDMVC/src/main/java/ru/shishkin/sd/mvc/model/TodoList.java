package ru.shishkin.sd.mvc.model;

import java.util.List;

public class TodoList {
    private int id;
    private String name;

    public TodoList(){}

    public TodoList(int id, String name) {
        this.id = id;
        this.name = name;
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
}
