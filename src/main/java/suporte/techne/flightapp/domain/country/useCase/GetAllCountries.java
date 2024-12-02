package suporte.techne.flightapp.domain.country.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import suporte.techne.flightapp.domain.country.CountryRepository;
import suporte.techne.flightapp.domain.country.DTO.CountryReturnDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllCountries {
    @Autowired
    private CountryRepository repository;

    public Page<CountryReturnDTO> getAllCountries(Pageable pageable) {
        return repository.findAllCountriesOrderedByName(pageable).map(CountryReturnDTO::new);
    }
}