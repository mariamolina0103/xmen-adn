package com.xmen.core.repositorio;

import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;

public abstract class MapperImpl<D, E> {

    private final ModelMapper mapper;
    private final Type dtoType;
    private final Type entityType;

    public D mapToDto(E entity) {
        return this.mapper.map(entity, this.dtoType);
    }

    public E mapToEntity(D dto) {
        return this.mapper.map(dto, this.entityType);
    }

    public MapperImpl(ModelMapper mapper, Type dtoType, Type entityType) {
        this.mapper = mapper;
        this.dtoType = dtoType;
        this.entityType = entityType;
    }

    public ModelMapper getMapper() {
        return this.mapper;
    }

}
