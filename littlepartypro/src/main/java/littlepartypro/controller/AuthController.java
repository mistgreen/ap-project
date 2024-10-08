package littlepartypro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import littlepartypro.dto.LoginDto;
import littlepartypro.dto.RegisterDto;
import littlepartypro.model.UserEntity;
import littlepartypro.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        if (loginDto.username().isEmpty() || loginDto.password().isEmpty()) {
            return new ResponseEntity<>("Username or password must not be empty", HttpStatus.BAD_REQUEST);
        }
        try {
            Authentication authentication = authenticationManager.authenticate((
                new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password())
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("User signed in successfully", HttpStatus.OK);
        } catch (AuthenticationException exception) {
            return new ResponseEntity<>("User does not exist", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (registerDto.username().isEmpty() || registerDto.password().isEmpty()) {
            return new ResponseEntity<>("Username or password must not be empty", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByUsername(registerDto.username())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity(registerDto.username(), passwordEncoder.encode(registerDto.password()));

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
}
