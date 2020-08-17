package dao;


import entity.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;


@Component
public class CityDao extends AbstractDao<City> {


    public City getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        City city = (City) session.get(City.class, id);
        session.getTransaction().commit();
        return city;
    }

    public City getByName(String cityName) {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            if(!session.getTransaction().isActive())
            session.beginTransaction();
            Query query = session.createQuery("from City where name = :param ");
            query.setParameter("param", cityName);
            City city = (City) query.getSingleResult();
            return city;
        } catch (NoResultException e) {
            throw new RuntimeException("Only Europe capital Cities are available.");
        }
    }

}
