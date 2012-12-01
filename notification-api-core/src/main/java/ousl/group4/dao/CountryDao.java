package ousl.group4.dao;

import ousl.group4.model.Country;

import java.util.List;

public interface CountryDao {

    public List<Country> getAllCountries();

    public Country countryByCountryCode(String countryCode);
}
