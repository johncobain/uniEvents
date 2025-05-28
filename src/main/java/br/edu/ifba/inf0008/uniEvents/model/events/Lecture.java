package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;

import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;

public class Lecture extends Event{
  private final String eventTypeJson = "Lecture";

  private String mainTopic;
  private String language;
  private int hours;
  private int minutes;

  public Lecture(String name, String description, String local, LocalDate date, int capacity, Modality modality, String code, String mainTopic, String language, int durationMinutes) {
    super(name, description, local, date,capacity, modality, code);
    this.mainTopic = mainTopic;
    this.language = language;

    this.hours = durationMinutes / 60;
    this.minutes = durationMinutes % 60;
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
    sb.append(Lines.multiLineText(String.format("Main Topic: %s", mainTopic))).append("\n");
    sb.append(Lines.multiLineText(String.format("Language: %s", language))).append("\n");
    sb.append(Lines.multiLineText(String.format("Duration: %02d:%02d", hours, minutes))).append("\n");
    return sb.toString();
  }
  
}
