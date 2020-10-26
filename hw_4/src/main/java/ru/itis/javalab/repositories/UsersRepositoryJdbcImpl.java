package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language SQL
    private static final String SQL_FIND_ALL = "select * from student";
    //language SQL
    private static final String SQL_FIND_BY_AGE = "select * from student where age=?";

    RowMapper<User> rowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .build();

    private SimpleJdbcTemplateImpl template;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        template = new SimpleJdbcTemplateImpl(dataSource);
    }

    @Override
    public void save(User entity) {
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return template.query(SQL_FIND_ALL, rowMapper);
    }

    @Override
    public List<User> findAllByAge(int age) {
        return template.query(SQL_FIND_BY_AGE, rowMapper, age);
    }
}
