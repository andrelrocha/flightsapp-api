package suporte.techne.flightapp.domain.user.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

public record UserDTO(@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "O endereço de e-mail não é válido")
                      @NotNull
                      String login,
                      @Size(min = 8, message = "Password must have at least 8 characters")
                      @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).*$", message = "The password must have at least one upper letter and a number")
                      @NotNull
                      String password,
                      @NotNull String name,
                      String socialNumber,
                      String phone,
                      @JsonFormat(pattern = "yyyy-MM-dd")
                      @DateTimeFormat(pattern = "yyyy-MM-dd")
                      LocalDate birthday,
                      UUID countryId,
                      @NotNull
                      @Size(max = 20, message = "Username max length is 20 characters")
                      String username,
                      Boolean twoFactorEnabled,
                      Boolean refreshTokenEnabled,
                      String theme) {
}
