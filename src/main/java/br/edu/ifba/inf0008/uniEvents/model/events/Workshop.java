package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;

import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;

public class Workshop extends Event {
  private final String eventTypeJson = "Workshop";

  private String activitiesSummary;
  

  public Workshop(String name, String description, String location, LocalDate date, int capacity, Modality modality, double totalHours, String code, String activitiesSummary) {
    super(name, description, location, date, capacity, modality, totalHours, code);
    this.activitiesSummary = activitiesSummary;
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
    sb.append(Lines.multiLineText(String.format("Activities Summary: %s", activitiesSummary))).append("\n");
    return sb.toString();
  }

}
