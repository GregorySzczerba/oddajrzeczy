package com.example.demo.User;

import com.example.demo.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByEmail(String email);
    Page<User> findAllUsersByIdIsNotAndRolesId(int id, int role_id, Pageable pageable);
    User findById(int id);




}
