package suporte.techne.flightapp.service;

import jakarta.servlet.http.HttpServletRequest;
import suporte.techne.flightapp.domain.user.DTO.UserDTO;
import suporte.techne.flightapp.domain.user.DTO.UserLoginDTO;
import suporte.techne.flightapp.domain.user.DTO.UserReturnDTO;
import suporte.techne.flightapp.infra.security.AuthTokensDTO;

public interface AuthService {
    UserReturnDTO createUser(UserDTO data);
    AuthTokensDTO performLogin(UserLoginDTO data, HttpServletRequest request);
}
