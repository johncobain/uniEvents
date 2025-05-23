package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;

public class ShortCourse extends Event {

  public ShortCourse(String name, String description, String location, LocalDate date, int capacity, Modality modality, String code) {
    super("ShortCourse", name, description, location, date, capacity, modality, code);
  }
  
}
