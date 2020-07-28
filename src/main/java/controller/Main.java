package controller;


import dao.CityDao;
import dao.DriverDao;
import entity.*;
import enums.*;

import java.sql.SQLException;

public class Main {



    public static void main(String[] args) throws SQLException {
        DriverDao driverDao = new DriverDao();
        CityDao cityDao = new CityDao();
        City city = new City("Moscow",54,32,true);
        City city2 = new City("Helsinki",32,12,true);
        Cargo cargo = new Cargo("Food",560, CargoStatus.PREPARED);
        Vehicle vehicle = new Vehicle("Boat007", VehicleType.BOAT, 5,1000, VehicleCondition.OK,city);
        vehicle.setCargo(cargo);
        Order order = new Order(false);
        Waypoint start = new Waypoint(city,cargo, OperationType.LOADING,order);
        Waypoint end = new Waypoint(city2,cargo, OperationType.UNLOADING,order);

            Driver driver = new Driver("Sergey", "Ivanov", DriverType.PILOT, 0, DriverStatus.REST, cityDao.getByName("Moscow"));
        driverDao.add(driver);
        driverDao.add(driver);
        driverDao.add(driver);



    }

}
