package com.vk.redirector.security;

import com.vk.redirector.entity.Role;
import com.vk.redirector.entity.User;
import com.vk.redirector.repository.RoleRepository;
import com.vk.redirector.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Optional;

import static com.vk.redirector.domain.RoleType.ROLE_ADMIN;
import static com.vk.redirector.domain.RoleType.ROLE_USERS_VIEWER;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    @PersistenceContext
    private final EntityManager em;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return user.get();
    }


    public void saveUser(User user) {
        Optional<User> userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB.isPresent()) {
            return;
        }
        Role role = roleRepository.findRolesByName(ROLE_USERS_VIEWER).get();
        user.setRoles(Collections.singleton(role));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}
