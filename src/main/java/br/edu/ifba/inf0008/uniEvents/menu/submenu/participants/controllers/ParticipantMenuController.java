package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifba.inf0008.uniEvents.menu.submenu.events.controllers.EventForms;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.participants.External;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;

public class ParticipantMenuController {
  private ParticipantManager participantManager;
  private EventManager eventManager;

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
      case "Student" -> created = StudentMenuController.create(participantManager, name, cpf, email, phone, birthDateString);
      case "Professor" -> created = ProfessorMenuController.create(participantManager, name, cpf, email, phone, birthDateString);
      case "External" -> created = ExternalMenuController.create(participantManager, name, cpf, email, phone, birthDateString);
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
      case "Student" -> updatedParticipant = StudentMenuController.update(participantManager, name, cpf, email, phone, birthDateString);
      case "Professor" -> updatedParticipant = ProfessorMenuController.update(participantManager, name, cpf, email, phone, birthDateString);
      case "External" -> updatedParticipant = ExternalMenuController.update(participantManager, name, cpf, email, phone, birthDateString);
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
  
  public void list(){
    ArrayList<Participant> participants = new ArrayList<>( participantManager.getAll().values());

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

  public void list(String type){
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

  public void get(String type){
    String cpf = ParticipantForms.getCpf(participantManager);
    if(cpf.equalsIgnoreCase("cancel")) return;

    if (participantManager.get(cpf) == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant not found!"));
      return;
    }
    
    if (!type.equalsIgnoreCase("Participant") && !participantManager.get(cpf).getType().equalsIgnoreCase(type)) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(type + " not found!"));
      return;
    }
    
    if(type.equalsIgnoreCase("Participant")) type = participantManager.get(cpf).getType();
    
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.titleLine(type, Colors.BLUE_BOLD));
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.straightLine());
    System.out.print(participantManager.get(cpf).toString());
    System.out.println(Lines.straightLine());

    ArrayList<String> options = new ArrayList<>(List.of("Go Back", "Update", "Remove", "Add To Event", "Remove From Event", "Show Events", "Show Ceritificates"));
    switch (type) {
      case "Student" -> {
        options.add("Add Interest");
        options.add("Remove Interest");
      }
      case "Professor" -> {
        options.add("Add Research Area");
        options.add("Remove Research Area");
        options.add("Add Publication");
        options.add("Remove Publication");
      }
      case "External" -> {
        options.add("Show Biography");
        options.add("Add Expertise Area");
        options.add("Remove Expertise Area");
      }
    }
    String option = ParticipantForms.getOption(options, "What do you want to do?");
    if (option.equalsIgnoreCase("go back")) return;

    switch (option) {
      case "Update" -> update(type, cpf);
      case "Remove" -> remove(cpf);
      case "Add To Event" -> addToEvent(cpf);
      case "Remove From Event" -> removeFromEvent(cpf);
      case "Show Events" -> showEvents(cpf);
      case "Show Certificates" -> showCertificates(cpf);
      default -> {
        switch (type) {
          case "Student" -> {
            switch (option) {
              case "Add Interest" -> StudentMenuController.addInterest(participantManager, cpf);
              case "Remove Interest" -> StudentMenuController.removeInterest(participantManager, cpf);
            }
          }
          case "Professor" -> {
            switch (option) {
              case "Add Research Area" -> ProfessorMenuController.addResearchArea(participantManager, cpf);
              case "Remove Research Area" -> ProfessorMenuController.removeResearchArea(participantManager, cpf);
              case "Add Publication" -> ProfessorMenuController.addPublication(participantManager, cpf);
              case "Remove Publication" -> ProfessorMenuController.removePublication(participantManager, cpf);
            }
          }
          case "External" -> {
            switch (option) {
              case "Show Biography" -> System.out.println(((External)participantManager.get(cpf)).getFormatedBio());
              case "Add Expertise Area" -> ExternalMenuController.addExpertiseArea(participantManager, cpf);
              case "Remove Expertise Area" -> ExternalMenuController.removeExpertiseArea(participantManager, cpf);
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
    System.out.println(Lines.titleLine("Events with " + participantManager.get(cpf).getType() + " " + participantManager.get(cpf).getName(), Colors.BLUE_BOLD));
    System.out.println(Lines.doubleLine());
    LinkedHashMap<String, Event> events = eventManager.getEventsByParticipant(cpf);
    if(events.isEmpty()){
      System.out.println(Lines.multiLineText(participantManager.get(cpf).getName() + " has no events!"));
    }
    for (Event event : events.values()) {
      System.out.println(Lines.straightLine());
      System.out.print(event.toString());
      System.out.println(Lines.straightLine());
    }
  }

  public void clear(){
    try {
      participantManager.clear(eventManager);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("All participants removed!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public void clear(String type){
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
  
  public void addToEvent(String cpf){
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
      System.out.println(Lines.successLine(participantManager.get(cpf).getType() + " added to event!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public void removeFromEvent(String cpf){
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
      System.out.println(Lines.successLine(participantManager.get(cpf).getType() + " removed from event!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public void showCertificates(String cpf){
    //TODO: implement showCertificates
  }
}
