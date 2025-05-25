package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import br.edu.ifba.inf0008.uniEvents.menu.submenu.events.EventForms;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.participants.External;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.model.participants.Student;
import br.edu.ifba.inf0008.uniEvents.model.participants.Teacher;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.AcademicStatus;
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

  public void create(String type){
    //TODO: implement automatically set type
    String selectedType = ParticipantForms.getType();
    if (selectedType.equalsIgnoreCase("cancel")) return;

    String name = ParticipantForms.getName();
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
    if (email.equals("cancel")) return;

    String phone = ParticipantForms.getPhone();
    if (phone.equals("cancel")) return;

    String birthDateString = ParticipantForms.getBirthDate();
    if (birthDateString.equals("cancel")) return;

    switch (selectedType) {
      // TODO: implement create student
      case "Student" -> participantManager.createStudent(name, cpf, email, phone, Utils.stringToDate(birthDateString), "Computer Science", 1, AcademicStatus.ACTIVE, 9.5, "IFBA", Utils.stringToDate("2022-01-01"));
      case "Teacher" -> participantManager.createTeacher(name, cpf, email, phone, Utils.stringToDate(birthDateString));
      case "External" -> participantManager.createExternal(name, cpf, email, phone, Utils.stringToDate(birthDateString));
    }

    System.out.println(Lines.clear());
    System.out.println(Lines.successLine("Participant created!"));
  }

  public void remove(){
    String cpf = ParticipantForms.getCpf(participantManager);
    if(cpf.equalsIgnoreCase("cancel")) return;

    if (participantManager.get(cpf) == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant not found!"));
      return;
    }
    try {
        participantManager.remove(cpf);
        System.out.println(Lines.clear());
        System.out.println(Lines.successLine("Participant removed!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public void update(String type){
    String cpf = ParticipantForms.getCpf(participantManager);
    if(cpf.equalsIgnoreCase("cancel")) return;

    Participant participant = participantManager.get(cpf);
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

    Participant updatedParticipant = null;
    switch (participant.getType()) {
      // TODO: implement update student
      case "Student" -> updatedParticipant = new Student(name, cpf, email, phone, Utils.stringToDate(birthDateString), "Computer Science", 1, AcademicStatus.ACTIVE, 9.5, "IFBA", Utils.stringToDate("2022-01-01"));
      case "Teacher" -> updatedParticipant = new Teacher(name, cpf, email, phone, Utils.stringToDate(birthDateString));
      case "External" -> updatedParticipant = new External(name, cpf, email, phone, Utils.stringToDate(birthDateString));
    }

    try {
      participantManager.update(participant, updatedParticipant);
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

  public void listByType(){
    String selectedType = ParticipantForms.getType();
    if(selectedType.equalsIgnoreCase("cancel")) return;

    LinkedHashMap<String, Participant> participants = participantManager.getAll();

    ArrayList<Participant> filteredParticipants = new ArrayList<>();
    for (Participant participant : participants.values()) {
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

  public void get(){
    //TODO: implement get Participant
  }

  public void showEvents(){
    String cpf = ParticipantForms.getCpf(participantManager);
    if(cpf.equalsIgnoreCase("cancel")) return;

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

  public void clearAll(){
    try {
      participantManager.clear(eventManager);
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

    Participant participant = participantManager.get(cpf);
    if (participant == null) {
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

    if (participantManager.get(cpf) == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant not found!"));
      return;
    }

    System.out.print("Enter event code (\"cancel\" to exit)>> ");
    String code = Utils.scanner.nextLine();
    if (code.equals("cancel")) return;

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

  public void addInterest(){
    //TODO: implement add interest
  }

  public void removeInterest(){
    //TODO: implement remove interest
  }
}
