package dao;


import entity.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityDao extends AbstractDao<City> {


    public City getById(int id){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        City city = (City) session.load(City.class, id);
        session.getTransaction().commit();
        return city;
    }

    public City getByName(String cityName){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from City where name = :param ");
        query.setParameter("param",cityName);
        List<City> cities = query.list();
        return cities.get(0);
    }

}
