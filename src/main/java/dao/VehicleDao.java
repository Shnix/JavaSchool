package dao;

import entity.*;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class VehicleDao extends AbstractDao<Vehicle> {

    @Override
    public Vehicle getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Vehicle vehicle = (Vehicle) session.load(Vehicle.class, id);
        session.getTransaction().commit();
        return vehicle;
    }
}
