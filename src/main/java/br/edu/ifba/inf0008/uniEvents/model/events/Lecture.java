package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;

public class Lecture extends Event{

  public Lecture(String name, String description, String local, LocalDate date, int capacity, Modality modality, String code) {
    super("Lecture", name, description, local, date,capacity, modality, code);
  }
  
}
