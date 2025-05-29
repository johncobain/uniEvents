package br.edu.ifba.inf0008.uniEvents.services;

import java.util.LinkedHashMap;

public interface IManager<T> {
  void add(T obj);
  void remove(String key);
  void update(String key, T updatedObj);
  void clear();
  T get(String key);
  LinkedHashMap<String, T> getAll();
}
