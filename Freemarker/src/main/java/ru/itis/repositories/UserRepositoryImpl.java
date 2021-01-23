package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from \"user\"";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from \"user\" where id = ?";

    //language=SQL
    private static final String SQL_UPDATE_ALL = "update \"user\" set email = ?, firstname = ?, lastname = ? where id = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL = "select * from \"user\" where email = ?";

    //language=SQL
    private static final String SQL_DELETE_BY_ID = "delete from \"user\" where id = ?";


    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .usingGeneratedKeyColumns("id")
                .withTableName("\"user\"")
                .usingColumns("firstname", "email", "lastname");
    }

    RowMapper<User> userMapper = (row, i) -> User
            .builder()
            .id(row.getInt("id"))
            .email(row.getString("email"))
            .firstname(row.getString("firstname"))
            .lastname(row.getString("lastname"))
            .build();


    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, userMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userMapper);
    }

    @Override
    public User findById(Integer id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userMapper, id);
    }


    @Override
    public void save(User entity) {
        Map<String, Object> param = new HashMap<>(3);
        param.put("email", entity.getEmail());
        param.put("firstname", entity.getFirstname());
        param.put("lastname", entity.getLastname());
        entity.setId((Integer) simpleJdbcInsert.executeAndReturnKey(param));
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(SQL_UPDATE_ALL,
                entity.getEmail(),
                entity.getFirstname(),
                entity.getLastname(),
                entity.getId());
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public void delete(User entity) {

    }
}
