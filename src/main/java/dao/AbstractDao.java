package dao;

import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

abstract class AbstractDao<T> implements Dao<T> {

    SessionFactory sessionFactory;

    AbstractDao() {
        this.sessionFactory =  new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Driver.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Cargo.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Vehicle.class)
                .addAnnotatedClass(Waypoint.class)
                .buildSessionFactory();
    }
}
