package suporte.techne.flightapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import suporte.techne.flightapp.domain.user.DTO.UserDTO;
import suporte.techne.flightapp.domain.user.DTO.UserLoginDTO;
import suporte.techne.flightapp.domain.user.DTO.UserReturnDTO;
import suporte.techne.flightapp.infra.security.AccessTokenDTO;
import suporte.techne.flightapp.infra.security.AuthTokensDTO;
import suporte.techne.flightapp.infra.utils.httpCookies.CookieManager;
import suporte.techne.flightapp.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private CookieManager cookieManager;

    @PostMapping("/signin")
    public ResponseEntity<AccessTokenDTO> performLogin(@RequestBody @Valid UserLoginDTO data, HttpServletResponse response, HttpServletRequest request) {
        AuthTokensDTO tokensJwt = authService.performLogin(data, request);
        if (tokensJwt.refreshToken() != null) {
            cookieManager.addRefreshTokenCookie(response, tokensJwt.refreshToken());
        }
        AccessTokenDTO accessTokenDto = new AccessTokenDTO(tokensJwt.accessToken());
        return ResponseEntity.ok(accessTokenDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserReturnDTO> signUp(@RequestBody @Valid UserDTO data) {
        var returnData = authService.createUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnData);
    }


}
