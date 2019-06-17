package com.example.demo.Organisation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationService {

    @Autowired
    private OrganisationRepository organisationRepository;

    public List<Organisation> selectOrganisations() {
        return organisationRepository.findAll();
    }

    public Organisation findById(Long id) {
        return organisationRepository.getById(id);
    }
}
