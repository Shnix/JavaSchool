package dao;

import dto.OrderDto;
import entity.*;
import enums.VehicleType;
import exception.ServiceLayerException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class VehicleDao extends AbstractDao<Vehicle> {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleDao.class);

    private CityDao cityDao;

    @Autowired
    public VehicleDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public Vehicle getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Vehicle vehicle = (Vehicle) session.get(Vehicle.class, id);
        if (vehicle == null) {
            LOGGER.info("Vehicle is null");
            throw new ServiceLayerException("Vehicle is null");
        }
        return vehicle;
    }

    public Vehicle getVehicleForOrder(OrderDto orderDto) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Vehicle where city = :param1 "
                + "and capacityInTons > :param2 "
                + "and vehicleCondition = :param3 "
                + "and vehicleType = :param4 "
                + "and cargo is null "
                + "and deleted = false");
        query.setParameter("param1", cityDao.getByName(orderDto.getStart()));
        query.setParameter("param2", orderDto.getCargoWeight());
        query.setParameter("param3", "OK");
        query.setParameter("param4", VehicleType.valueOf(orderDto.getVehicleType().toUpperCase()));
        List<Vehicle> vehicles = query.list();
        if (vehicles.size() >= 1) {
            return vehicles.get(0);
        }
        return new Vehicle();
    }
}
