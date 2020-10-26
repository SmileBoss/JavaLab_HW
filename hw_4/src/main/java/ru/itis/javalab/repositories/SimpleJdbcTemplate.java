package ru.itis.javalab.repositories;

import java.util.List;

public interface SimpleJdbcTemplate {
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object ...args);
}
