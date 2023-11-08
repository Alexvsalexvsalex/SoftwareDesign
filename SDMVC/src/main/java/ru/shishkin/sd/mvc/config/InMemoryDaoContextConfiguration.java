package ru.shishkin.sd.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.shishkin.sd.mvc.dao.TaskDao;
import ru.shishkin.sd.mvc.dao.TaskInMemoryDao;
import ru.shishkin.sd.mvc.dao.TodoListDao;
import ru.shishkin.sd.mvc.dao.TodoListInMemoryDao;

/**
 * @author shishkin
 */
@Configuration
public class InMemoryDaoContextConfiguration {
    @Bean
    public TaskDao taskDao() {
        return new TaskInMemoryDao();
    }

    @Bean
    public TodoListDao listDao() {
        return new TodoListInMemoryDao();
    }
}
