package suporte.techne.flightapp.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import suporte.techne.flightapp.domain.user.User;
import suporte.techne.flightapp.domain.user.UserRepository;

@Component
public class AuthenticateUserWithValidJwt {
    @Autowired
    private UserRepository userRepository;

    public User findUserAuthenticated(String login) {
        return (User) userRepository.findByLogin(login);
    }

}
