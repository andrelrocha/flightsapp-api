package suporte.techne.flightapp.service;

import suporte.techne.flightapp.domain.user.DTO.UserDTO;
import suporte.techne.flightapp.domain.user.DTO.UserReturnDTO;

public interface AuthService {
    UserReturnDTO createUser(UserDTO data);
}
