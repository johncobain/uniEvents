package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;
import java.util.UUID;

import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;

public class Certificate {
  private final String id;
  private String eventName;
  private String eventCode;
  private String date;
  private Modality modality;
  private double totalHours;
  
  public Certificate(String eventName, String eventCode, LocalDate date, Modality modality, double totalHours){
    this.id = UUID.randomUUID().toString();
    this.eventName = eventName;
    this.eventCode = eventCode;
  }
  public Certificate(){
    this.id = UUID.randomUUID().toString();
  }//GSON

  public String getId(){
    return id;
  }

  public String getEventName(){
    return eventName;
  }

  public void setEventName(String eventName){
    this.eventName = eventName;
  }

  public String getEventCode(){
    return eventCode;
  }

  public void setEventCode(String eventCode){
    this.eventCode = eventCode;
  }
  
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }
  public Modality getModality() {
    return modality;
  }
  public void setModality(Modality modality) {
    this.modality = modality;
  }
  public double getTotalHours() {
    return totalHours;
  }
  public void setTotalHours(int totalHours) {
    this.totalHours = totalHours;
  }
  public String summary(){
    return String.format("%s - %s", eventName, eventCode);
  }

  @Override
  public String toString(){
    return summary();
  }
}
