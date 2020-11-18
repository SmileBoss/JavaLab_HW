package ru.itis;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.lang.reflect.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class EntityManager {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;
    private DataSource dataSource;


    public EntityManager(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;

    }

    // createTable("account", ru.itis.User.class);
    public <T> void createTable(String tableName, Class<T> entityClass) {
        StringBuilder SQL_CREATE_TABLE =
                new StringBuilder("create table " + tableName + " ( ");
        // сгенерировать CREATE TABLE на основе класса
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            if ("id".equals(field.getName())) {
                SQL_CREATE_TABLE.append("id serial primary key, ");
            }
            if ("String".equals(field.getType().getSimpleName())) {
                SQL_CREATE_TABLE.append(field.getName());
                SQL_CREATE_TABLE.append(" varchar(255), ");
            }
            if ("boolean".equals(field.getType().getSimpleName())) {
                SQL_CREATE_TABLE.append(field.getName());
                SQL_CREATE_TABLE.append(" boolean, ");
            }
        }
        SQL_CREATE_TABLE.deleteCharAt(SQL_CREATE_TABLE.length() - 2);
        SQL_CREATE_TABLE.append(");");
        System.out.println(SQL_CREATE_TABLE.toString());
        // create table account ( id integer, firstName varchar(255), ...))
        jdbcTemplate.update(SQL_CREATE_TABLE.toString());

    }

    public void save(String tableName, Object entity) {
        Class<?> classOfEntity = entity.getClass();
        StringBuilder SQL_SAVE_OBJECT =
                new StringBuilder("insert into " + tableName + " (");
        // сканируем его поля
        Field[] fields = classOfEntity.getDeclaredFields();
        for (Field field : fields) {
            if (!"id".equals(field.getName())) {
                SQL_SAVE_OBJECT.append(field.getName());
                SQL_SAVE_OBJECT.append(", ");
            }
        }
        SQL_SAVE_OBJECT.deleteCharAt(SQL_SAVE_OBJECT.length() - 2);
        SQL_SAVE_OBJECT.append(") values( ");
        // сканируем значения этих полей
        for (Field field : fields) {
            field.setAccessible(true);
            if (!"id".equals(field.getName())) {
                SQL_SAVE_OBJECT.append("?, ");
            }
        }
        SQL_SAVE_OBJECT.deleteCharAt(SQL_SAVE_OBJECT.length() - 2);
        SQL_SAVE_OBJECT.append(");");
        System.out.println(SQL_SAVE_OBJECT.toString());
        // генерируем insert into
        Map<String, Object> parameters = new HashMap<>();
        String id;
        for (Field field : fields) {
            if (!"id".equals(field.getName())) {
                try {
                    parameters.put(field.getName(), field.get(entity));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(tableName).usingGeneratedKeyColumns("id");
        simpleJdbcInsert.execute(parameters);

    }

    // ru.itis.User user = entityManager.findById("account", ru.itis.User.class, Long.class, 10L);
    public <T, ID> T findById(String tableName, Class<T> resultType, Class<ID> idType, ID idValue) {
        StringBuilder SQL_FIND_BY_ID = new StringBuilder
                ("select * from "
                        + tableName
                        + " where id = "
                        + idValue);
        Field[] fields = resultType.getDeclaredFields();
        Class<?>[] types = new Class<?>[fields.length];
        for (int i = 0; i < fields.length; i++) {
            types[i] = fields[i].getType();
        }

        resultType.getClassLoader();

        Object[] variables = new Object[fields.length];

        try {
            PreparedStatement pStatement = dataSource.getConnection().prepareStatement(SQL_FIND_BY_ID.toString());
            ResultSet resultSet = pStatement.executeQuery();
            Field[] f = resultType.getDeclaredFields();
            if (resultSet.next()){
                for (int i = 0; i < f.length; i++) {
                    variables[i] = resultSet.getObject(i+1);
                }
            }
            System.out.println(Arrays.toString(variables));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try{
            Constructor<?> constructor = resultType.getConstructor(types);
            Object obj = constructor.newInstance(variables);
            return (T) obj;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
