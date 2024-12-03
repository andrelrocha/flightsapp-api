package suporte.techne.flightapp.domain.user.DTO;

import suporte.techne.flightapp.domain.user.User;
import suporte.techne.flightapp.domain.user.UserRole;

import java.time.LocalDate;

public record UserReturnDTO(String id,
                            String login,
                            String username,
                            String name,
                            String socialNumber,
                            String phone,
                            LocalDate birthday,
                            String theme,
                            String countryName,
                            String countryId,
                            Boolean twoFactorEnabled,
                            Boolean refreshTokenEnabled,
                            UserRole role) {

    public UserReturnDTO(User user) {
        this(user.getId().toString(), user.getLogin(), user.getUsername(), user.getName(), user.getSocialNumber(), user.getPhone(),
                user.getBirthday(), String.valueOf(user.getTheme()), user.getCountry().getName(), user.getCountry().getId().toString(),
                user.isTwoFactorEnabled(), user.isRefreshTokenEnabled(), user.getRole());
    }
}
