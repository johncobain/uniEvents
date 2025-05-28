package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;

import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;

public class Workshop extends Event {
  private final String eventTypeJson = "Workshop";

  private int totalHours;
  private String activitiesSummary;
  

  public Workshop(String name, String description, String location, LocalDate date, int capacity, Modality modality, String code, int totalHours, String activitiesSummary) {
    super(name, description, location, date, capacity, modality, code);
    this.totalHours = totalHours;
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
    sb.append(Lines.multiLineText(String.format("Total Hours: %d", totalHours))).append("\n");
    sb.append(Lines.multiLineText(String.format("Activities Summary: %s", activitiesSummary))).append("\n");
    return sb.toString();
  }

}
