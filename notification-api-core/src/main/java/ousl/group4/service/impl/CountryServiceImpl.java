package ousl.group4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ousl.group4.dao.CountryDao;
import ousl.group4.model.Country;
import ousl.group4.service.CountryService;

import java.util.List;

@Service("countryService")
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;

    @Transactional
    public List<Country> getAllCountries() {
        return countryDao.getAllCountries();
    }

    @Transactional
    public Country countryByCountryCode(String countryCode) {
        return countryDao.countryByCountryCode(countryCode);
    }

}
