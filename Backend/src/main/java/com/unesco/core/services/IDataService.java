package com.unesco.core.services;

import java.util.List;

public interface IDataService<T> {
    T Save(T t);
    void Delete(long id);
    T Get(long id);
    List<T> GetAll();
}
