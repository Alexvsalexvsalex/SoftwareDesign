package ru.shishkin.sd.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shishkin.sd.mvc.dao.TaskDao;
import ru.shishkin.sd.mvc.dao.TodoListDao;
import ru.shishkin.sd.mvc.model.Task;
import ru.shishkin.sd.mvc.model.TodoList;

/**
 * @author shishkin
 */
@Controller
public class TodoListController {
    private final TodoListDao todoListDao;
    private final TaskDao taskDao;

    public TodoListController(TodoListDao todoListDao, TaskDao taskDao) {
        this.todoListDao = todoListDao;
        this.taskDao = taskDao;
    }

    @RequestMapping(value = "/add-list", method = RequestMethod.POST)
    public String addList(@ModelAttribute("list") TodoList todoList) {
        todoListDao.addList(todoList);
        return "redirect:/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getLists(ModelMap map) {
        map.addAttribute("lists", todoListDao.getLists());
        map.addAttribute("list", new TodoList());
        return "index";
    }

    @RequestMapping(value = "/get-list", method = RequestMethod.GET)
    public String getList(@RequestParam int listId, ModelMap map) {
        map.addAttribute("list", todoListDao.getList(listId).get());
        map.addAttribute("tasks", taskDao.getTasks(listId));
        map.addAttribute("task", new Task());
        return "list_view";
    }
}
