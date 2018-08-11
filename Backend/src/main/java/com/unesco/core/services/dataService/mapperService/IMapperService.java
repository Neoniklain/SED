package com.unesco.core.services.dataService.mapperService;

import org.springframework.stereotype.Service;

@Service
public interface IMapperService {

    <T> Object toEntity(T model);

    <T> Object toDto(T entity);

}
