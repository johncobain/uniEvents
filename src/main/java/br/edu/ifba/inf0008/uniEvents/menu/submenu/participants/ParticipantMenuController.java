package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants;

import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class ParticipantMenuController {
  private ParticipantManager participantManager;
  private EventManager eventManager;

  public void setParticipantManager(ParticipantManager participantManager) {
    this.participantManager = participantManager;
  }

  public void setEventManager(EventManager eventManager) {
    this.eventManager = eventManager;
  }

  public void create(){
    String selectedType = ParticipantForms.getType();
    if (selectedType.equalsIgnoreCase("cancel")) return;

    String name = ParticipantForms.getName();
    if(name.equalsIgnoreCase("cancel")) return;

    String cpf = ParticipantForms.getCpf(participantManager);
    if(cpf.equalsIgnoreCase("cancel")) return;

    String email = ParticipantForms.getEmail();
    if (email.equals("cancel")) return;

    String phone = ParticipantForms.getPhone();
    if (phone.equals("cancel")) return;

    String birthDateString = ParticipantForms.getBirthDate();
    if (birthDateString.equals("cancel")) return;

    switch (selectedType) {
      case "Student" -> participantManager.createStudent(name, cpf, email, phone, Utils.stringToDate(birthDateString));
      case "Teacher" -> participantManager.createTeacher(name, cpf, email, phone, Utils.stringToDate(birthDateString));
      case "External" -> participantManager.createExternal(name, cpf, email, phone, Utils.stringToDate(birthDateString));
    }

    System.out.println(Lines.clear());
    System.out.println(Lines.successLine("Participant created!"));
  }

  public void remove(){
    String cpf = ParticipantForms.getCpf(participantManager);
    if(cpf.equalsIgnoreCase("cancel")) return;

    Participant participant = participantManager.getParticipant(cpf);
    if (participant == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant not found!"));
      return;
    }
    try {
        participantManager.removeParticipant(participant);
        System.out.println(Lines.clear());
        System.out.println(Lines.successLine("Participant removed!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public void update(){
    String cpf = ParticipantForms.getCpf(participantManager);
    if(cpf.equalsIgnoreCase("cancel")) return;

    Participant participant = participantManager.getParticipant(cpf);
    if (participant == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant not found!"));
      return;
    }

    String name = ParticipantForms.getName();
    if(name.equalsIgnoreCase("cancel")) return;

    String email = ParticipantForms.getEmail();
    if (email.equals("cancel")) return;

    String phone = ParticipantForms.getPhone();
    if (phone.equals("cancel")) return;

    String birthDateString = ParticipantForms.getBirthDate();
    if (birthDateString.equals("cancel")) return;

    try {
      participantManager.updateParticipant(participant, name, email, phone, Utils.stringToDate(birthDateString));
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Participant updated!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }
  
  public void listAll(){
    ArrayList<Participant> participants = participantManager.getAllParticipants();

    if(participants.isEmpty()){
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("No participants found!"));
      return;
    }
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.titleLine("All Participants", Colors.BLUE_BOLD));
    System.out.println(Lines.doubleLine());
    for(Participant participant : participants){
      System.out.println(Lines.straightLine());
      System.out.print(participant.toString());
      System.out.println(Lines.straightLine());
    }

  }

  public void listByType(){
    String selectedType = ParticipantForms.getType();
    if(selectedType.equalsIgnoreCase("cancel")) return;

    ArrayList<Participant> participants = participantManager.getAllParticipants();
    ArrayList<Participant> filteredParticipants = new ArrayList<>();
    for (Participant participant : participants) {
      if (participant.getType().equals(selectedType)) {
        filteredParticipants.add(participant);
      }
    }
    if(filteredParticipants.isEmpty()){
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("No participants found!"));
      return;
    }
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.titleLine(selectedType+" Participants", Colors.BLUE_BOLD));
    System.out.println(Lines.doubleLine());
    for (Participant participant : filteredParticipants) {
      System.out.println(Lines.straightLine());
      System.out.print(participant.toString());
      System.out.println(Lines.straightLine());
    }
  }

  public void showEvents(){
    String cpf = ParticipantForms.getCpf(participantManager);
    if(cpf.equalsIgnoreCase("cancel")) return;

    Participant participant = participantManager.getParticipant(cpf);
    if (participant == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant not found!"));
      return;
    }
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.titleLine("Events with participant " + participantManager.getParticipant(cpf).getName(), Colors.BLUE_BOLD));
    System.out.println(Lines.doubleLine());
    ArrayList<Event> events = eventManager.getEventsByParticipant(participant);
    if(events.isEmpty()){
      System.out.println(Lines.leftText(participant.getName() + " has no events!"));
    }
    for (Event event : events) {
      System.out.println(Lines.straightLine());
      System.out.print(event.toString());
      System.out.println(Lines.straightLine());
    }
  }

  public void clearAll(){
    try {
      participantManager.clearAllParticipants(eventManager);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("All participants removed!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }
  
  public void addToEvent(){
    String cpf = ParticipantForms.getCpf(participantManager);
    if(cpf.equalsIgnoreCase("cancel")) return;

    Participant participant = participantManager.getParticipant(cpf);
    if (participant == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant not found!"));
      return;
    }

    System.out.print("Enter event code (\"cancel\" to exit)>> ");
    String code = Utils.scanner.nextLine();
    if (code.equals("cancel")) return;

    Event event = eventManager.getEvent(code);
    if (event == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Event not found!"));
      return;
    }
    try {
      eventManager.addParticipant(event.getCode(), participant.getCpf());
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Participant added to event!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public void removeFromEvent(){
    String cpf = ParticipantForms.getCpf(participantManager);
    if(cpf.equalsIgnoreCase("cancel")) return;

    Participant participant = participantManager.getParticipant(cpf);
    if (participant == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant not found!"));
      return;
    }

    System.out.print("Enter event code (\"cancel\" to exit)>> ");
    String code = Utils.scanner.nextLine();
    if (code.equals("cancel")) return;

    Event event = eventManager.getEvent(code);
    if (event == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Event not found!"));
      return;
    }
    try {
      eventManager.removeParticipant(event.getCode(), participant.getCpf());
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Participant removed from event!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }
}
