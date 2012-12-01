package ousl.group4.service;

import ousl.group4.model.Country;

import java.util.List;

public interface CountryManager {

    public List<Country> getAllCountries();

    public Country countryByCountryCode(String countryCode);
}
