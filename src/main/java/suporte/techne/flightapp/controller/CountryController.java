package suporte.techne.flightapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import suporte.techne.flightapp.domain.country.DTO.CountryReturnDTO;
import suporte.techne.flightapp.service.CountryService;

@RestController
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("/all")
    public ResponseEntity<Page<CountryReturnDTO>> getAllCountriesPageable (@RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "240") int size,
                                                                           @RequestParam(defaultValue = "name") String sortField,
                                                                           @RequestParam(defaultValue = "asc") String sortOrder) {
        var pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortField));
        var countriesPageable = countryService.getAllCountries(pageable);
        return ResponseEntity.ok(countriesPageable);
    }
}
