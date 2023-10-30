package com.miniko.test.controller;

import com.miniko.test.entities.user.*;
import com.miniko.test.security.TokenService;
import com.miniko.test.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
    public ResponseEntity login(@RequestBody @Valid LoginDTO data, HttpSession httpSession) {
        try {
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            Authentication auth = authenticationManager.authenticate(usernamePassword);
            String token = tokenService.generateToken((User) auth.getPrincipal());

            User user = userService.findUserByEmail(data.email());

            UserDTO userDTO = new UserDTO(user, token);
            httpSession.setAttribute("user", userDTO);
            return ResponseEntity.ok(userDTO);
        }
        catch (Exception error) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email and password do not match any account");
        }
    }

    @PostMapping("/registry")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data, HttpSession httpSession) {
        try {
            if(this.userService.findUserByEmail(data.email()) != null)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already used");

            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            User newUser = new User(data.name(), data.email(), encryptedPassword, UserRole.USER);

            this.userService.createUser(newUser);
            String token = tokenService.generateToken(newUser);

            UserDTO userDTO = new UserDTO(newUser, token);


            httpSession.setAttribute("user", userDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
        }
        catch(Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid parameters");
        }
    }

    @PostMapping("/change-avatar")
    public ResponseEntity changeAvatar(@RequestBody AvatarDTO avatar, HttpSession httpSession) {
        UserDTO userDTO = (UserDTO) httpSession.getAttribute("user");
        if(userDTO == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        String email = tokenService.validateToken(userDTO.token());

        if(email == "") {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }

        User user = userService.findUserByEmail(email);

        System.out.println(user + " | " + email);

        user.setAvatar(avatar.avatar());
        userService.updateUser(user.getId(), user);

        return ResponseEntity.ok(user);
    }
}
