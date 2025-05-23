package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;
import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;

public abstract class Event {
  private String name;
  private String description;
  private String location;
  private LocalDate date;
  private int capacity;
  private Modality modality;
  private ArrayList<Participant> participants;
  private String code;
  
  public Event(String name, String description, String location, LocalDate date, int capacity, Modality modality, String code) {
    this.name = name;
    this.description = description;
    this.location = location;
    this.date = date;
    this.capacity = capacity;
    this.modality = modality;
    this.code = code;
    this.participants = new ArrayList<>();
  }

  protected  Event(){} //Gson

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public Modality getModality() {
    return modality;
  }

  public void setModality(Modality modality) {
    this.modality = modality;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  
}
