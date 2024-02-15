package littlepartypro.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import littlepartypro.dto.RegisterDto;
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


}