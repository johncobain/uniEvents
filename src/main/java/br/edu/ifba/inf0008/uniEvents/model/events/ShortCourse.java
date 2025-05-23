package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;

public class ShortCourse extends Event {
  private final String eventTypeJson = "Short Course";

  public ShortCourse(String name, String description, String location, LocalDate date, int capacity, Modality modality, String code) {
    super(name, description, location, date, capacity, modality, code);
  }

  public ShortCourse() {
    super();
  } //Gson

  @Override
  public String getEventType() {
    return eventTypeJson;
  }
  
}
