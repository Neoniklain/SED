package com.unesco.core.managers;

import java.util.List;

public interface IListManager<T> {

    void Init(List<T> t);
    List<T> GetAll();

}
