package littlepartypro.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import littlepartypro.model.UserEntity;
import littlepartypro.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Test
    public void testFindByUsername() {
        // Mock data
        String username = "test_user";
        UserEntity user = new UserEntity(username, "test_password");

        // Mock behavior
        when(userRepositoryMock.findByUsername(username)).thenReturn(Optional.of(user));

        // Test
        Optional<UserEntity> foundUserOptional = userRepositoryMock.findByUsername(username);
        assertTrue(foundUserOptional.isPresent());
        assertEquals(user, foundUserOptional.get());
    }

    @Test
    public void testFindByUsernameNotFound() {
        // Mock data
        String username = "nonexistent_user";

        // Mock behavior
        when(userRepositoryMock.findByUsername(username)).thenReturn(Optional.empty());

        // Test
        Optional<UserEntity> foundUserOptional = userRepositoryMock.findByUsername(username);
        assertFalse(foundUserOptional.isPresent());
    }

    @Test
    public void testExistsByUsername() {
        // Mock data
        String existingUsername = "existing_user";

        // Mock behavior
        when(userRepositoryMock.existsByUsername(existingUsername)).thenReturn(true);

        // Test
        assertTrue(userRepositoryMock.existsByUsername(existingUsername));
    }

    @Test
    public void testExistsByUsernameNotFound() {
        // Mock data
        String nonexistentUsername = "nonexistent_user";

        // Mock behavior
        when(userRepositoryMock.existsByUsername(nonexistentUsername)).thenReturn(false);

        // Test
        assertFalse(userRepositoryMock.existsByUsername(nonexistentUsername));
    }
}
