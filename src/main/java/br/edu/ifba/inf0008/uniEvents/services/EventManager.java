package br.edu.ifba.inf0008.uniEvents.services;

import java.time.LocalDate;
import java.util.LinkedHashMap;

import br.edu.ifba.inf0008.uniEvents.model.events.AcademicFair;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.Lecture;
import br.edu.ifba.inf0008.uniEvents.model.events.Modality;
import br.edu.ifba.inf0008.uniEvents.model.events.ShortCourse;
import br.edu.ifba.inf0008.uniEvents.model.events.Workshop;
import br.edu.ifba.inf0008.uniEvents.model.participants.Student;
import br.edu.ifba.inf0008.uniEvents.repository.EventRepository;
import br.edu.ifba.inf0008.uniEvents.repository.ParticipantRepository;


public class EventManager {
  private final EventRepository eventRepository;
  private final ParticipantRepository participantRepository;

  public static LinkedHashMap<String, Event> events;

  public EventManager(EventRepository eventRepository, ParticipantRepository participantRepository) {
    this.eventRepository = eventRepository;
    this.participantRepository = participantRepository;
    events = eventRepository.getEvents();
  }

  public void addEvent(Event event){
    events.put(event.getCode(), event);
    eventRepository.addEvent(event);
  }    

  public void removeEvent(Event event){
    events.remove(event.getCode());
    eventRepository.removeEvent(event);
  }

  public void updateEvent(Event event, Event updatedEvent) {
    events.put(event.getCode(), updatedEvent);
    eventRepository.updateEvent(event, event.getCode());
  }

  public Event getEvent(String code){
    return events.get(code);
  }

  public void addParticipant(String code, String cpf){
    if (getEvent(code) == null) {
      throw new IllegalArgumentException("Event not found");
    }

    if (participantRepository.getParticipant(cpf) == null) {
      throw new IllegalArgumentException("Participant not found");
    }

    if(getEvent(code) instanceof ShortCourse && !(participantRepository.getParticipant(cpf) instanceof Student)){
      throw new IllegalArgumentException("Only students can participate in short courses");
    }

    if(getEvent(code).isFull()){
      throw new IllegalArgumentException("Event is full");
    }

    if(getEvent(code).isParticipantRegistered(cpf)){
      throw new IllegalArgumentException("Participant is already registered in this event");
    }

    getEvent(code).addParticipantCpf(cpf);
    getEvent(code).addParticipant(participantRepository.getParticipant(cpf));
    eventRepository.saveEvents();
  }

  public void removeParticipant(String code, String cpf){
    if (getEvent(code) == null) {
      throw new IllegalArgumentException("Event not found");
    }
    if (participantRepository.getParticipant(cpf) == null) {
      throw new IllegalArgumentException("Participant not found");
    }

    if(!getEvent(code).isParticipantRegistered(cpf)){
      throw new IllegalArgumentException("Participant is not registered in this event");
    }

    getEvent(code).removeParticipantCpf(cpf);
    getEvent(code).removeParticipant(cpf);
    eventRepository.saveEvents();
  }

  public LinkedHashMap<String, Event> getEventsByParticipant(String cpf) {
    LinkedHashMap<String, Event> participantEvents = new LinkedHashMap<>();
    for (Event event : events.values()) {
      if (event.getParticipants().containsKey(cpf)) {
        participantEvents.put(event.getCode(), event);
      }
    }
    return participantEvents;
  }

   public void clearAllEvents() {
    events.clear();
    eventRepository.clearAllEvents();
  }

  public Boolean isCodeAlreadyInUse(String code) {
    return events.get(code) != null;
  }

  public LinkedHashMap<String, Event> getAllEvents() {
    return events;
  }

  public void createLecture(String name, String location, String description, LocalDate date, int capacity, Modality modality, String code) {
    Lecture event = new Lecture(name, location, description, date, capacity, modality, code);
    addEvent(event);
  }

  public void createWorkshop(String name, String location, String description, LocalDate date, int capacity, Modality modality, String code) {
    Workshop event = new Workshop(name, location, description, date, capacity, modality, code);
    addEvent(event);
  }

  public void createShortCourse(String name, String location, String description, LocalDate date, int capacity, Modality modality, String code) {
    ShortCourse event = new ShortCourse(name, location, description, date, capacity, modality, code);
    addEvent(event);
  }

  public void createAcademicFair(String name, String location, String description, LocalDate date, int capacity, Modality modality, String code) {
    AcademicFair event = new AcademicFair(name, location, description, date, capacity, modality, code);
    addEvent(event);
  }
}
