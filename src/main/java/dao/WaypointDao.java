package dao;

import entity.Waypoint;
import exception.ServiceLayerException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class WaypointDao extends AbstractDao<Waypoint> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaypointDao.class);

    @Override
    public Waypoint getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Waypoint waypoint = (Waypoint) session.get(Waypoint.class, id);
        if (waypoint == null) {
            LOGGER.info("Waypoint is null");
            throw new ServiceLayerException("Waypoint is null");
        }
        return waypoint;
    }
}
