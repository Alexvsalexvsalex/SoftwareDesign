package ru.shishkin.sd.mvc.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import ru.shishkin.sd.mvc.model.Task;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author shishkin
 */
public class TaskJdbcDao extends JdbcDaoSupport implements TaskDao {

    public TaskJdbcDao(DataSource dataSource) {
        super();
        setDataSource(dataSource);
    }

    @Override
    public int addTask(Task task) {
        String sql = "INSERT INTO TASK (LIST_ID, NAME, READY) VALUES (?, ?, ?)";
        return getJdbcTemplate().update(sql, task.getListId(), task.getName(), task.getReady());
    }

    @Override
    public List<Task> getTasks(int todoListId) {
        String sql = "SELECT * FROM TASK WHERE LIST_ID = ?";
        return getJdbcTemplate().query(sql, new Object[]{todoListId}, new BeanPropertyRowMapper(Task.class));
    }

    @Override
    public void updateTask(int taskId, boolean taskReady) {
        String sql = "UPDATE TASK SET READY = ? WHERE ID = ?";
        getJdbcTemplate().update(sql, new Object[]{taskReady, taskId});
    }

    @Override
    public void deleteTask(int taskId) {
        String sql = "DELETE FROM TASK WHERE ID = ?";
        getJdbcTemplate().update(sql, taskId);
    }
}
