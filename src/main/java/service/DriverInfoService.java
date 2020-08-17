package service;


import dao.DriverDao;
import dto.DriverInfoDto;
import dtoconverter.Converter;
import dtoconverter.DriverInfoConverter;
import entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DriverInfoService {

    private DriverDao driverDao;

    private Converter<Driver,DriverInfoDto> dtoConverter;

    @Autowired
    public DriverInfoService(DriverDao driverDao, Converter<Driver,DriverInfoDto> dtoConverter) {
        this.driverDao = driverDao;
        this.dtoConverter=dtoConverter;
    }

    public DriverInfoDto getDriverInfo(int id){
        Driver driver = driverDao.getById(id);
        return dtoConverter.convertIntoDto(driver);
    }
}
