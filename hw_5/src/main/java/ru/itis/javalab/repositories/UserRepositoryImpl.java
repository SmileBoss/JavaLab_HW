package ru.itis.javalab.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    SimpleJdbcInsert simpleJdbcInsert;

    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("users").usingColumns("email", "nickname", "password").usingGeneratedKeyColumns("id_user");
    }

    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL = "select * from users where email = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL_AND_PASSWORD = "select * from users where email = ? and password = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from users";

    //language=SQL
    private static final String SQL_UPDATE_ALL = "update users set email = ?, nickname = ?, password = ? where id_user = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_AUTH = "select u.* from users u join cookie c on u.id_user = c.user_id where c.auth = ?";


    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from users where id_user = ?";

    //language=SQL
    private static final String SQL_INSERT = "insert into users(email, nickname, password) " +
            "values (?, ?, ?)";

    RowMapper<User> userRowMapper = (row, i) -> User.builder()
            .id(row.getInt("id_user"))
            .email(row.getString("email"))
            .nickname(row.getString("nickname"))
            .password(row.getString("password"))
            .build();

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL_AND_PASSWORD, userRowMapper, email, password));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, userRowMapper, email));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByAuth(String auth) {
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_AUTH, userRowMapper, auth));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(User entity) {
        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("email", entity.getEmail());
        parameters.put("nickname", entity.getNickname());
        parameters.put("password", entity.getPassword());
        entity.setId((Integer) simpleJdbcInsert.executeAndReturnKey(parameters));
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(SQL_UPDATE_ALL,
                entity.getEmail(),
                entity.getNickname(),
                entity.getPassword(),
                entity.getId());
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public Optional<User> findById(Integer id) {
       try {
           return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id));
       }catch (EmptyResultDataAccessException e){
           return Optional.empty();
       }
    }
}
