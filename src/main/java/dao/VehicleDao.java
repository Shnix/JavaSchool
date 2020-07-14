package dao;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class VehicleDao extends AbstractDao<Vehicle> {
    private SessionFactory sessionFactory;


    public VehicleDao() {
        this.sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Vehicle.class)
                .buildSessionFactory();
    }

    @Override
    public void add(Vehicle vehicle) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(vehicle);
        session.getTransaction().commit();
        session.close();
    }



    @Override
    public void delete(Vehicle vehicle) {

    }

    @Override
    public void update(Vehicle vehicle) {

    }

    @Override
    public List<Vehicle> list() {
        return null;
    }

    @Override
    public Vehicle getById(int id) {
        return null;
    }
}
