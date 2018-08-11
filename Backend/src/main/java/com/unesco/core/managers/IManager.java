package com.unesco.core.managers;

public interface IManager<T> {
    void init(T t);
    T get();
}
