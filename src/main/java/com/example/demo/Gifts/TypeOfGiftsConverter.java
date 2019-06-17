package com.example.demo.Gifts;

import org.springframework.core.convert.converter.Converter;

    public class TypeOfGiftsConverter implements Converter<String, TypeOfGift> {

        private TypeOfGiftsService typeOfGiftsService;

        public TypeOfGiftsConverter(TypeOfGiftsService typeOfGiftsService) {
            this.typeOfGiftsService = typeOfGiftsService;
        }

        @Override
        public TypeOfGift convert(String s) {
            if (s == null || s.isEmpty()) {
                return null;
            }
            Long id = Long.valueOf(s);
            return typeOfGiftsService.getById(id);
        }


}
