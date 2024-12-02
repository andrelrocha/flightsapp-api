package suporte.techne.flightapp.domain.country.DTO;

import suporte.techne.flightapp.domain.country.Country;

import java.util.UUID;

public record CountryReturnDTO(UUID id, String name, String isoCode) {
    public CountryReturnDTO(Country country) {
        this(country.getId(), country.getName(), country.getIsoCode());
    }
}