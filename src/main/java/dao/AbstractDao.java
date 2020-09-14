package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
abstract class AbstractDao<T> implements Dao<T> {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public void add(T t) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(t);
    }

    @Override
    public void delete(T t) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(t);
    }

    @Override
    public void update(T t) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(t);
    }

    @Override
    public List<T> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List entities = session.createQuery("from "
                + this.getClass().getSimpleName().replaceAll("Dao", "")).list();
        return entities;
    }
}
