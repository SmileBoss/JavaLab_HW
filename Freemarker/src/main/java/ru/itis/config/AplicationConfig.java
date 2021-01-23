package ru.itis.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import ru.itis.repositories.UserRepository;
import ru.itis.repositories.UserRepositoryImpl;
import ru.itis.services.UserService;
import ru.itis.services.UserServiceImpl;

import javax.sql.DataSource;


@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan("ru.itis")
public class AplicationConfig {
    @Autowired
    Environment environment;

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("db.url"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty("db.hikari.max-pool-size")));
        hikariConfig.setUsername(environment.getProperty("db.username"));
        hikariConfig.setPassword(environment.getProperty("db.password"));
        hikariConfig.setDriverClassName(environment.getProperty("db.driver.classname"));
        return hikariConfig;
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public UserRepository userRepository() {return new UserRepositoryImpl(dataSource());
    }

    @Bean
    public UserService userService() {return new UserServiceImpl(userRepository());
    }

}
