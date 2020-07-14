package config;

import entity.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.DriverManager;

@Configuration
public class Config {

    @Bean
    public SessionFactory sessionFactory(){
        SessionFactory factory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Driver.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Cargo.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Vehicle.class)
                .addAnnotatedClass(Waypoint.class)
                .buildSessionFactory();
        return factory;
    }
}
