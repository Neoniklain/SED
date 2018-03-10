package com.unesco.core.repositories.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface CrudPagableRepository<T,M extends Serializable> extends CrudRepository<T,M> {
   Page findAll(Pageable pageRequest);
}
