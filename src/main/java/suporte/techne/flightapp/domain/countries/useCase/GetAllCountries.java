package suporte.techne.flightapp.domain.countries.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import suporte.techne.flightapp.domain.countries.CountryRepository;
import suporte.techne.flightapp.domain.countries.DTO.CountryReturnDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllCountries {
    @Autowired
    private CountryRepository repository;

    public Page<CountryReturnDTO> getAllCountries(Pageable pageable) {
        System.out.println("get all countries chamado");
        List<String> countriesName = new ArrayList<>();
        countriesName.add("Brazil");
        countriesName.add("Angola");

        System.out.println(countriesName);

        System.out.println("sem query própria: "+repository.findAll());

        System.out.println(repository.findAllByNamesIgnoreCaseAndTrimmed(countriesName));

        return repository.findAllCountriesOrderedByName(pageable).map(CountryReturnDTO::new);
    }
}