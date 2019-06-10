package com.example.demo.Organisation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganisationService {

    @Autowired
    private OrganisationRepository organisationRepository;

    public List<Organisation> selectOrganisations() {
        List<Organisation> organisationList = new ArrayList<>();
        organisationList = organisationRepository.findAll();
        return organisationList;
    }
}
