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
                      @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
                      @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).*$", message = "A senha deve conter pelo menos uma letra maiúscula e um número")
                      @NotNull
                      String password,
                      @NotNull String name,
                      String socialNumber,
                      String phone,
                      @JsonFormat(pattern = "dd/MM/yyyy")
                      @DateTimeFormat(pattern = "dd/MM/yyyy")
                      LocalDate birthday,
                      UUID countryId,
                      @NotNull
                      @Size(max = 20, message = "O nome de usuário deve ter no máximo 20 caracteres")
                      String username,

                      Boolean twoFactorEnabled,
                      Boolean refreshTokenEnabled,

                      String theme) {
}