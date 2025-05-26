package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;

import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;

public class ShortCourse extends Event {
  private final String eventTypeJson = "Short Course";

  public ShortCourse(String name, String description, String location, LocalDate date, int capacity, Modality modality, String code) {
    super(name, description, location, date, capacity, modality, code);
  }

  public ShortCourse() {
    super();
  } //Gson

  public Boolean checkEligibility(){
  // TODO: see if student is eligible for course by gpa (maybe move to ShortCourse?)
  return true;
  }

  @Override
  public String getType() {
    return eventTypeJson;
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    return sb.toString();
  }
  
}
