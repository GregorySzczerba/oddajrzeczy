package com.example.demo.Gifts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("giftsService")
public class GiftsService {

    @Autowired
    private GiftsRepository giftsRepository;

    public List<Gifts> selectGifts() {
        return giftsRepository.findAll();
    }

    public List<Gifts> userGifts(int id) {
       List<Gifts> giftsList = giftsRepository.findAllByUserId(id);
       return giftsList;
    }

    public void delete(Gifts gift) {
    }


}
