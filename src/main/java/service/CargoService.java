package service;

import dao.CargoDao;
import dao.DriverDao;
import dto.OrderDto;
import entity.Cargo;
import entity.Driver;
import enums.CargoStatus;
import exception.ServiceLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CargoService {

    private CargoDao cargoDao;

    private DriverDao driverDao;

    @Autowired
    public CargoService(CargoDao cargoDao, DriverDao driverDao) {
        this.driverDao = driverDao;
        this.cargoDao = cargoDao;
    }

    public Cargo createCargo(OrderDto orderDto) {
        Cargo cargo = new Cargo();
        cargo.setCargoStatus(CargoStatus.PREPARED);
        cargo.setWeight(orderDto.getCargoWeight());
        cargo.setName(orderDto.getCargoName());
        cargoDao.add(cargo);
        return cargo;
    }

    public void updateStatus(String id, String status) {
        Driver driver = driverDao.getById(Integer.parseInt(id));
        if (driver.getVehicle()!= null) {
            Cargo cargo = driver.getVehicle().getCargo();
            String trim = status.replaceAll("\"", "");
            cargo.setCargoStatus(CargoStatus.valueOf(trim.toUpperCase()));
            cargoDao.update(cargo);
        } else {
            throw new ServiceLayerException("You have no cargo");
        }
    }

    public Cargo getByName(String name) {
        return cargoDao.getByName(name);
    }
}
