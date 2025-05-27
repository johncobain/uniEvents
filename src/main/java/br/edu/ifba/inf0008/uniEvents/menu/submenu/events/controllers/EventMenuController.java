package br.edu.ifba.inf0008.uniEvents.menu.submenu.events.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.controllers.ParticipantForms;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.ShortCourse;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.services.ReportsManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;

public class EventMenuController {
  private final EventManager eventManager;
  private final ParticipantManager participantManager;

  public EventMenuController(EventManager eventManager, ParticipantManager participantManager) {
    this.eventManager = eventManager;
    this.participantManager = participantManager;
  }
  
  public void create(String type){
    String name = EventForms.getText("Name");
    if (name.equalsIgnoreCase("cancel")) return;

    String location = EventForms.getText("Location");
    if (location.equalsIgnoreCase("cancel")) return;

    String description = EventForms.getText("Description");
    if (description.equalsIgnoreCase("cancel")) return;

    String date = EventForms.getDate();
    if (date.equalsIgnoreCase("cancel")) return;

    int capacity = EventForms.getNumber("Capacity");
    if (capacity == -1) return;

    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    for(Modality modality : Modality.getAll())options.add(modality.getDescription());

    String modality = EventForms.getOption(options, "Modality");
    if (modality.equalsIgnoreCase("cancel")) return;
    
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

    Boolean created = false;
    switch (type) {
      case "Lecture" -> created = LectureMenuController.create(eventManager, name, description, location, date, capacity, modality, code);
      case "Workshop" -> created = WorkshopMenuController.create(eventManager, name, description, location, date, capacity, modality, code);
      case "Short Course" -> created = ShortCourseMenuController.create(eventManager, participantManager, name, description, location, date, capacity, modality, code);
      case "Academic Fair" -> created = AcademicFairMenuController.create(eventManager, name, description, location, date, capacity, modality, code);
    }

    if(!created)return;

    System.out.println(Lines.clear());
    System.out.println(Lines.successLine("Event created!"));
  }

  public Boolean remove(String code){
    String confirmation = EventForms.getYN("Are you sure you want to remove this event?", "n");
    if (confirmation.equalsIgnoreCase("n")) {
      System.out.println(Lines.clear());
      System.out.println(Lines.warningLine("Event not removed!"));
      return false;
    }

    try {
      eventManager.remove(code);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Event removed!"));
      return true;
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
      return false;
    }

  }

  public void update(String type, String code){
    if (eventManager.get(code) == null) {
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
    
    int capacity = EventForms.getNumber("Capacity");
    if (capacity == -1) return;

    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    for(Modality modality : Modality.getAll())options.add(modality.getDescription());

    String modality = EventForms.getOption(options, "Modality");
    if (modality.equalsIgnoreCase("cancel")) return;
    
    Event updatedEvent = null;
    switch (type) {
      case "Lecture" -> updatedEvent = LectureMenuController.update(eventManager, code, name, description, location, date, capacity, modality);
      case "Workshop" -> updatedEvent = WorkshopMenuController.update(eventManager, code, name, description, location, date, capacity, modality);
      case "Short Course" -> updatedEvent = ShortCourseMenuController.update(eventManager, participantManager, code, name, description, location, date, capacity, modality);
      case "Academic Fair" -> updatedEvent = AcademicFairMenuController.update(eventManager, code, name, description, location, date, capacity, modality);
    }

    if(updatedEvent == null) return;

    try {
      eventManager.update(code, updatedEvent);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Event updated!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Error updating event"));
    }

  }

  public void list(){
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

  public void list(String type){
    List<Event> events = eventManager.getAll().values()
      .stream()
      .filter(e -> e.getType().equalsIgnoreCase(type))
      .collect(Collectors.toList());

    if(events.isEmpty()){
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("No events in " + type + " found!"));
      return;
    }

    System.out.println(Lines.doubleLine());
    System.out.println(Lines.titleLine(type+" Events", Colors.YELLOW_BOLD));
    System.out.println(Lines.doubleLine());
    for (Event event : events) {
      System.out.print(ReportsManager.summary(event, false));
    }
  }

  public void get(String type){
    String code = EventForms.getCode();
    if (code.equalsIgnoreCase("cancel")) return;

    if (eventManager.get(code) == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Event not found!"));
      return;
    }

    if(!type.equalsIgnoreCase("Event") && !eventManager.get(code).getType().equalsIgnoreCase(type)){
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Event is not a " + type + "!"));
      return;
    }

    if(type.equalsIgnoreCase("Event")) type = eventManager.get(code).getType();

    System.out.print(ReportsManager.summary(eventManager.get(code), false));
    
    ArrayList<String> options = new ArrayList<>(List.of("Go Back", "Update", "Remove", "List Participants", "Add Participant", "Remove Participant", "Clear Participants", "Generate Certificate"));
    switch (type) {
      // case "Lecture" -> options.add("View Slides");
      // case "Workshop" -> options.add("View Materials");
      // case "Short Course" -> options.add("View Materials");
      // case "Academic Fair" -> options.add("View Stands");
      default -> System.out.println();
    }
    String option;
    while (true) { 
      option = EventForms.getOption(options, "What do you want to do with this " + type + "?");
      if (option.equalsIgnoreCase("cancel")) return;
      
      switch (option) {
        case "Update" -> update(type, code);
        case "Remove" -> {
          if(remove(code)) return;
        }
        case "List Participants" -> listParticipants(code);
        case "Add Participant" -> addParticipant(code);
        case "Remove Participant" -> removeParticipant(code);
        case "Clear Participants" -> clearParticipants(code);
        case "Generate Certificate" -> generateCertificate(code);
        default -> {
          switch (type) {
            case "Lecture" -> {
            }
            case "Workshop" -> {
            }
            case "Short Course" -> {
            }
            case "Academic Fair" -> {
            }
          }
        }
      }
    }
  }

  public void clear(){
    try {
      eventManager.clear();
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("All events removed!")); 
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  } 

  public void clear(String type){
    try{
      List<Event> eventsOfType = eventManager.getAll().values()
      .stream()
      .filter(e -> e.getType().equalsIgnoreCase(type))
      .collect(Collectors.toList());

      for(Event event : eventsOfType) {
        eventManager.remove(event.getCode());
      }
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("All " + type + "s removed!"));
    }catch(Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public void listParticipants(String code){
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.titleLine("Participants of " + eventManager.get(code).getName() + " (" + eventManager.get(code).getCode() + ")", Colors.YELLOW_BOLD));
    System.out.println(Lines.doubleLine());
    List<Participant> participants = eventManager.get(code).getParticipants().values()
      .stream()
      .sorted((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()))
      .collect(Collectors.toList());
    if (participants.isEmpty()) {
      System.out.println(Lines.errorLine(eventManager.get(code).getName() + " has no participants!"));
      return;
    }
    for (Participant participant : participants) {
      System.out.println(Lines.straightLine());
      System.out.print(participant.toString());
      System.out.println(Lines.straightLine());
    }
  }

  public void addParticipant(String code){
    String cpf = ParticipantForms.getCpf();
    if (cpf.equalsIgnoreCase("cancel")) return;

    if(participantManager.get(cpf) == null){
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant not found!"));
      return;
    }

    if(eventManager.get(code).isParticipantRegistered(cpf)){
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant with CPF " + cpf + " is already registered in this event!"));
      return;      
    }

    if(
      participantManager.get(cpf).getType().equalsIgnoreCase("Student") && 
      eventManager.get(code).getType().equalsIgnoreCase("Short Course") &&      
      !((ShortCourse)eventManager.get(code)).checkEligibility(participantManager, cpf)
      ){
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant " + participantManager.get(cpf).getName() + " is not eligible to register in this event!"));
      return;      
    }

    try {
      eventManager.addParticipant(code, cpf);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Participant added!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public void removeParticipant(String code){
    String cpf = ParticipantForms.getCpf();
    if (cpf.equalsIgnoreCase("cancel")) return;

    if(eventManager.get(code).isParticipantRegistered(cpf) == false){
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant with CPF " + cpf + " is not registered in this event!"));
      return;      
    }

    String confirmation = EventForms.getYN("Are you sure you want to remove this participant?", "n");
    if (confirmation.equalsIgnoreCase("n")) {
      System.out.println(Lines.clear());
      System.out.println(Lines.warningLine("Participant not removed!"));
      return;
    }

    try {
      eventManager.removeParticipant(code, cpf);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Participant removed!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public void clearParticipants(String code){
    String confirmation = EventForms.getYN("Are you sure you want to remove all participants?", "n");
    if (confirmation.equalsIgnoreCase("n")) {
      System.out.println(Lines.clear());
      System.out.println(Lines.warningLine("Participants not removed!"));
      return;
    }
    try {
      eventManager.clearParticipants(code);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("All participants removed!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public void generateCertificate(String code){
    //TODO: implement generateCertificate
  }
}
