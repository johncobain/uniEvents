package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;

public class Workshop extends Event {

  public Workshop(String name, String description, String location, LocalDate date, int capacity, Modality modality, String code) {
    super("Workshop", name, description, location, date, capacity, modality, code);
  }
  
}
