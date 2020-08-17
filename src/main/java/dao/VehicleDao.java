package dao;

import dto.OrderDto;
import entity.*;
import enums.VehicleType;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VehicleDao extends AbstractDao<Vehicle> {

    private CityDao cityDao;

    @Autowired
    public VehicleDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public Vehicle getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Vehicle vehicle = (Vehicle) session.get(Vehicle.class, id);
        session.getTransaction().commit();

        return vehicle;
    }

    public Vehicle getVehicleForOrder(OrderDto orderDto) {

        Session session = this.sessionFactory.getCurrentSession();
        if(!session.getTransaction().isActive())
        session.beginTransaction();
        Query query = session.createQuery("from Vehicle where city = :param1 "
                + "and capacityInTons > :param2 "
                + "and vehicleCondition = :param3 "
                + "and vehicleType = :param4 "
                + "and cargo is null");
        query.setParameter("param1", cityDao.getByName(orderDto.getStart()));
        query.setParameter("param2", orderDto.getCargoWeight());
        query.setParameter("param3", "OK");
        query.setParameter("param4", VehicleType.valueOf(orderDto.getVehicleType().toUpperCase()));
        List<Vehicle> vehicles =  query.list();
        if(vehicles.size()>=1) {
            return vehicles.get(0);
        }
        return new Vehicle();
    }
}
