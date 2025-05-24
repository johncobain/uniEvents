package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;

public class AcademicFair extends Event {
  private final String eventTypeJson = "Academic Fair";

  public AcademicFair(String name, String description, String location, LocalDate date, int capacity, Modality modality, String code) {
    super( name, description, location, date, capacity, modality, code);
  }

  public AcademicFair() {
    super();
  } //Gson

  @Override
  public String getType() {
    return eventTypeJson;
  }
  
}
