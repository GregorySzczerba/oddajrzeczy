package com.example.demo.Gifts;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Time;
import java.time.LocalTime;

@Converter(autoApply = true)
public class LocalTimeAttributeConverter implements AttributeConverter<LocalTime, Time> {


    @Override
    public Time convertToDatabaseColumn(LocalTime localTime) {
        if (localTime != null) {
            return Time.valueOf(localTime);
        } else {
            return null;
        }
    }

    @Override
    public LocalTime convertToEntityAttribute(Time time) {
        if (time != null) {
            return time.toLocalTime();
        } else {
            return null;
        }
    }

}
