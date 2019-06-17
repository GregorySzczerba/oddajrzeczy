package com.example.demo.User;

import com.example.demo.role.Role;
import com.example.demo.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("userService")
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserById(int id) {
        return userRepository.findById(id);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(0);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    public void saveAdmin(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    public List<User> selectAdmins(int id, int role_id, Pageable pageable) {
        List<User> admins = userRepository.findAllUsersByIdIsNotAndRolesIdOrderByIdAsc(id, role_id, pageable);
        return admins;
    }

    public List<User> selectUsers(int id, int role_id, Pageable pageable) {
        List<User> users = userRepository.findAllUsersByIdIsNotAndRolesIdOrderByIdAsc(id, role_id, pageable);
        return users;
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

}