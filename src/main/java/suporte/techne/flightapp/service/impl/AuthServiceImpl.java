package suporte.techne.flightapp.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import suporte.techne.flightapp.domain.user.DTO.UserDTO;
import suporte.techne.flightapp.domain.user.DTO.UserLoginDTO;
import suporte.techne.flightapp.domain.user.DTO.UserReturnDTO;
import suporte.techne.flightapp.domain.user.useCase.CreateUser;
import suporte.techne.flightapp.domain.user.useCase.PerformSignIn;
import suporte.techne.flightapp.infra.security.AuthTokensDTO;
import suporte.techne.flightapp.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private CreateUser createUser;
    @Autowired
    private PerformSignIn performSignIn;

    @Override
    public UserReturnDTO createUser(UserDTO data) {
        return createUser.createUser(data);
    }

    @Override
    public AuthTokensDTO performLogin(UserLoginDTO data, HttpServletRequest request) {
        return performSignIn.performLogin(
                data, request);
    }
}
