package suporte.techne.flightapp.domain.countries.DTO;

import suporte.techne.flightapp.domain.countries.Country;

import java.util.UUID;

public record CountryReturnDTO(UUID id, String name, String isoCode) {
    public CountryReturnDTO(Country country) {
        this(country.getId(), country.getName(), country.getIsoCode());
    }
}