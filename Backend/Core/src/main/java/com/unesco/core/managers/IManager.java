package com.unesco.core.managers;

public interface IManager<T> extends IValidateManager {
    void init(T t);
    T get();
}
