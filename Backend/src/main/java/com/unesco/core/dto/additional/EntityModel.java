package com.unesco.core.dto.additional;

public interface EntityModel<E> {
   void EntityToModel(E entity);
   E toEntity(E model);
}
