package dao;

import entity.Waypoint;
import org.hibernate.Session;

import java.util.List;

public class WaypointDao extends AbstractDao<Waypoint> {

    @Override
    public Waypoint getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Waypoint waypoint = (Waypoint) session.load(Waypoint.class, id);
        session.getTransaction().commit();
        return waypoint;
    }
}
