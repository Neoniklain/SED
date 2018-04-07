package com.unesco.core.services.mapperService;

public interface IMapperService {

    <T> Object toEntity(T model);

    <T> Object toModel(T entity);

}
