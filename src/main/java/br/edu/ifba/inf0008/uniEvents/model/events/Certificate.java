package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class Certificate {
  private final String id;
  private final LocalDateTime createdAt;
  private String eventName;
  private String eventCode;
  private Modality modality;
  private double totalHours;
  private LocalDate eventDate;
  private String participantName;
  private String participantCpf;
  private String participantEmail;
  
  public Certificate(Event event, Participant participant){
    this.id = UUID.randomUUID().toString();
    this.createdAt = LocalDateTime.now();
    this.eventName = event.getName();
    this.eventCode = event.getCode();
    this.modality = event.getModality();
    this.totalHours = event.getTotalHours();
    this.eventDate = event.getDate();
    this.participantName = participant.getName();
    this.participantCpf = participant.getCpf();
    this.participantEmail = participant.getEmail();
  }
  public Certificate(){
    this.id = UUID.randomUUID().toString();
    this.createdAt = LocalDateTime.now();
  }//GSON

  public String getId(){
    return id;
  }
  public String summary(){
    return String.format("%s - %s", eventName, eventCode);
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append(Lines.doubleLine()).append("\n");
    sb.append(Lines.centeredMultiLineText("CERTIFICATE", Colors.GREEN_BOLD)).append("\n");
    sb.append(Lines.doubleLine()).append("\n");
    sb.append(Lines.centeredMultiLineText("Created at " + Utils.dateToString(createdAt))).append("\n");
    sb.append(Lines.multiLineText("")).append("\n");
    sb.append(Lines.multiLineText("Event: " + eventName)).append("\n");
    sb.append(Lines.multiLineText("Code: " + eventCode)).append("\n");
    sb.append(Lines.multiLineText("Modality: " + modality.getDescription())).append("\n");
    sb.append(Lines.multiLineText("Total Hours: " + totalHours)).append("\n");
    sb.append(Lines.multiLineText("Date: " + Utils.dateToString(eventDate))).append("\n");
    sb.append(Lines.multiLineText("")).append("\n");

    sb.append(Lines.multiLineText("Participant: " + participantName)).append("\n");
    sb.append(Lines.multiLineText("CPF: " + participantCpf)).append("\n");
    sb.append(Lines.multiLineText("Email: " + participantEmail)).append("\n");
    sb.append(Lines.multiLineText("")).append( "\n");
    sb.append(Lines.centeredMultiLineText("Certificate ID: " + id)).append("\n");
    sb.append(Lines.doubleLine()).append("\n");
    return sb.toString();
  }
}
