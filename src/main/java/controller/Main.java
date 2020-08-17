package controller;


import dao.CityDao;
import dao.DriverDao;
import dao.OrderDao;
import dao.VehicleDao;
import dto.OrderDto;
import dtoconverter.OrderConverter;
import entity.*;
import enums.*;
import service.OrderService;
import service.VehicleService;
import util.DistanceCalculator;

import java.sql.SQLException;

public class Main {



    public static void main(String[] args) throws SQLException {
//            DriverDao driverDao = new DriverDao();
//            VehicleDao vehicleDao = new VehicleDao();
//            vehicleDao.delete(vehicleDao.getById(9));
//            Driver driver1 = driverDao.getById(27);
//            Driver driver2 = driverDao.getById(34);
//            driver1.setOrder(null);
//            driver2.setOrder(null);
//            driverDao.update(driver1);
//            driverDao.update(driver2);
//            OrderDao orderDao = new OrderDao();
//            Order byId = orderDao.getById(15);
//            byId.getVehicle().setCargo(null);
//            vehicleDao.update(byId.getVehicle());
//            Waypoint[] waypoints =byId.getWaypoints().toArray(new Waypoint[0]);
//                System.out.println(waypoints[0].getCity().getName());
//                System.out.println(waypoints[1].getCity().getName());
//        System.out.println(orderDao.list());
//        orderDao.delete
//        (byId);
           CityDao cityDao = new CityDao();
           City city = cityDao.getByName("Moscow");
           City city2 = cityDao.getByName("Helsinki");
           Double d= DistanceCalculator.calculateDistance(city,city2);
        System.out.println(d);
//        city.setLatitude(34.545);
//        cityDao.update(city);
//        OrderDao orderDao = new OrderDao();
//        OrderDto orderDto = new OrderConverter().convertIntoDto(orderDao.getById(15));
//        System.out.println(orderDto.getStart());
//        System.out.println(new VehicleDao(new CityDao()).getVehicleForOrder(orderDto).getName());

    }

}
