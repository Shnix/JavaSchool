package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
abstract class AbstractDao<T> implements Dao<T> {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public void add(T t) {
        Session session = this.sessionFactory.getCurrentSession();
        if(!session.getTransaction().isActive())
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
        if (!session.getTransaction().isActive())
            session.beginTransaction();
        session.update(t);
        session.getTransaction().commit();

    }

    @Override
    public List<T> list() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List entities = session.createQuery("from "
                + this.getClass().getSimpleName().replaceAll("Dao", "")).list();
        session.getTransaction().commit();

        return entities;
    }
}
