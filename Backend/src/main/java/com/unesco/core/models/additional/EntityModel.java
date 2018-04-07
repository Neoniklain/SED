package com.unesco.core.models.additional;

public interface EntityModel<E> {
   void EntityToModel(E entity);
   E toEntity(E model);
}
