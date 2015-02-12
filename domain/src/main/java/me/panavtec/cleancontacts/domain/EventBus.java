package me.panavtec.cleancontacts.domain;

public interface EventBus {
    public void post(Object object);
    public void register(Object object);
    public void unregister(Object object);
}