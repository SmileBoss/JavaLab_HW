package ru.itis.javalab.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.itis.javalab.models.CookieUser;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class CookieRepositoryImpl implements CookieRepository {

    // language=SQL
    private static final String SQL_SELECT_BY_AUTH = "select * from cookie where auth = ?";

    // language=SQL
    private static final String SQL_INSERT_USER_COOKIE = "insert into cookie(user_id, auth) values(?, ?)";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from cookie where user_id = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from cookie";

    //language=SQL
    private static final String SQL_UPDATE_ALL = "update cookie set auth = ? where user_id = ?";

    //language=SQL
    private static final String SQL_DELETE = "delete from cookie where user_id = ?";

    RowMapper<CookieUser> cookieUserRowMapper = (row, i) -> CookieUser.builder()
            .user_id(row.getInt("user_id"))
            .auth(row.getString("auth")).build();

    JdbcTemplate jdbcTemplate;
    SimpleJdbcInsert simpleJdbcInsert;

    public CookieRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("cookie").usingColumns("user_id", "auth");
    }


    @Override
    public void save(CookieUser entity) {
        jdbcTemplate.update(SQL_INSERT_USER_COOKIE, entity.getUser_id(), entity.getAuth());
    }

    @Override
    public void update(CookieUser entity) {
        jdbcTemplate.update(SQL_UPDATE_ALL,
                entity.getAuth(),
                entity.getUser_id());
    }

    @Override
    public void delete(CookieUser entity) {
        deleteById(entity.getUser_id());
    }

    @Override
    public List<CookieUser> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, cookieUserRowMapper);
    }

    @Override
    public Optional<CookieUser> findById(Integer id) {
       try {
           return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, cookieUserRowMapper, id));
       } catch (EmptyResultDataAccessException e){
           return Optional.empty();
       }
    }

    @Override
    public Optional<CookieUser> findByAuth(String auth) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_AUTH, cookieUserRowMapper, auth));
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update(SQL_SELECT_BY_ID, id);
    }
}
