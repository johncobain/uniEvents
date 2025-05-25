package br.edu.ifba.inf0008.uniEvents.menu.submenu.events;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import br.edu.ifba.inf0008.uniEvents.model.events.AcademicFair;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.Lecture;
import br.edu.ifba.inf0008.uniEvents.model.events.ShortCourse;
import br.edu.ifba.inf0008.uniEvents.model.events.Workshop;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.ReportsManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class EventMenuController {
  private EventManager eventManager;
  
  public void setEventManager(EventManager eventManager) {
    this.eventManager = eventManager;
  }
  
  public void create(){
    String selectedType = EventForms.getType();
    if (selectedType.equalsIgnoreCase("cancel")) return;

    String name = EventForms.getText("Name");
    if (name.equalsIgnoreCase("cancel")) return;

    String location = EventForms.getText("Location");
    if (location.equalsIgnoreCase("cancel")) return;

    String description = EventForms.getText("Description");
    if (description.equalsIgnoreCase("cancel")) return;

    String date = EventForms.getDate();
    if (date.equalsIgnoreCase("cancel")) return;

    int capacity = EventForms.getCapacity();
    if (capacity == -1) return;

    int modalityResponse = EventForms.getModality();
    if (modalityResponse == 0) return;

    Modality modality;
    switch (modalityResponse) {
      case 1 -> modality = Modality.INPERSON;
      case 2 -> modality = Modality.ONLINE;
      case 3 -> modality = Modality.HYBRID;
      default -> modality = Modality.INPERSON;
    }
    
    String code;
    while (true) { 
      code = EventForms.getCode();
      if(eventManager.isCodeAlreadyInUse(code)){
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine("Code '"+code+"' is already in use!"));
        continue;
      }
      break;
    }
    if(code.equalsIgnoreCase("cancel")) return;

    switch (selectedType) {
      case "Lecture" -> eventManager.createLecture(name, description, location, Utils.stringToDate(date), capacity, modality, code);
      case "Workshop" -> eventManager.createWorkshop(name, description, location, Utils.stringToDate(date), capacity, modality, code);

      case "Short Course" -> eventManager.createShortCourse(name, description, location, Utils.stringToDate(date), capacity, modality, code);

      case "Academic Fair" -> eventManager.createAcademicFair(name, description, location, Utils.stringToDate(date), capacity, modality, code);

    }

    System.out.println(Lines.clear());
    System.out.println(Lines.successLine("Event created!"));
  }

  public void remove(){
    String code = EventForms.getCode();
    if (code.equalsIgnoreCase("cancel")) return;

    if (eventManager.get(code) == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Event not found!"));
      return;
    }
    try {
      eventManager.remove(code);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Event removed!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }

  }

  public void update(){
    String code = EventForms.getCode();
    if (code.equalsIgnoreCase("cancel")) return;

    Event event = eventManager.get(code);
    if (event == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Event not found!"));
      return;
    }

    String name = EventForms.getText("Name");
    if (name.equalsIgnoreCase("cancel")) return;

    String location = EventForms.getText("Location");
    if (location.equalsIgnoreCase("cancel")) return;

    String description = EventForms.getText("Description");
    if (description.equalsIgnoreCase("cancel")) return;

    String date = EventForms.getDate();
    if (date.equalsIgnoreCase("cancel")) return;
    
    int capacity = EventForms.getCapacity();
    if (capacity == -1) return;

    int modalityResponse = EventForms.getModality();
    if (modalityResponse == 0) return;

    Modality modality;
    switch (modalityResponse) {
      case 1 -> modality = Modality.INPERSON;
      case 2 -> modality = Modality.ONLINE;
      case 3 -> modality = Modality.HYBRID;
      default -> modality = Modality.INPERSON;
    }
    
    Event updatedEvent = null;
    switch (event.getType()) {
      case "Lecture" -> updatedEvent = new Lecture(name, description, location, Utils.stringToDate(date), capacity, modality, event.getCode());
      case "Workshop" -> updatedEvent = new Workshop(name, description, location, Utils.stringToDate(date), capacity, modality, event.getCode());
      case "Short Course" -> updatedEvent = new ShortCourse(name, description, location, Utils.stringToDate(date), capacity, modality, event.getCode());
      case "Academic Fair" -> updatedEvent = new AcademicFair(name, description, location, Utils.stringToDate(date), capacity, modality, event.getCode());
    }

    try {
      eventManager.update(event, updatedEvent);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Event updated!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Error updating event"));
    }

  }

  public void listAll(){
    ArrayList<Event> events = new ArrayList<>(eventManager.getAll().values());

    if (events.isEmpty()) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("No events found!"));
      return;
    }
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.titleLine("All Events", Colors.YELLOW_BOLD));
    System.out.println(Lines.doubleLine());
    for(Event event : events){
      System.out.print(ReportsManager.summary(event, false));
    }
  }

  public void listByType(){
    String selectedType = EventForms.getType();
    if (selectedType.equalsIgnoreCase("cancel")) return;

    LinkedHashMap<String, Event> events = eventManager.getAll();
    ArrayList<Event> filteredEvents = new ArrayList<>();
    for (Event event : events.values()) {
      if (event.getType().equals(selectedType)) {
        filteredEvents.add(event);
      }
    }
    if(filteredEvents.isEmpty()){
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("No events in " + selectedType + " found!"));
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

  public void show(){
    String code = EventForms.getCode();
    if (code.equalsIgnoreCase("cancel")) return;

    Event event = eventManager.get(code);
    if (event == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Event not found!"));
      return;
    }
    LinkedHashMap<String, Participant> participants = event.getParticipants();
    if (participants.isEmpty()) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("No participants found!"));
      return;
    }
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.titleLine("Event", Colors.YELLOW_BOLD));
    System.out.println(Lines.doubleLine());
    System.out.println(event.toString());
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.titleLine("Participants", Colors.YELLOW_BOLD));
    System.out.println(Lines.doubleLine());
    for (Participant participant : participants.values()) {
      System.out.println(Lines.straightLine());
      System.out.println(Lines.leftText(String.format("    Participant: %s", participant.getName())));
      System.out.println(Lines.leftText(String.format("    CPF: %s", participant.getCpf())));
      System.out.println(Lines.leftText(String.format("    Email: %s", participant.getEmail())));
      System.out.println(Lines.leftText(String.format("    Phone: %s", participant.getPhone())));
      System.out.println(Lines.leftText(String.format("    Birthdate: %s", participant.getBirthDate())));
      System.out.println(Lines.straightLine());
    }
  }

  public void clearAll(){
    try {
      eventManager.clear();
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("All events removed!")); 
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  } 
}
