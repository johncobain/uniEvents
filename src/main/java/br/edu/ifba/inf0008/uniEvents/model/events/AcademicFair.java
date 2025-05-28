package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;

import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;

public class AcademicFair extends Event {
  private final String eventTypeJson = "Academic Fair";
  private int stands;
  private String centralTheme;
  private LocalDate finalDate;

  public AcademicFair(String name, String description, String location, LocalDate date, int capacity, Modality modality, String code, int stands, String centralTheme, LocalDate finalDate) {
    super( name, description, location, date, capacity, modality, code);
    this.stands = stands;
    this.centralTheme = centralTheme;
    this.finalDate = finalDate;
  }

  public AcademicFair() {
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
    sb.append(Lines.multiLineText(String.format("Stands: %d", stands))).append("\n");
    sb.append(Lines.multiLineText(String.format("Central Theme: %s", centralTheme))).append("\n");
    sb.append(Lines.multiLineText(String.format("Final Date: %s", finalDate))).append("\n");
    return sb.toString();
  }
  
}
