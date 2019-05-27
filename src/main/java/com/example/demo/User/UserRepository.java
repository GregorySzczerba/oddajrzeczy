package com.example.demo.User;

import com.example.demo.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    List<User> findAllUsersByIdIsNotAndRolesId(int id, int role_id);
    User deleteById(int id);
    User findById(int id);


}
