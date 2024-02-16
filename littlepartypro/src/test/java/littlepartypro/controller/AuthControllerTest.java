package littlepartypro.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import littlepartypro.dto.RegisterDto;
import littlepartypro.model.UserEntity;
import littlepartypro.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @Mock
    private UserRepository repository;
    @Mock
    private PasswordEncoder passwordEncoder;
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
    

}