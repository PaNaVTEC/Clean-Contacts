package me.panavtec.cleancontacts.domain.abstractions;

public interface Bus {
    public void post(Object object);
    public void register(Object object);
    public void unregister(Object object);
}