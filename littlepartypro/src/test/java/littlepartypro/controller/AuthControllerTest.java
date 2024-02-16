package littlepartypro.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import littlepartypro.dto.LoginDto;
import littlepartypro.dto.RegisterDto;
import littlepartypro.model.UserEntity;
import littlepartypro.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @Mock
    private UserRepository repository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @MockBean
    private SecurityContext securityContextHolder;
    @InjectMocks
    private AuthController authController;

    @Test
    void whenRegisterShouldCallExistsByUsernameOnRepository(){
        RegisterDto registerDto = new RegisterDto("username", "password");
        authController.register(registerDto);

        verify(repository).existsByUsername(registerDto.username());
    }
    @Test
    void whenRegisterShouldReturnErrorIfUsernameExists(){
        RegisterDto registerDto = new RegisterDto("username", "password");
        when(repository.existsByUsername("username")).thenReturn(Boolean.TRUE);
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);

        assertEquals(expectedResponse, authController.register(registerDto));
        verify(repository, never()).save(any(UserEntity.class));
        assert(expectedResponse.getStatusCode() == HttpStatus.BAD_REQUEST);
        assert(expectedResponse.getBody().equals("Username is already taken!"));
    }

    @Test
    void whenRegisterShouldCreateNewUserIfUsernameNotExists(){
        RegisterDto registerDto = new RegisterDto("username", "password");
        when(repository.existsByUsername("username")).thenReturn(Boolean.FALSE);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        ResponseEntity<String> expectedResponse = authController.register(registerDto);

        verify(repository).save(any(UserEntity.class));
        assert(expectedResponse.getStatusCode() == HttpStatus.OK);
        assert(expectedResponse.getBody().equals("User registered success!"));
    }

    @Test
    void whenLoginShouldCallAuthenticateOnAuthenticationManager(){
        LoginDto loginDto = new LoginDto("username", "password");
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password());
        authController.login(loginDto);

        verify(authenticationManager).authenticate(authToken);
    }

    @Test
    void whenLoginShouldCorrectlySetAuthentication(){
        LoginDto loginDto = new LoginDto("username", "password");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()));
        authController.login(loginDto);

        assert SecurityContextHolder.getContext().getAuthentication() == authentication;
    }

    @Test
    void whenLoginShouldBeSuccessfulWithCorrectCredentials(){
        LoginDto loginDto = new LoginDto("username", "password");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()));

        ResponseEntity<String> expectedResponse = authController.login(loginDto);

        assert expectedResponse.getStatusCode() == HttpStatus.OK;
        assert expectedResponse.getBody().equals("User signed in successfully");
    }


}