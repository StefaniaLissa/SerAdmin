package com.example.seradmin.database.Listeners;

public abstract class RetrievalEventListener<T> extends AbstractEventListener {
    public abstract void OnDataRetrieved(T t);
}
