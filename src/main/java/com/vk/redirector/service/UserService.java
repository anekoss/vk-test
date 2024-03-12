//package com.vk.redirector.service;
//
//import com.vk.redirector.entity.RoleEntity;
//import com.vk.redirector.entity.UserEntity;
//import com.vk.redirector.repository.RoleRepository;
//import com.vk.redirector.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class UserService implements UserDetailsService {
//
//
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    public boolean loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        return true;
//
//    }
//
//    public UserEntity findUserById(Long userId) {
//        Optional<UserEntity> userFromDb = userRepository.findById(userId);
//        return userFromDb.orElse(new UserEntity());
//    }
//
//    public List<UserEntity> allUsers() {
//        return userRepository.findAll();
//    }
//
//    public boolean saveUser(UserEntity user) {
//        UserEntity userFromDB = userRepository.findByUsername(user.getUsername());
//        if (userFromDB != null) {
//            return false;
//        }
//        user.setRoles(Collections.singleton(new RoleEntity(1L, "ROLE_USER")));
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return true;
//    }
//
//    public boolean deleteUser(Long userId) {
//        if (userRepository.findById(userId).isPresent()) {
//            userRepository.deleteById(userId);
//            return true;
//        }
//        return false;
//    }


//}
