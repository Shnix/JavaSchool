package service;

import dao.VehicleDao;
import dto.DtoConverter;
import dto.VehicleDto;
import entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
public class VehicleService {

    private VehicleDao vehicleDao;

    private DtoConverter dtoConverter;

    @Autowired
    public VehicleService(VehicleDao vehicleDao,DtoConverter dtoConverter) {
        this.vehicleDao = vehicleDao;
        this.dtoConverter=dtoConverter;
    }

    @Transactional
    public List<VehicleDto> getAllVehicles(){
        List<Vehicle> vehicles = vehicleDao.list();
        return vehicles.stream().map(o->dtoConverter.convertIntoDto(o))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteVehicle(String id){
        vehicleDao.delete(vehicleDao.getById(Integer.parseInt(id)));
    }

    @Transactional
    public void addVehicle(VehicleDto vehicleDto){
        Vehicle vehicle = dtoConverter.convertFromDto(vehicleDto);
        vehicleDao.add(vehicle);
    }

    @Transactional
    public void update(int id,VehicleDto vehicleDto){
        Vehicle vehicle =  dtoConverter.convertFromDto(vehicleDto);
        vehicle.setId(id);
        vehicleDao.update(vehicle);
    }
}
