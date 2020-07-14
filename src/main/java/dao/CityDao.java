package dao;


import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class CityDao extends AbstractDao<City> {

    public void add(City city){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(city);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(City city) {

    }

    @Override
    public void update(City city) {

    }

    @Override
    public List<City> list() {
        return null;
    }

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
