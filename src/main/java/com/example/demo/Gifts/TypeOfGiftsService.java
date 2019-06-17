package com.example.demo.Gifts;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeOfGiftsService {

    private TypeOfGiftRepository typeOfGiftRepository;

    public List<TypeOfGift> getAllTypeOfGifts() {
        return typeOfGiftRepository.findAll();
    }

    public TypeOfGift getById(Long id) {
        return typeOfGiftRepository.findOne(id);
    }
}
