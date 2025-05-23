package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;

public class AcademicFair extends Event {

  public AcademicFair(String name, String description, String location, LocalDate date, int capacity, Modality modality, String code) {
    super("AcademicFair", name, description, location, date, capacity, modality, code);
  }
  
}
