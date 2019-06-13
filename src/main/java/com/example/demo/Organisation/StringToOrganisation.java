package com.example.demo.Organisation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToOrganisation implements Converter<String, Organisation> {

    @Autowired
    private OrganisationRepository organisationRepository;

    @Override
    public Organisation convert(String s) {
        Long id  = Long.valueOf(s);
        return organisationRepository.getById(id);
    }
}
