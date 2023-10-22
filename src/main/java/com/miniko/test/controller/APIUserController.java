package com.miniko.test.controller;

import com.miniko.test.entities.user.*;
import com.miniko.test.entities.user.UserDTO;
import com.miniko.test.security.TokenService;
import com.miniko.test.service.UserService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class APIUserController {
    private final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    public APIUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/email-used")
    public boolean verifyEmailUsed(@RequestParam String email) {
        return userService.findUserByEmail(email) != null;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO data) {
        try {
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            Authentication auth = authenticationManager.authenticate(usernamePassword);
            String token = tokenService.generateToken((User) auth.getPrincipal());

            UserDTO userDTO = new UserDTO(this.userService.findUserByEmail(data.email()).getName(), token);
            return ResponseEntity.ok(userDTO);
        }
        catch (Exception error) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email and password do not match any account");
        }
    }

    @PostMapping("/registry")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        try {
            if(this.userService.findUserByEmail(data.email()) != null)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already used");

            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            User newUser = new User(data.name(), data.email(), encryptedPassword, UserRole.USER);

            this.userService.createUser(newUser);
            String token = tokenService.generateToken(newUser);

            return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(newUser.getName(), token));
        }
        catch(Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid parameters");
        }
    }
}
