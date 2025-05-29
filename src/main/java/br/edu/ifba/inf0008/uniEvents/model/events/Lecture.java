package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;
import java.util.List;

import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;

public class Lecture extends Event{
  private final String eventTypeJson = "Lecture";

  private String mainTopic;
  private List<String> subTopics;
  private List<String> objectives;
  private String language;

  public Lecture(String name, String description, String local, LocalDate date, int capacity, Modality modality, double totalHours, String code, String mainTopic, List<String> subTopics, List<String> objectives, String language) {
    super(name, description, local, date,capacity, modality, totalHours, code);
    this.mainTopic = mainTopic;
    this.subTopics = subTopics;
    this.objectives = objectives;
    this.language = language;
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
    sb.append(Lines.multiLineText(String.format("Sub Topics: %s", String.join(" - ", subTopics)))).append("\n");
    sb.append(Lines.multiLineText(String.format("Objectives: %s", String.join(" - ", objectives)))).append("\n");
    sb.append(Lines.multiLineText(String.format("Language: %s", language))).append("\n");
    return sb.toString();
  }
  
}
