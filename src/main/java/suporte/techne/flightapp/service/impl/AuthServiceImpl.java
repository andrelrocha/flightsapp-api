package suporte.techne.flightapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import suporte.techne.flightapp.domain.user.DTO.UserDTO;
import suporte.techne.flightapp.domain.user.DTO.UserReturnDTO;
import suporte.techne.flightapp.domain.user.useCase.CreateUser;
import suporte.techne.flightapp.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private CreateUser createUser;

    @Override
    public UserReturnDTO createUser(UserDTO data) {
        return createUser.createUser(data);
    }
}
