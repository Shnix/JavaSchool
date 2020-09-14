package dao;


import entity.*;
import exception.ServiceLayerException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;


@Component
@Transactional
public class CityDao extends AbstractDao<City> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityDao.class);

    public City getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        City city = (City) session.get(City.class, id);
        if (city == null) {
            LOGGER.info("City is null");
            throw new ServiceLayerException("City is null");
        }
        return city;
    }

    public City getByName(String cityName) {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            Query query = session.createQuery("from City where name = :param ");
            query.setParameter("param", cityName);
            City city = (City) query.getSingleResult();
            return city;
        } catch (NoResultException e) {
            throw new ServiceLayerException("Only Europe capital Cities are available.");
        }
    }

}
