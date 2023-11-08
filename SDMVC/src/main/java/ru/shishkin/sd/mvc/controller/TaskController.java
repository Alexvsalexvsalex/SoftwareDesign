package ru.shishkin.sd.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shishkin.sd.mvc.dao.TaskDao;
import ru.shishkin.sd.mvc.model.Task;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shishkin
 */
@Controller
public class TaskController {
    private final TaskDao taskDao;

    public TaskController(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @RequestMapping(value = "/add-task", method = RequestMethod.POST)
    public String addTask(HttpServletRequest request,
                          @RequestParam int listId, @ModelAttribute("task") Task task) {
        task.setListId(listId);
        taskDao.addTask(task);
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/mark-task", method = RequestMethod.POST)
    public String markTask(HttpServletRequest request,
                           @RequestParam int taskId, @RequestParam boolean taskReady) {
        taskDao.updateTask(taskId, taskReady);
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/delete-task", method = RequestMethod.POST)
    public String deleteTask(HttpServletRequest request,
                             @RequestParam int taskId) {
        taskDao.deleteTask(taskId);
        return "redirect:" + request.getHeader("Referer");
    }
}
