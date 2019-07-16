package com.example.demo.foundation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoundationService {

    @Autowired
    private FoundationRepository foundationRepository;

    public List<Foundation> selectFoundations() {
        return foundationRepository.findAll();
    }

    public Foundation findById(Long id) {
        return foundationRepository.getById(id);
    }
}
