package br.edu.ifba.inf0008.uniEvents.menu.submenu.events.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifba.inf0008.uniEvents.exceptions.UniEventsException;
import br.edu.ifba.inf0008.uniEvents.forms.CommonForms;
import br.edu.ifba.inf0008.uniEvents.forms.EventForms;
import br.edu.ifba.inf0008.uniEvents.forms.ParticipantForms;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.services.IManager;
import br.edu.ifba.inf0008.uniEvents.services.ReportsGenerator;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;

public class EventMenuController {
  private final IManager<Event> eventManager;
  private final IManager<Participant> participantManager;

  public EventMenuController(IManager<Event> eventManager, IManager<Participant> participantManager) {
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

    String date = CommonForms.getDate("Event date");
    if (date.equalsIgnoreCase("cancel")) return;
    
    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    for(Modality modality : Modality.getAll())options.add(modality.getDescription());
    
    String modality = CommonForms.getOption(options, "Select the Modality");
    if (modality.equalsIgnoreCase("cancel")) return;

    double totalHours = EventForms.getDouble("Total hours");
    if(totalHours == -1) return;

    int capacity = 0;
    if(Modality.fromDescription(modality) != Modality.ONLINE){
      capacity = EventForms.getNumber("Capacity");
    }
    if (capacity == -1) return;
    
    String code;
    while (true) { 
      code = EventForms.getCode();
      Boolean isUnique = true;
      for(Event event : eventManager.getAll().values()){
        if(event.getCode().equals(code)){
          System.out.println(Lines.clear());
          System.out.println(Lines.errorLine("Code '"+code+"' is already in use!"));
          isUnique = false;
          break;
        }
      }
      if(isUnique) break;
    }
    if(code.equalsIgnoreCase("cancel")) return;

    Event createdEvent = null;
    switch (type) {
      case "Lecture" -> createdEvent = LectureMenuController.getForm(eventManager, name, description, location, date, capacity, modality, totalHours, code);
      case "Workshop" -> createdEvent = WorkshopMenuController.getForm(eventManager, name, description, location, date, capacity, modality, totalHours, code);
      case "Short Course" -> createdEvent = ShortCourseMenuController.getForm(eventManager, name, description, location, date, capacity, modality, totalHours, code);
      case "Academic Fair" -> createdEvent = AcademicFairMenuController.getForm(eventManager, name, description, location, date, capacity, modality, totalHours, code);
    }

    if(createdEvent == null){
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Event not created!"));
      return;
    }
    eventManager.add(createdEvent);
    System.out.println(Lines.clear());
    System.out.println(Lines.successLine("Event created!"));
  }

  public Boolean remove(String code){
    String confirmation = CommonForms.getYN("Are you sure you want to remove this event?", "n");
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

    String date = CommonForms.getDate("Event date");
    if (date.equalsIgnoreCase("cancel")) return;
    
    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    for(Modality modality : Modality.getAll())options.add(modality.getDescription());
    
    String modality = CommonForms.getOption(options, "Select the Modality");
    if (modality.equalsIgnoreCase("cancel")) return;

    double totalHours = EventForms.getDouble("Total hours");
    if(totalHours == -1) return;
    
    int capacity = 0;
    if(Modality.fromDescription(modality) != Modality.ONLINE){
      capacity = EventForms.getNumber("Capacity");
    }
    if (capacity == -1) return;

    Event updatedEvent = null;
    switch (type) {
      case "Lecture" -> updatedEvent = LectureMenuController.getForm(eventManager, name, description, location, date, capacity, modality, totalHours, code);
      case "Workshop" -> updatedEvent = WorkshopMenuController.getForm(eventManager, name, description, location, date, capacity, modality, totalHours, code);
      case "Short Course" -> updatedEvent = ShortCourseMenuController.getForm(eventManager, name, description, location, date, capacity, modality, totalHours, code);
      case "Academic Fair" -> updatedEvent = AcademicFairMenuController.getForm(eventManager, name, description, location, date, capacity, modality, totalHours, code);
    }

    if(updatedEvent == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Event not updated!"));
      return;
    }

    updatedEvent.setParticipants(eventManager.get(code).getInPersonParticipants(), eventManager.get(code).getOnlineParticipants());

    eventManager.update(code, updatedEvent);
    System.out.println(Lines.clear());
    System.out.println(Lines.successLine(type + " updated!"));
  }

  public void list(){
    ArrayList<Event> events = new ArrayList<>(eventManager.getAll().values());

    if (events.isEmpty()) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("No events found!"));
      return;
    }
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.centeredMultiLineText("All Events", Colors.YELLOW_BOLD));
    System.out.println(Lines.doubleLine());
    for(Event event : events){
      System.out.print(ReportsGenerator.eventSummary(event, false));
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
    System.out.println(Lines.centeredMultiLineText(type+" Events", Colors.YELLOW_BOLD));
    System.out.println(Lines.doubleLine());
    for (Event event : events) {
      System.out.print(ReportsGenerator.eventSummary(event, false));
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

    System.out.print(ReportsGenerator.eventSummary(eventManager.get(code), false));
    
    ArrayList<String> options = new ArrayList<>(List.of("Go Back", "Update", "Remove", "Show Instructions", "List Participants", "Remove Participant", "Clear Participants", "Generate Certificate"));

    String option;
    while (true) { 
      option = CommonForms.getOption(options, "What do you want to do with this " + type + "?");
      if (option.equalsIgnoreCase("cancel")) return;
      
      switch (option) {
        case "Update" -> update(type, code);
        case "Remove" -> {
          if(remove(code)) return;
        }
        case "Show Instructions" -> showInstructions(code);
        case "List Participants" -> listParticipants(code);
        case "Remove Participant" -> removeParticipant(code);
        case "Clear Participants" -> clearParticipants(code);
        case "Generate Certificate" -> generateCertificate(code);
      }
    }
  }

  public void clear(){
    String confirmation = CommonForms.getYN("Are you sure you want to remove all events?", "n");
    if (confirmation.equalsIgnoreCase("n")) {
      System.out.println(Lines.clear());
      System.out.println(Lines.warningLine("Events not removed!"));
      return;
    }
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
    String confirmation = CommonForms.getYN("Are you sure you want to remove all " + type + " events?", "n");
    if (confirmation.equalsIgnoreCase("n")) {
      System.out.println(Lines.clear());
      System.out.println(Lines.warningLine(type + "s not removed!"));
      return;
    }
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

  public void showInstructions(String code){
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.centeredMultiLineText("Instructions of " + eventManager.get(code).getName() + " (" + eventManager.get(code).getCode() + ")", Colors.YELLOW_BOLD));
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.multiLineText(eventManager.get(code).getInstructions()));
    System.out.println(Lines.doubleLine());
  }

  public void listParticipants(String code){
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.centeredMultiLineText("Participants of " + eventManager.get(code).getName() + " (" + eventManager.get(code).getCode() + ")", Colors.YELLOW_BOLD));
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

  public void removeParticipant(String code){
    String cpf = ParticipantForms.getCpf();
    if (cpf.equalsIgnoreCase("cancel")) return;

    if(eventManager.get(code).isParticipantRegistered(cpf) == false){
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant with CPF " + cpf + " is not registered in this event!"));
      return;      
    }

    String confirmation = CommonForms.getYN("Are you sure you want to remove this participant?", "n");
    if (confirmation.equalsIgnoreCase("n")) {
      System.out.println(Lines.clear());
      System.out.println(Lines.warningLine("Participant not removed!"));
      return;
    }

    try {
      eventManager.get(code).removeParticipant(cpf);
      eventManager.update(code, eventManager.get(code));
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Participant removed!"));
    } catch (UniEventsException e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public void clearParticipants(String code){
    String confirmation = CommonForms.getYN("Are you sure you want to remove all participants?", "n");
    if (confirmation.equalsIgnoreCase("n")) {
      System.out.println(Lines.clear());
      System.out.println(Lines.warningLine("Participants not removed!"));
      return;
    }
    try {
      eventManager.get(code).clearParticipants();
      eventManager.update(code, eventManager.get(code));
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("All participants removed!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public void generateCertificate(String code){
    String cpf = ParticipantForms.getCpf();
    if (cpf.equalsIgnoreCase("cancel")) return;

    if(eventManager.get(code).isParticipantRegistered(cpf) == false){
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant is not registered in this event!"));
      return;      
    }

    try {
      eventManager.get(code).generateCertificate(cpf);
      participantManager.update(cpf, participantManager.get(cpf));
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Certificate generated!"));
    } catch (UniEventsException e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }
}
