package dao;

import entity.Cargo;
import exception.ServiceLayerException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Component
@Transactional
public class CargoDao extends AbstractDao<Cargo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CargoDao.class);

    @Override
    public Cargo getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Cargo cargo = (Cargo) session.get(Cargo.class, id);
        if(cargo == null){
            LOGGER.info("Cargo is null");
            throw new ServiceLayerException("Cargo is null");
        }
        return cargo;
    }

    public Cargo getByName(String cargoName) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Cargo where name = :param ");
        query.setParameter("param", cargoName);
        List<Cargo> cargoes = query.list();
        return cargoes.get(0);
    }
}
