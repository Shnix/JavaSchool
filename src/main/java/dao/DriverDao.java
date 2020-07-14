package dao;

import entity.*;

import org.hibernate.Session;

import java.util.List;

public class DriverDao extends AbstractDao<Driver> {

    @Override
    public void add(Driver driver){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(driver);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Driver driver) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(driver);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Driver driver){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(driver);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Driver> list() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Driver> drivers = session.createQuery("from Driver").list();
        session.getTransaction().commit();
        session.close();
        return drivers;
    }

    @Override
    public Driver getById(int id){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Driver driver = session.get(Driver.class, id);
        session.getTransaction().commit();
        session.close();
        return driver;
    }
}
