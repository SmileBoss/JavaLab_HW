package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    //language=SQL
    private static final String SQL_INSERT_USER = "insert into simple_user(email, password, confirm_code) " +
            "values (?, ?, ?)";

    //language=SQL
    private static final String SQL_UPDATE_USER = "update simple_user set email = ?, password = ?, confirm_code = ?, is_deleted = ? " +
            "where id = ?";

    //language=SQL
    private static final String SQL_FIND_USER_BY_ID = "select * from simple_user where id = ?";

    //language=SQL
    private static final String SQL_FIND_USER_BY_EMAIL = "select * from simple_user where email = ?";

    private JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper<User> rowMapper = (rs, rowNum) -> User.builder()
            .email(rs.getString("email"))
            .password(rs.getString("password"))
            .confirmCode(rs.getString("confirm_code"))
            .id(rs.getLong("id"))
            .isDeleted(rs.getBoolean("is_deleted")).build();

    @Override
    public Optional<User> findOneByEmail(String email) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_USER_BY_EMAIL, rowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(SQL_INSERT_USER, entity.getEmail(), entity.getPassword(), entity.getConfirmCode());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(SQL_UPDATE_USER,
                entity.getEmail(),
                entity.getPassword(),
                entity.getConfirmCode(),
                entity.getId(),
                entity.getIsDeleted());
    }

    @Override
    public Optional<User> findOne(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_USER_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
}
