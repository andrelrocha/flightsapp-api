package suporte.techne.flightapp.domain.user.DTO;

import suporte.techne.flightapp.domain.country.Country;

public record UserCreateDTO(UserDTO data, Country country) {
}
