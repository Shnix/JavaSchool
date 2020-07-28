package service;

import dao.CityDao;
import dao.DriverDao;
import dto.DriverDto;
import dto.DtoConverter;
import entity.Driver;
import enums.DriverStatus;
import enums.DriverType;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
public class DriverService {

    private DriverDao driverDao;

    private CityDao cityDao;

    private DtoConverter dtoConverter;

    @Autowired
    public DriverService(DriverDao driverDao, CityDao cityDao,DtoConverter dtoConverter) {
        this.cityDao=cityDao;
        this.driverDao = driverDao;
        this.dtoConverter=dtoConverter;
    }

    @Transactional
    public List<DriverDto> getAllDrivers(){
        List<Driver> drivers = driverDao.list();
        return drivers.stream().map(o->dtoConverter.convertIntoDto(o))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteDriver(String id){
        driverDao.delete(driverDao.getById(Integer.parseInt(id)));
    }

    @Transactional
    public void addDriver(DriverDto driver){
        Driver driver1 = dtoConverter.convertFromDto(driver);
        System.out.println(driver1);
        driverDao.add(driver1);
    }

    @Transactional
    public void update(int id,DriverDto driverDto){
        Driver driver = dtoConverter.convertFromDto(driverDto);
        driver.setId(id);
        driverDao.update(driver);
    }




}
