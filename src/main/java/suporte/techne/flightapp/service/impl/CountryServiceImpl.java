package suporte.techne.flightapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import suporte.techne.flightapp.domain.country.DTO.CountryReturnDTO;
import suporte.techne.flightapp.domain.country.useCase.GetAllCountries;
import suporte.techne.flightapp.domain.country.useCase.GetCountriesByName;
import suporte.techne.flightapp.service.CountryService;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private GetAllCountries getAllCountries;
    @Autowired
    private GetCountriesByName getCountriesByName;

    @Override
    public Page<CountryReturnDTO> getAllCountries(Pageable pageable) {
        return getAllCountries.getAllCountries(pageable);
    }

    @Override
    public List<CountryReturnDTO> getCountriesByName(List<String> names) {
        return getCountriesByName.getCountriesByName(names);
    }
}
