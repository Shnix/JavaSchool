package controller;

import dao.CityDao;
import dao.DriverDao;
import entity.City;
import entity.Driver;
import enums.DriverStatus;

public class Main2 {
    public static void main(String[] args) {
        CityDao cityDao = new CityDao();
        City city =cityDao.getByName("Helsinki");
        DriverDao driverDao = new DriverDao();
        Driver driver = driverDao.getById(1);
        driver.setCurrentCity(city);
        driver.setLastName("Popov");
        driver.setStatus(DriverStatus.BUSY);
        driverDao.update(driver);
//        DriverDao driverDao = new DriverDao();
//        Driver driver = driverDao.getById(3);
//        driver.setVehicle();
    }
}
