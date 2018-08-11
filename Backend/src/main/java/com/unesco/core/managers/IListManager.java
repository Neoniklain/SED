package com.unesco.core.managers;

import java.util.List;

public interface IListManager<T> {

    void init(List<T> t);
    List<T> getAll();

}
