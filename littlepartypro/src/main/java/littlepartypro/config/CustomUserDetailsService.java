package littlepartypro.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import littlepartypro.model.RoleEntity;
import littlepartypro.model.UserEntity;
import littlepartypro.repository.RoleRepository;
import littlepartypro.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new User(user.getUsername(), user.getPassword(), getAuthority(user));
}
    private Collection<? extends GrantedAuthority> getAuthority(UserEntity user) {
        // Fetch the role associated with the user's roleId
        RoleEntity role = roleRepository.findById(user.getRoleId())
            .orElseThrow(() -> new RuntimeException("Role not found for user"));

        // Create a SimpleGrantedAuthority object using the roleName of the fetched role
        GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());

        // Return a collection containing the authority
        return Collections.singletonList(authority);
    }

}
