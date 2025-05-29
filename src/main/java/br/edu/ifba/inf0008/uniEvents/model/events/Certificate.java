package br.edu.ifba.inf0008.uniEvents.model.events;

import java.util.UUID;

public class Certificate {
  private final String id;
  private String eventName;
  private String eventCode;
  
  public Certificate(String eventName, String eventCode){
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

  public String summary(){
    return String.format("%s - %s", eventName, eventCode);
  }

  @Override
  public String toString(){
    return summary();
  }
}
