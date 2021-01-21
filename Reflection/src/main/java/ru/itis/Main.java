package ru.itis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) {
        DataSource dataSource = null;
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ru.itis.ApplicationConfig.class);
        dataSource = applicationContext.getBean(DataSource.class);

        EntityManager manager = new EntityManager(dataSource);
        manager.createTable("account", User.class);
        System.out.println("-----------------");

        User user = new User(1L, "Nikita", "SHIRMANOV", true);
        manager.save("account", user);

        manager.findById("account", User.class, Long.class, 1L);

    }
}
