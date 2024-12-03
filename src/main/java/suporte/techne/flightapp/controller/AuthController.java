package suporte.techne.flightapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import suporte.techne.flightapp.domain.user.DTO.UserDTO;
import suporte.techne.flightapp.domain.user.DTO.UserReturnDTO;
import suporte.techne.flightapp.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserReturnDTO> signUp(@RequestBody UserDTO data) {
        var returnData = authService.createUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnData);
    }
}
