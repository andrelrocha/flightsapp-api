package suporte.techne.flightapp.domain.user.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import suporte.techne.flightapp.domain.country.Country;
import suporte.techne.flightapp.domain.country.CountryRepository;
import suporte.techne.flightapp.domain.user.DTO.UserCreateDTO;
import suporte.techne.flightapp.domain.user.DTO.UserDTO;
import suporte.techne.flightapp.domain.user.DTO.UserReturnDTO;
import suporte.techne.flightapp.domain.user.User;
import suporte.techne.flightapp.domain.user.UserRepository;
import suporte.techne.flightapp.infra.exceptions.ValidationException;

@Component
public class CreateUser {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserReturnDTO createUser(UserDTO data) {
        boolean userExists = userRepository.userExistsByLogin(data.login());

        if (userExists) {
            throw new ValidationException("Email on user creation already exists in our database");
        }

        var country = countryRepository.findById(data.countryId())
                .orElseThrow(() -> new ValidationException("No country was found for the informed ID."));

        var newUser = createUserEntity(data, country);

        var encodedPassword = bCryptPasswordEncoder.encode(data.password());
        newUser.setPassword(encodedPassword);

        var userOnDb = userRepository.save(newUser);

        return new UserReturnDTO(userOnDb);
    }

    private static User createUserEntity(UserDTO data, Country country) {
        var updatedData = new UserDTO(
                data.login(),
                data.password(),
                data.name(),
                data.socialNumber(),
                data.phone(),
                data.birthday(),
                data.countryId(),
                data.username(),
                data.twoFactorEnabled(),
                data.refreshTokenEnabled(),
                data.theme()
        );

        var createDTO = new UserCreateDTO(updatedData, country);

        return new User(createDTO);
    }

}
