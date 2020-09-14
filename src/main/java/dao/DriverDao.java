package dao;

import dto.OrderDto;
import entity.*;

import enums.DriverType;
import exception.ServiceLayerException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import util.WorkingHoursCalculator;

import java.util.List;
import java.util.stream.Collectors;


@Component
@Transactional
public class DriverDao extends AbstractDao<Driver> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverDao.class);

    private static final int MAX_WORKING_HOURS = 178;

    private CityDao cityDao;

    private WorkingHoursCalculator workingHoursCalculator;

    @Autowired
    public DriverDao(CityDao cityDao, WorkingHoursCalculator workingHoursCalculator) {
        this.workingHoursCalculator = workingHoursCalculator;
        this.cityDao = cityDao;
    }

    @Override
    public Driver getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Driver driver = session.get(Driver.class, id);
        if (driver == null) {
            LOGGER.info("Driver is null");
            throw new ServiceLayerException("Driver is null");
        }
        return driver;
    }

    public List<Driver> selectDrivers(OrderDto orderDto) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Driver where city = :param1 " +
                "and driverType = :param2 " +
                "and workingHours < :param3 " +
                "and driverStatus = :param4 " +
                "and deleted = false");
        query.setParameter("param1", cityDao.getByName(orderDto.getStart()));
        query.setParameter("param2", checkDriverType(orderDto));
        query.setParameter("param3", workingHours(orderDto));
        query.setParameter("param4", "REST");
        List<Driver> drivers = query.list();

        return drivers.stream()
                .filter(o -> o.getOrder() == null)
                .collect(Collectors.toList());
    }

    private int workingHours(OrderDto orderDto) {
        return MAX_WORKING_HOURS - workingHoursCalculator.calculateWorkingHours(orderDto);
    }

    private DriverType checkDriverType(OrderDto orderDto) {
        return orderDto.getVehicleType().toUpperCase().equals("PLAIN")
                ? DriverType.PILOT : DriverType.SAILOR;
    }

}
