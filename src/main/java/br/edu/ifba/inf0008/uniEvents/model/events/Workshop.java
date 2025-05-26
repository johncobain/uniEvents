package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;

import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;

public class Workshop extends Event {
  private final String eventTypeJson = "Workshop";

  public Workshop(String name, String description, String location, LocalDate date, int capacity, Modality modality, String code) {
    super(name, description, location, date, capacity, modality, code);
  }

  public Workshop() {
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
