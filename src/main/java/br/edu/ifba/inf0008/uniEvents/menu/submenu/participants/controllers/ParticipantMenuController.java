package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifba.inf0008.uniEvents.exceptions.UniEventsException;
import br.edu.ifba.inf0008.uniEvents.forms.CommonForms;
import br.edu.ifba.inf0008.uniEvents.forms.EventForms;
import br.edu.ifba.inf0008.uniEvents.forms.ParticipantForms;
import br.edu.ifba.inf0008.uniEvents.model.events.Certificate;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.ShortCourse;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.model.participants.External;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.services.IManager;
import br.edu.ifba.inf0008.uniEvents.services.ReportsGenerator;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;

public class ParticipantMenuController {
  private final IManager<Participant> participantManager;
  private final IManager<Event> eventManager;

  public ParticipantMenuController(IManager<Participant> participantManager, IManager<Event> eventManager) {
    this.participantManager = participantManager;
    this.eventManager = eventManager;
  }

  public void create(String type){
    String name = ParticipantForms.getName(type);
    if(name.equalsIgnoreCase("cancel")) return;

    String cpf;
    while(true){
      cpf = ParticipantForms.getCpf();
      if(participantManager.get(cpf) != null){
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

    String birthDateString = CommonForms.getDate("birth date");
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

  public Boolean remove(String cpf){
    String confirmation = CommonForms.getYN("Are you sure you want to remove this participant?", "n");
    if (confirmation.equalsIgnoreCase("n")) {
      System.out.println(Lines.clear());
      System.out.println(Lines.warningLine("Participant not removed!"));
      return false;
    }

    try {
      participantManager.remove(cpf);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Participant removed!"));
      return true;
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
      return false;
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

    String birthDateString = CommonForms.getDate("birth date");
    if (birthDateString.equalsIgnoreCase("cancel")) return;

    Boolean updated = false;
    switch (type) {
      case "Student" -> updated = StudentMenuController.update(participantManager, name, cpf, email, phone, birthDateString);
      case "Professor" -> updated = ProfessorMenuController.update(participantManager, name, cpf, email, phone, birthDateString);
      case "External" -> updated = ExternalMenuController.update(participantManager, name, cpf, email, phone, birthDateString);
    }

    if (!updated) return;

    System.out.println(Lines.clear());
    System.out.println(Lines.successLine(type + " updated!"));
  }
  
  public void list(){
    ArrayList<Participant> participants = new ArrayList<>( participantManager.getAll().values());

    if(participants.isEmpty()){
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("No participants found!"));
      return;
    }
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.centeredMultiLineText("All Participants", Colors.BLUE_BOLD));
    System.out.println(Lines.doubleLine());
    for(Participant participant : participants){
      System.out.print(ReportsGenerator.participantSummary(participant));
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
    System.out.println(Lines.centeredMultiLineText(type+"s", Colors.BLUE_BOLD));
    System.out.println(Lines.doubleLine());
    for (Participant participant : participants) {
      System.out.print(ReportsGenerator.participantSummary(participant));
    }
  }

  public void get(String type){
    String cpf = ParticipantForms.getCpf();
    if(cpf.equalsIgnoreCase("cancel")) return;

    if (participantManager.get(cpf) == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant not found!"));
      return;
    }
    
    if (!type.equalsIgnoreCase("Participant") && !participantManager.get(cpf).getType().equalsIgnoreCase(type)) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant is not a " + type + "!"));
      return;
    }
    
    if(type.equalsIgnoreCase("Participant")) type = participantManager.get(cpf).getType();
    
    System.out.print(ReportsGenerator.participantSummary(participantManager.get(cpf)));

    ArrayList<String> options = new ArrayList<>(List.of("Go Back", "Update", "Remove", "Add To Event", "Remove From Event", "Show Events", "Show Certificates"));
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
    String option;
    while(true){ 
      option = CommonForms.getOption(options, "What do you want to do with this " + type + "?");
      if (option.equalsIgnoreCase("cancel")) return;
      
      switch (option) {
        case "Update" -> update(type, cpf);
        case "Remove" -> {
          if(remove(cpf)) return;
        }
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
    System.out.println(Lines.centeredMultiLineText("Events with " + participantManager.get(cpf).getType() + " " + participantManager.get(cpf).getName(), Colors.BLUE_BOLD));
    System.out.println(Lines.doubleLine());
    List<Event> events = eventManager.getAll().values().stream()
      .filter(e -> e.getParticipants().containsKey(cpf))
      .sorted((e1, e2) -> e1.getDate().compareTo(e2.getDate()))
      .collect(Collectors.toList());
    if(events.isEmpty()){
      System.out.println(Lines.multiLineText(participantManager.get(cpf).getName() + " has no events!"));
      return;
    }
    for (Event event : events) {
      System.out.println(Lines.straightLine());
      System.out.print(event.toString());
      System.out.println(Lines.straightLine());
    }
  }

  public void clear(){
    String confirmation = CommonForms.getYN("Are you sure you want to remove all participants?", "n");
    if (confirmation.equalsIgnoreCase("n")) {
      System.out.println(Lines.clear());
      System.out.println(Lines.warningLine("Participants not removed!"));
      return;
    }
    try {
      participantManager.clear();
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("All participants removed!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public void clear(String type){
    String confirmation = CommonForms.getYN("Are you sure you want to remove all " + type + "s?", "n");
    if (confirmation.equalsIgnoreCase("n")) {
      System.out.println(Lines.clear());
      System.out.println(Lines.warningLine(type + "s not removed!"));
      return;
    }
    try {
      List<Participant> participantsOfType = participantManager.getAll().values()
      .stream()
      .filter(p -> p.getType().equalsIgnoreCase(type))
      .collect(Collectors.toList());

      for (Participant participant : participantsOfType) {
        participantManager.remove(participant.getCpf());
      }
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("All " + type + "s removed!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }
  
  public void addToEvent(String cpf){
    String code = EventForms.getCode();
    if (code.equalsIgnoreCase("cancel")) return;

    if (eventManager.get(code) == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Event not found!"));
      return;
    }

    if(eventManager.get(code).isParticipantRegistered(cpf)){
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant with CPF " + cpf + " is already registered in this event!"));
      return;      
    }

    if(eventManager.get(code).getType().equalsIgnoreCase("Short Course")){
      if(!participantManager.get(cpf).getType().equalsIgnoreCase("Student")){
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine("Only Students can register in Short Courses!"));
        return;
      }
      
      if(!((ShortCourse)eventManager.get(code)).checkEligibility(participantManager, cpf)){
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine("Participant " + participantManager.get(cpf).getName() + " is not eligible to register in this event!"));
        return;      
      }
    }

    String modality = eventManager.get(code).getModality().getDescription();
    if(Modality.fromDescription(modality) == Modality.HYBRID){
      ArrayList<String> options = new ArrayList<>();
      options.add("Cancel");
      options.add(Modality.INPERSON.getDescription());
      options.add(Modality.ONLINE.getDescription());
      
      modality = CommonForms.getOption(options, "Select Modality to register");
      if (modality.equalsIgnoreCase("cancel")) return;
    }

    try {
      eventManager.get(code).addParticipant(participantManager.get(cpf), Modality.fromDescription(modality));
      eventManager.update(code, eventManager.get(code));
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine(participantManager.get(cpf).getType() + " " + participantManager.get(cpf).getName() + " added to event!"));
    } catch (UniEventsException e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public void removeFromEvent(String cpf){
    String code = EventForms.getCode();
    if (code.equalsIgnoreCase("cancel")) return;

    if (eventManager.get(code) == null) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Event not found!"));
      return;
    }

    if(!eventManager.get(code).isParticipantRegistered(cpf)){
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant with CPF " + cpf + " is not registered in this event!"));
      return;
    }

    String confirmation = CommonForms.getYN("Are you sure you want to remove this participant from the event?", "n");
    if (confirmation.equalsIgnoreCase("n")) {
      System.out.println(Lines.clear());
      System.out.println(Lines.warningLine("Participant not removed from event!"));
      return;
    }
    
    try {
      eventManager.get(code).removeParticipant(cpf);
      eventManager.update(code, eventManager.get(code));
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine(participantManager.get(cpf).getType() + " " + participantManager.get(cpf).getName() + " removed from event!"));
    } catch (UniEventsException e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public void showCertificates(String cpf){
    List<Certificate> certificates = participantManager.get(cpf).getCertificates();
    if (certificates.isEmpty()) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Participant has no certificates!"));
      return;
    }
    System.out.println(Lines.doubleLine());
    System.out.println(Lines.centeredMultiLineText("Certificates of " + participantManager.get(cpf).getName(), Colors.BLUE_BOLD));
    System.out.println(Lines.doubleLine());
    for (Certificate certificate : certificates) {
      System.out.print(certificate.toString());
    }
  }
}
