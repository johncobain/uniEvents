package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants;

import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.menu.submenu.BaseMenu;
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

  BaseMenu baseMenu;
  ArrayList<String> participantTypes = new ArrayList<>();
  {
    participantTypes.add("Cancel");
    participantTypes.add("Student");
    participantTypes.add("Teacher");
    participantTypes.add("External");
  }

  public void setParticipantManager(ParticipantManager participantManager) {
    this.participantManager = participantManager;
  }

  public void setEventManager(EventManager eventManager) {
    this.eventManager = eventManager;
  }

  public Boolean create(){
    baseMenu = new BaseMenu("Select participant type",  participantTypes);
    int response = baseMenu.getResponse();

    if (response == 0) {
      return false;
    }

    String selectedType = participantTypes.get(response);

    System.out.print("Enter participant name >> ");
    String name = Utils.scanner.nextLine();
    System.out.print("Enter participant CPF >> ");
    String cpf = Utils.scanner.nextLine();
    System.out.print("Enter participant email >> ");
    String email = Utils.scanner.nextLine();
    System.out.print("Enter participant phone >> ");
    String phone = Utils.scanner.nextLine();
    System.out.print("Enter participant birth date (dd/MM/yyyy) >> ");
    String birthDateString = Utils.scanner.nextLine();

    switch (selectedType) {
      case "Student" -> participantManager.createStudent(name, cpf, email, phone, Utils.stringToDate(birthDateString));
      case "Teacher" -> participantManager.createTeacher(name, cpf, email, phone, Utils.stringToDate(birthDateString));
      case "External" -> participantManager.createExternal(name, cpf, email, phone, Utils.stringToDate(birthDateString));
    }

    return true;
  }

  public Boolean remove(){
    System.out.print("Enter participant CPF >> ");
    String cpf = Utils.scanner.nextLine();
    Participant participant = participantManager.getParticipant(cpf);
    if (participant == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.straightLine());
      System.out.println(Lines.errorLine("Participant not found!"));
      System.out.println(Lines.straightLine());
      return false;
    }
    try {
        participantManager.removeParticipant(participant);
        return true;
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.straightLine());
      System.out.println(Lines.errorLine(e.getMessage()));
      System.out.println(Lines.straightLine());
      return false;
    }
  }

  public Boolean update(){
    System.out.print("Enter participant CPF >> ");
    String cpf = Utils.scanner.nextLine();
    Participant participant = participantManager.getParticipant(cpf);
    if (participant == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.straightLine());
      System.out.println(Lines.errorLine("Participant not found!"));
      System.out.println(Lines.straightLine());
      return false;
    }
    System.out.print("Enter participant name >> ");
    String name = Utils.scanner.nextLine();
    System.out.print("Enter participant email >> ");
    String email = Utils.scanner.nextLine();
    System.out.print("Enter participant phone >> ");
    String phone = Utils.scanner.nextLine();
    System.out.print("Enter participant birth date (dd/MM/yyyy) >> ");
    String birthDateString = Utils.scanner.nextLine();
    try {
      participantManager.updateParticipant(participant, name, email, phone, Utils.stringToDate(birthDateString));
      return true;
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.straightLine());
      System.out.println(Lines.errorLine(e.getMessage()));
      System.out.println(Lines.straightLine());
      return false;
    }
  }
  
  public void listAll(){
    ArrayList<Participant> participants = participantManager.getAllParticipants();

    if(participants.isEmpty()){
      System.out.println(Lines.clear());
      System.out.println(Lines.straightLine());
      System.out.println(Lines.errorLine("No participants found!"));
      System.out.println(Lines.straightLine());
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
    baseMenu = new BaseMenu("Select participant type",  participantTypes);
    int response = baseMenu.getResponse();
    
    if (response == 0) {
      return;
    }

    String selectedType = participantTypes.get(response);
    ArrayList<Participant> participants = participantManager.getAllParticipants();
    ArrayList<Participant> filteredParticipants = new ArrayList<>();
    for (Participant participant : participants) {
      if (participant.getType().equals(selectedType)) {
        filteredParticipants.add(participant);
      }
    }
    if(filteredParticipants.isEmpty()){
      System.out.println(Lines.clear());
      System.out.println(Lines.straightLine());
      System.out.println(Lines.errorLine("No participants found!"));
      System.out.println(Lines.straightLine());
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
    System.out.print("Enter participant CPF >> ");
    String cpf = Utils.scanner.nextLine();
    Participant participant = participantManager.getParticipant(cpf);
    if (participant == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.straightLine());
      System.out.println(Lines.errorLine("Participant not found!"));
      System.out.println(Lines.straightLine());
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

  public Boolean clearAll(){
    try {
      participantManager.clearAllParticipants(eventManager);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
    public Boolean addToEvent(){
      System.out.print("Enter participant CPF >> ");
      String cpf = Utils.scanner.nextLine();
      Participant participant = participantManager.getParticipant(cpf);
      if (participant == null) {
        System.out.println(Lines.clear());
        System.out.println(Lines.straightLine());
        System.out.println(Lines.errorLine("Participant not found!"));
        System.out.println(Lines.straightLine());
        return false;
      }
      System.out.print("Enter event code >> ");
      String code = Utils.scanner.nextLine();
      Event event = eventManager.getEvent(code);
      if (event == null) {
        System.out.println(Lines.clear());
        System.out.println(Lines.straightLine());
        System.out.println(Lines.errorLine("Event not found!"));
        System.out.println(Lines.straightLine());
        return false;
      }
      try {
        eventManager.addParticipant(event.getCode(), participant.getCpf());
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.straightLine());
        System.out.println(Lines.errorLine(e.getMessage()));
        System.out.println(Lines.straightLine());
        return false;
      }    
      return true;
    }

    public Boolean removeFromEvent(){
      System.out.print("Enter participant CPF >> ");
      String cpf = Utils.scanner.nextLine();
      Participant participant = participantManager.getParticipant(cpf);
      if (participant == null) {
        System.out.println(Lines.clear());
        System.out.println(Lines.straightLine());
        System.out.println(Lines.errorLine("Participant not found!"));
        System.out.println(Lines.straightLine());
        return false;
      }
      System.out.print("Enter event code >> ");
      String code = Utils.scanner.nextLine();
      Event event = eventManager.getEvent(code);
      if (event == null) {
        System.out.println(Lines.clear());
        System.out.println(Lines.straightLine());
        System.out.println(Lines.errorLine("Event not found!"));
        System.out.println(Lines.straightLine());
        return false;
      }
      try {
        eventManager.removeParticipant(event.getCode(), participant.getCpf());
        return true;
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.straightLine());
        System.out.println(Lines.errorLine(e.getMessage()));
        System.out.println(Lines.straightLine());
        return false;
      }
    }
}
