package me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.persistors;

import java.sql.SQLException;

public interface Persistor<T> {
  void persist(T data) throws SQLException;
}
