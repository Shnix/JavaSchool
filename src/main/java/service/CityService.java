package service;

import dao.CityDao;
import entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CityService {

    private CityDao cityDao;

    @Autowired
    public CityService(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public City getByName(String name) {
        return cityDao.getByName(name);
    }
}
