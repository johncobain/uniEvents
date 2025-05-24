package br.edu.ifba.inf0008.uniEvents.menu.submenu.events;

import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.menu.submenu.BaseMenu;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.Modality;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class EventMenuController {
  private EventManager eventManager;

  BaseMenu baseMenu;
  ArrayList<String> eventTypes = new ArrayList<>();
  {
    eventTypes.add("Cancel");
    eventTypes.add("Lecture");
    eventTypes.add("Workshop");
    eventTypes.add("Short Course");
    eventTypes.add("Academic Fair");
  }
  
  public void setEventManager(EventManager eventManager) {
    this.eventManager = eventManager;
  }
  
  public Boolean create(){
    baseMenu = new BaseMenu("Select event type", eventTypes);
    int response = baseMenu.getResponse();

    if (response == 0) {
      return false;
    }

    String selectedType = eventTypes.get(response);

    System.out.print("Enter event name >> ");
    String name = Utils.scanner.nextLine();
    System.out.print("Enter event location >> ");
    String location = Utils.scanner.nextLine();
    System.out.print("Enter event description >> ");
    String description = Utils.scanner.nextLine();
    System.out.print("Enter event date (dd/MM/yyyy) >> ");
    String date = Utils.scanner.nextLine();
    int capacity;
    do { 
      System.out.print("Enter event capacity >> ");
      String capacityStr = Utils.scanner.nextLine();
      try {
        capacity = Integer.parseInt(capacityStr);
        if (capacity <= 0) {
          throw new Exception("Capacity must be greater than 0!");
        }
        break;
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.straightLine());
        System.out.println(Lines.errorLine(e.getMessage()));
        System.out.println(Lines.straightLine());
      }
    } while (true);
    ArrayList<String> modalities = new ArrayList<>();
    modalities.add(Modality.INPERSON.toString());
    modalities.add(Modality.ONLINE.toString());
    modalities.add(Modality.HYBRID.toString());
    baseMenu = new BaseMenu("Select event modality", modalities);
    int modalityResponse = baseMenu.getResponse();

    Modality modality;
    switch (modalityResponse) {
      case 0 -> modality = Modality.INPERSON;
      case 1 -> modality = Modality.ONLINE;
      case 2 -> modality = Modality.HYBRID;
      default -> modality = Modality.INPERSON;
    }
    
    String code;
    do { 
      System.out.print("Enter event code >> ");
      code = Utils.scanner.nextLine();
      if(eventManager.isCodeAlreadyInUse(code)){
        System.out.println(Lines.clear());
        System.out.println(Lines.straightLine());
        System.out.println(Lines.errorLine("Code '"+code+"' already in use!"));
        System.out.println(Lines.straightLine());
        continue;
      }
      if (code.isEmpty()) {
        System.out.println(Lines.clear());
        System.out.println(Lines.straightLine());
        System.out.println(Lines.errorLine("Code cannot be empty!"));
        System.out.println(Lines.straightLine());
        continue;
      }
      break;
    } while (true);
    switch (selectedType) {
      case "Lecture" -> eventManager.createLecture(name, description, location, Utils.stringToDate(date), capacity, modality, code);
      case "Workshop" -> eventManager.createWorkshop(name, description, location, Utils.stringToDate(date), capacity, modality, code);

      case "Short Course" -> eventManager.createShortCourse(name, description, location, Utils.stringToDate(date), capacity, modality, code);

      case "Academic Fair" -> eventManager.createAcademicFair(name, description, location, Utils.stringToDate(date), capacity, modality, code);

    }

    return true;
  }

  public Boolean remove(){
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
      eventManager.removeEvent(event);
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
    System.out.print("Enter event name >> ");
    String name = Utils.scanner.nextLine();
    System.out.print("Enter event location >> ");
    String location = Utils.scanner.nextLine();
    System.out.print("Enter event description >> ");
    String description = Utils.scanner.nextLine();
    System.out.print("Enter event date (dd/MM/yyyy) >> ");
    String date = Utils.scanner.nextLine();
    int capacity;
    do { 
      System.out.print("Enter event capacity >> ");
      String capacityStr = Utils.scanner.nextLine();
      try {
        capacity = Integer.parseInt(capacityStr);
        if (capacity <= 0) {
          throw new Exception("Capacity must be greater than 0!");
        }
        break;
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.straightLine());
        System.out.println(Lines.errorLine(e.getMessage()));
        System.out.println(Lines.straightLine());
      }
    } while (true);
    ArrayList<String> modalities = new ArrayList<>();
    modalities.add(Modality.INPERSON.toString());
    modalities.add(Modality.ONLINE.toString());
    modalities.add(Modality.HYBRID.toString());
    baseMenu = new BaseMenu("Select event modality", modalities);
    int modalityResponse = baseMenu.getResponse();
    Modality modality;
    switch (modalityResponse) {
      case 0 -> modality = Modality.INPERSON;
      case 1 -> modality = Modality.ONLINE;
      case 2 -> modality = Modality.HYBRID;
      default -> modality = Modality.INPERSON;
    }
    
    eventManager.updateEvent(event, name, location, description, Utils.stringToDate(date), capacity, modality);

    return true;
  }

  public void listAll(){
    ArrayList<Event> events = eventManager.getAllEvents();

    if (events.isEmpty()) {
      System.out.println(Lines.clear());
      System.out.println(Lines.straightLine());
      System.out.println(Lines.errorLine("No events found!"));
      System.out.println(Lines.straightLine());
      return;
    }
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.titleLine("All Events", Colors.YELLOW_BOLD));
    System.out.println(Lines.doubleLine());
    for (Event event : events) {
      System.out.println(Lines.straightLine());
      System.out.print(event.toString());
      System.out.println(Lines.straightLine());
    }
  }

  public void listByType(){
    baseMenu = new BaseMenu("Select event type", eventTypes);
    int response = baseMenu.getResponse();

    if (response == 0) {
      return;
    }

    String selectedType = eventTypes.get(response);
    ArrayList<Event> events = eventManager.getAllEvents();
    ArrayList<Event> filteredEvents = new ArrayList<>();
    for (Event event : events) {
      if (event.getType().equals(selectedType)) {
        filteredEvents.add(event);
      }
    }
    if(filteredEvents.isEmpty()){
      System.out.println(Lines.clear());
      System.out.println(Lines.straightLine());
      System.out.println(Lines.errorLine("No events in " + selectedType + " found!"));
      System.out.println(Lines.straightLine());
      return;
    }
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.titleLine(selectedType+" Events", Colors.YELLOW_BOLD));
    System.out.println(Lines.doubleLine());
    for (Event event : filteredEvents) {
      System.out.println(Lines.straightLine());
      System.out.print(event.toString());
      System.out.println(Lines.straightLine());
    }
  }

  public void showParticipants(){
    System.out.print("Enter event code >> ");
    String code = Utils.scanner.nextLine();
    Event event = eventManager.getEvent(code);
    if (event == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.straightLine());
      System.out.println(Lines.errorLine("Event not found!"));
      System.out.println(Lines.straightLine());
      return;
    }
    ArrayList<Participant> participants = event.getParticipants();
    if (participants.isEmpty()) {
      System.out.println(Lines.clear());
      System.out.println(Lines.straightLine());
      System.out.println(Lines.errorLine("No participants found!"));
      System.out.println(Lines.straightLine());
      return;
    }
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.titleLine("Event", Colors.YELLOW_BOLD));
    System.out.println(Lines.doubleLine());
    System.out.println(event.toString());
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.titleLine("Participants", Colors.YELLOW_BOLD));
    System.out.println(Lines.doubleLine());
    for (Participant participant : participants) {
      System.out.println(Lines.straightLine());
      System.out.println(Lines.leftText(String.format("    Participant: %s", participant.getName())));
      System.out.println(Lines.leftText(String.format("    CPF: %s", participant.getCpf())));
      System.out.println(Lines.leftText(String.format("    Email: %s", participant.getEmail())));
      System.out.println(Lines.leftText(String.format("    Phone: %s", participant.getPhone())));
      System.out.println(Lines.leftText(String.format("    Birthdate: %s", participant.getBirthDate())));
      System.out.println(Lines.straightLine());
    }
  }

  public Boolean clearAll(){
    try {
      eventManager.clearAllEvents();
      return true;   
    } catch (Exception e) {
      return false;
    }
  } 
}
