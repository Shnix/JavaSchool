package dao;

import entity.*;

import org.hibernate.Session;
import org.springframework.stereotype.Component;


@Component
public class DriverDao extends AbstractDao<Driver> {

    @Override
    public Driver getById(int id){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Driver driver = session.get(Driver.class, id);
        session.getTransaction().commit();
        return driver;
    }
}
