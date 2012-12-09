package ousl.group4.service;

import ousl.group4.model.Country;

import java.util.List;

public interface CountryService {

    public List<Country> getAllCountries();

    public Country countryByCountryCode(String countryCode);
}
