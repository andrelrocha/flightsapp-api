package suporte.techne.flightapp.domain.user.useCase;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import suporte.techne.flightapp.domain.auditLog.LoginStatus;
import suporte.techne.flightapp.domain.user.DTO.UserLoginDTO;
import suporte.techne.flightapp.domain.auditLog.useCase.RegisterAuditLog;
import suporte.techne.flightapp.domain.user.User;
import suporte.techne.flightapp.infra.security.AuthTokensDTO;
import suporte.techne.flightapp.infra.security.TokenService;

@Component
public class PerformSignIn {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RegisterAuditLog registerAuditLog;
    @Autowired
    private UpdateUserFailedLogin updateUserFailedLogin;

    @Transactional
    public AuthTokensDTO performLogin(UserLoginDTO data, HttpServletRequest request) {
        if (data.login().isEmpty() || data.password().isEmpty()) {
            throw new IllegalArgumentException("Login and password can't be empty.");
        }

        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());

        try {
            Authentication authentication = manager.authenticate(authenticationToken);

            User userAuthenticated = (User) authentication.getPrincipal();

            userAuthenticated.resetAccessCount();

            String accessToken = tokenService.generateAccessToken(userAuthenticated);

            String refreshToken = null;
            if (userAuthenticated.isRefreshTokenEnabled()) {
                refreshToken = tokenService.generateRefreshToken(userAuthenticated);
            }

            registerAuditLog.logLogin(
                    data.login(),
                    request,
                    LoginStatus.SUCCESS,
                    request.getHeader("User-Agent")
            );

            return new AuthTokensDTO(accessToken, refreshToken);
        } catch (BadCredentialsException e) {
            handleFailedLogin(data.login(), request);
            throw new BadCredentialsException("Wrong login or password.");
        }
    }

    @Transactional
    private void handleFailedLogin(String login, HttpServletRequest request) {
        updateUserFailedLogin.updateFailedLogin(login);

        registerAuditLog.logLogin(
                login,
                request,
                LoginStatus.FAILURE,
                request.getHeader("User-Agent")
        );
    }
}
