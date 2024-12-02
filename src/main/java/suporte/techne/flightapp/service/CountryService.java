package suporte.techne.flightapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import suporte.techne.flightapp.domain.countries.DTO.CountryReturnDTO;

import java.util.List;

public interface CountryService {
    Page<CountryReturnDTO> getAllCountries(Pageable pageable);
    List<CountryReturnDTO> getCountriesByName(List<String> names);
}
