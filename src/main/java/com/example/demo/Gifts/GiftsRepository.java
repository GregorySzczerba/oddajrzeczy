package com.example.demo.Gifts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftsRepository extends JpaRepository<Gifts, Integer> {


    public List<Gifts> findAllByUserId(int id);

    Gifts findById(Long id);
}
