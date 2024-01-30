package ba.edu.ibu.webengineeringproject.rest.controllers;

import ba.edu.ibu.webengineeringproject.core.service.AuthService;
import ba.edu.ibu.webengineeringproject.rest.dto.LoginDTO;
import ba.edu.ibu.webengineeringproject.rest.dto.LoginRequestDTO;
import ba.edu.ibu.webengineeringproject.rest.dto.UserDTO;
import ba.edu.ibu.webengineeringproject.rest.dto.UserRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://65ad855a7f6077a721dc046c--tangerine-haupia-e1cfff.netlify.app", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/get-me")
    public ResponseEntity<UserDTO> getMe(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || authorizationHeader.isEmpty()) {
            return ResponseEntity.internalServerError().body(null);
        }

        return ResponseEntity.ok(authService.getMe(authorizationHeader));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserRequestDTO user) {
        return ResponseEntity.ok(authService.signUp(user));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public ResponseEntity<LoginDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        return ResponseEntity.ok(authService.signIn(loginRequest));
    }
}