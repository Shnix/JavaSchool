package dao;

import entity.Waypoint;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;


@Component
public class WaypointDao extends AbstractDao<Waypoint> {

    @Override
    public Waypoint getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Waypoint waypoint = (Waypoint) session.get(Waypoint.class, id);
        session.getTransaction().commit();

        return waypoint;
    }
}
