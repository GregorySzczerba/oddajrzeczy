package com.example.demo.User;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;

public interface Page<T> extends Slice {

    @Override
    default int getNumber() {
        return 0;
    }

    @Override
    default int getSize() {
        return 0;
    }

    @Override
    default int getNumberOfElements() {
        return 0;
    }

    @Override
    default List getContent() {
        return null;
    }

    @Override
    default boolean hasContent() {
        return false;
    }

    @Override
    default Sort getSort() {
        return null;
    }

    @Override
    default boolean isFirst() {
        return false;
    }

    @Override
    default boolean isLast() {
        return false;
    }

    @Override
    default boolean hasNext() {
        return false;
    }

    @Override
    default boolean hasPrevious() {
        return false;
    }

    @Override
    default Pageable nextPageable() {
        return null;
    }

    @Override
    default Pageable previousPageable() {
        return null;
    }

    @Override
    default Slice map(Converter converter) {
        return null;
    }

    @Override
    default Iterator iterator() {
        return null;
    }
}
