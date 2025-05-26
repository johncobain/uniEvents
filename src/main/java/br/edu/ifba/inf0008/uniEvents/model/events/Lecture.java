package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;

import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;

public class Lecture extends Event{
  private final String eventTypeJson = "Lecture";

  public Lecture(String name, String description, String local, LocalDate date, int capacity, Modality modality, String code) {
    super(name, description, local, date,capacity, modality, code);
  }

  public Lecture() {
    super();
  } //Gson

  @Override
  public String getType() {
    return eventTypeJson;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    return sb.toString();
  }
  
}
