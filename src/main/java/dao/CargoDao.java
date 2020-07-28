package dao;

import entity.Cargo;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CargoDao extends AbstractDao<Cargo> {

    @Override
    public Cargo getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Cargo cargo = (Cargo) session.load(Cargo.class, id);
        session.getTransaction().commit();
        return cargo;
    }

    public Cargo getByName(String cargoName){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Cargo where name = :param ");
        query.setParameter("param",cargoName);
        List<Cargo> cargoes = query.list();
        return cargoes.get(0);
    }
}
