package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifba.inf0008.uniEvents.menu.submenu.events.EventForms;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.participants.External;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.model.participants.Teacher;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class ParticipantMenuController {
  private ParticipantManager participantManager;
  private EventManager eventManager;
  private final StudentMenuController studentMenuController = new StudentMenuController();

  public void setParticipantManager(ParticipantManager participantManager) {
    this.participantManager = participantManager;
  }

  public void setEventManager(EventManager eventManager) {
    this.eventManager = eventManager;
  }

  public void create(String type){
    String name = ParticipantForms.getName(type);
    if(name.equalsIgnoreCase("cancel")) return;

    String cpf;
    while(true){
      cpf = ParticipantForms.getCpf(participantManager);
      if(participantManager.isCpfAlreadyInUse(cpf)){
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine("CPF '" + cpf + "' is already in use! Please try again."));
        continue;
      }
      break;
    }
    if(cpf.equalsIgnoreCase("cancel")) return;

    String email = ParticipantForms.getEmail();
    if (email.equalsIgnoreCase("cancel")) return;

    String phone = ParticipantForms.getPhone();
    if (phone.equalsIgnoreCase("cancel")) return;

    String birthDateString = ParticipantForms.getDate("birth date");
    if (birthDateString.equalsIgnoreCase("cancel")) return;

    Boolean created = false;
    switch (type) {
      case "Student" -> created = studentMenuController.create(participantManager, name, cpf, email, phone, birthDateString);
      case "Teacher" -> participantManager.createTeacher(name, cpf, email, phone, Utils.stringToDate(birthDateString));
      case "External" -> participantManager.createExternal(name, cpf, email, phone, Utils.stringToDate(birthDateString));
    }

    if (!created) return;

    System.out.println(Lines.clear());
    System.out.println(Lines.successLine(type + " '" + name + "' created!"));
  }

  public void remove(String cpf){
    if (participantManager.get(cpf) == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant not found!"));
      return;
    }
    try {
        participantManager.remove(cpf, eventManager);
        System.out.println(Lines.clear());
        System.out.println(Lines.successLine("Participant removed!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public void update(String type, String cpf){
    if (participantManager.get(cpf) == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant not found!"));
      return;
    }

    String name = ParticipantForms.getName(type);
    if(name.equalsIgnoreCase("cancel")) return;

    String email = ParticipantForms.getEmail();
    if (email.equalsIgnoreCase("cancel")) return;

    String phone = ParticipantForms.getPhone();
    if (phone.equalsIgnoreCase("cancel")) return;

    String birthDateString = ParticipantForms.getDate("birth date");
    if (birthDateString.equalsIgnoreCase("cancel")) return;

    Participant updatedParticipant = null;
    switch (participantManager.get(cpf).getType()) {
      case "Student" -> updatedParticipant = studentMenuController.update(participantManager, cpf, name, email, phone, birthDateString);
      case "Teacher" -> updatedParticipant = new Teacher(name, cpf, email, phone, Utils.stringToDate(birthDateString));
      case "External" -> updatedParticipant = new External(name, cpf, email, phone, Utils.stringToDate(birthDateString));
    }

    if (updatedParticipant == null) return;

    try {
      participantManager.update(cpf, updatedParticipant);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Participant updated!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Error updating participant"));
    }
  }
  
  public void listAll(){
    LinkedHashMap<String, Participant> participants = participantManager.getAll();

    if(participants.isEmpty()){
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("No participants found!"));
      return;
    }
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.titleLine("All Participants", Colors.BLUE_BOLD));
    System.out.println(Lines.doubleLine());
    for(Participant participant : participants.values()){
      System.out.println(Lines.straightLine());
      System.out.print(participant.toString());
      System.out.println(Lines.straightLine());
    }

  }

  public void listType(String type){
    List<Participant> participants = participantManager.getAll().values()
    .stream()
    .filter(p -> p.getType().equalsIgnoreCase(type))
    .collect(Collectors.toList()); 

    if(participants.isEmpty()){
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("No " + type + "s found!"));
      return;
    }
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.titleLine(type+"s", Colors.BLUE_BOLD));
    System.out.println(Lines.doubleLine());
    for (Participant participant : participants) {
      System.out.println(Lines.straightLine());
      System.out.print(participant.toString());
      System.out.println(Lines.straightLine());
    }
  }

  public void get(){
    String cpf = ParticipantForms.getCpf(participantManager);
    if(cpf.equalsIgnoreCase("cancel")) return;

    if (participantManager.get(cpf) == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant not found!"));
      return;
    }

    String type = participantManager.get(cpf).getType();
    
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.titleLine(type, Colors.BLUE_BOLD));
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.straightLine());
    System.out.print(participantManager.get(cpf).toString());
    System.out.println(Lines.straightLine());

    ArrayList<String> options = new ArrayList<>(List.of("Go Back", "Update", "Remove", "Add Event", "Remove Event", "Show Events", "Show Ceritificates"));
    switch (type) {
      case "Student" -> {
        options.add("Add Interest");
        options.add("Remove Interest");
      }
    }
    String option = ParticipantForms.getOption(options, "What do you want to do?");
    if (option.equalsIgnoreCase("go back")) return;

    switch (option) {
      case "Update" -> update(type, cpf);
      case "Remove" -> remove(cpf);
      case "Add Event" -> addEvent(cpf);
      case "Remove Event" -> removeEvent(cpf);
      case "Show Events" -> showEvents(cpf);
      case "Show Certificates" -> showCertificates(cpf);
      default -> {
        switch (type) {
          case "Student" -> {
            switch (option) {
              case "Add Interest" -> studentMenuController.addInterest(participantManager, cpf);
              case "Remove Interest" -> studentMenuController.removeInterest(participantManager, cpf);
            }
          }
      }}
    }
  }

  public void showEvents(String cpf){
    if (participantManager.get(cpf) == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant not found!"));
      return;
    }
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.titleLine("Events with participant " + participantManager.get(cpf).getName(), Colors.BLUE_BOLD));
    System.out.println(Lines.doubleLine());
    LinkedHashMap<String, Event> events = eventManager.getEventsByParticipant(cpf);
    if(events.isEmpty()){
      System.out.println(Lines.leftText(participantManager.get(cpf).getName() + " has no events!"));
    }
    for (Event event : events.values()) {
      System.out.println(Lines.straightLine());
      System.out.print(event.toString());
      System.out.println(Lines.straightLine());
    }
  }

  public void clearAll(String type){
    switch (type) {
      case "Participant" -> {
          try {
            participantManager.clear(eventManager);
            System.out.println(Lines.clear());
            System.out.println(Lines.successLine("All participants removed!"));
          } catch (Exception e) {
            System.out.println(Lines.clear());
            System.out.println(Lines.errorLine(e.getMessage()));
          }
        }
      default -> {
        try {
          List<Participant> participantsOfType = participantManager.getAll().values()
          .stream()
          .filter(p -> p.getType().equalsIgnoreCase(type))
          .collect(Collectors.toList());

          for (Participant participant : participantsOfType) {
            participantManager.remove(participant.getCpf(), eventManager);
          }
          System.out.println(Lines.clear());
          System.out.println(Lines.successLine("All " + type + "s removed!"));
        } catch (Exception e) {
          System.out.println(Lines.clear());
          System.out.println(Lines.errorLine(e.getMessage()));
        }
      }
    }
  }
  
  public void addEvent(String cpf){
    if (participantManager.get(cpf) == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant not found!"));
      return;
    }

    String code = EventForms.getCode();
    if (code.equalsIgnoreCase("cancel")) return;

    Event event = eventManager.get(code);
    if (event == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Event not found!"));
      return;
    }
    try {
      eventManager.addParticipant(event.getCode(), cpf);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Participant added to event!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public void removeEvent(String cpf){
    if (participantManager.get(cpf) == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant not found!"));
      return;
    }

    String code = EventForms.getCode();
    if (code.equalsIgnoreCase("cancel")) return;

    if (eventManager.get(code) == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Event not found!"));
      return;
    }
    try {
      eventManager.removeParticipant(code, cpf);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Participant removed from event!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public void showCertificates(String cpf){
    //TODO: implement
  }
}
