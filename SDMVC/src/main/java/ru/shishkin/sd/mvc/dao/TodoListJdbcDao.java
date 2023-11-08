package ru.shishkin.sd.mvc.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import ru.shishkin.sd.mvc.model.TodoList;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

/**
 * @author shishkin
 */
public class TodoListJdbcDao extends JdbcDaoSupport implements TodoListDao {

    public TodoListJdbcDao(DataSource dataSource) {
        super();
        setDataSource(dataSource);
    }

    @Override
    public int addList(TodoList list) {
        String sql = "INSERT INTO LIST (NAME) VALUES (?)";
        return getJdbcTemplate().update(sql, list.getName());
    }

    @Override
    public List<TodoList> getLists() {
        String sql = "SELECT * FROM LIST";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(TodoList.class));
    }

    @Override
    public Optional<TodoList> getList(int id) {
        String sql = "SELECT * FROM LIST WHERE ID = ?";
        return getJdbcTemplate().query(sql, new Object[]{id}, new BeanPropertyRowMapper(TodoList.class)).stream().findFirst();
    }

}
