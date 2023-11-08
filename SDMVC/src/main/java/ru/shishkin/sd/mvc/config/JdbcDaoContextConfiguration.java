package ru.shishkin.sd.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.shishkin.sd.mvc.dao.TaskJdbcDao;
import ru.shishkin.sd.mvc.dao.TodoListJdbcDao;

import javax.sql.DataSource;

/**
 * @author shishkin
 */
public class JdbcDaoContextConfiguration {
    @Bean
    public TaskJdbcDao taskJdbcDao(DataSource dataSource) {
        return new TaskJdbcDao(dataSource);
    }

    @Bean
    public TodoListJdbcDao listDao(DataSource dataSource) {
        return new TodoListJdbcDao(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:product.db");
        dataSource.setUsername("");
        dataSource.setPassword("");
        return dataSource;
    }
}
