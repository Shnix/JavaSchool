package dao;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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

    @Override
    public void add(T t) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(t);
        session.getTransaction().commit();

    }

    @Override
    public void delete(T t) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(t);
        session.getTransaction().commit();

    }

    @Override
    public void update(T t) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(t);
        session.getTransaction().commit();

    }

    @Override
    public List<T> list() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List entities = session.createQuery("from "
                + this.getClass().getSimpleName().replaceAll("Dao","")).list();
        session.getTransaction().commit();
        return entities;
    }
}
