package com.example.demo.Gifts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftsRepository extends JpaRepository<Gifts, Integer> {



}
