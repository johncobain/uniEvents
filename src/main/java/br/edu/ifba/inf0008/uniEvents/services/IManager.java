package br.edu.ifba.inf0008.uniEvents.services;

import java.util.LinkedHashMap;

public interface IManager<T> {
  void add(T object);
  void remove(String id);
  void update(String id, T updatedObject);
  void clear();
  T get(String id);
  LinkedHashMap<String, T> getAll();
}
