package com.unesco.core.managers;

public interface IManager<T> {
    void Init(T t);
    T Get();
}
