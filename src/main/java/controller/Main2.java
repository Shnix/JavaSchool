package controller;

import dao.CityDao;
import dao.DriverDao;
import dao.VehicleDao;
import entity.City;
import entity.Driver;
import entity.Vehicle;
import enums.DriverStatus;
import enums.VehicleCondition;
import enums.VehicleType;

public class Main2 {
    public static void main(String[] args) {
        DriverDao driverDao = new DriverDao();
        Driver driver = driverDao.getById(37);
        CityDao cityDao = new CityDao();
        Vehicle vehicle = new Vehicle("AS64532", VehicleType.PLAIN,1,20, VehicleCondition.OK,cityDao.getByName("Helsinki"));
        new VehicleDao().add(vehicle);
        driver.setVehicle(vehicle);
        driverDao.update(driver);
//        DriverDao driverDao = new DriverDao();
//        Driver driver = driverDao.getById(3);
//        driver.setVehicle();
    }
}
