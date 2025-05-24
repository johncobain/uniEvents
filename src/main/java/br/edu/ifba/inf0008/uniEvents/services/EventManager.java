package br.edu.ifba.inf0008.uniEvents.services;

import java.time.LocalDate;
import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.model.events.AcademicFair;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.Lecture;
import br.edu.ifba.inf0008.uniEvents.model.events.Modality;
import br.edu.ifba.inf0008.uniEvents.model.events.ShortCourse;
import br.edu.ifba.inf0008.uniEvents.model.events.Workshop;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.repository.EventRepository;
import br.edu.ifba.inf0008.uniEvents.repository.ParticipantRepository;


public class EventManager {
  private final EventRepository eventRepository;
  private final ParticipantRepository participantRepository;

  public static ArrayList<Event> events = new ArrayList<>();

  public EventManager(EventRepository eventRepository, ParticipantRepository participantRepository) {
    this.eventRepository = eventRepository;
    this.participantRepository = participantRepository;
    events = eventRepository.getEvents();
  }

  public void addEvent(Event event){
    events.add(event);
    eventRepository.addEvent(event);
  }

  public void addParticipant(String eventCode, String participantCpf){
    Event event = getEvent(eventCode);
    if (event == null) {
      throw new IllegalArgumentException("Event not found");
    }
    Participant participant = participantRepository.getParticipant(participantCpf);
    if (participant == null) {
      throw new IllegalArgumentException("Participant not found");
    }

    if(event.getType().equals("Short Course") && !participant.getType().equals( "Student")){
      throw new IllegalArgumentException("Only students can participate in short courses");
    }

    if(event.isFull()){
      throw new IllegalArgumentException("Event is full");
    }

    if(event.isParticipantRegistered(participant)){
      throw new IllegalArgumentException("Participant is already registered in this event");
    }

    event.addParticipantCpf(participant.getCpf());
    event.addParticipant(participant);
    eventRepository.saveEvents();
  }

  public void removeParticipant(String eventCode, String participantCpf){
    Event event = getEvent(eventCode);
    if (event == null) {
      throw new IllegalArgumentException("Event not found");
    }
    Participant participant = participantRepository.getParticipant(participantCpf);
    if (participant == null) {
      throw new IllegalArgumentException("Participant not found");
    }

    if(!event.isParticipantRegistered(participant)){
      throw new IllegalArgumentException("Participant is not registered in this event");
    }

    event.removeParticipantCpf(participant.getCpf());
    event.removeParticipant(participant);
    eventRepository.saveEvents();
  }
    

  public void removeEvent(Event event){
    events.remove(event);
    eventRepository.removeEvent(event);
  }

  public void updateEvent(Event event, String name, String location, String description, LocalDate date, int capacity, Modality
  modality) {
    event.setName(name);
    event.setLocation(location);
    event.setDescription(description);
    event.setDate(date);
    event.setCapacity(capacity);
    event.setModality(modality);
    eventRepository.updateEvent(event, event.getCode());
  }

  public Event getEvent(String code){
    for (Event event : events) {
      if (event.getCode().equals(code)) {
        return event;
      }
    }
    return null;
  }

  public ArrayList<Event> getEventsByParticipant(Participant participant) {
    ArrayList<Event> participantEvents = new ArrayList<>();
    for (Event event : events) {
      if (event.getParticipants().contains(participant)) {
        participantEvents.add(event);
      }
    }
    return participantEvents;
  }

   public void clearAllEvents() {
    events.clear();
    eventRepository.clearAllEvents();
  }

  public Boolean isCodeAlreadyInUse(String code) {
    for (Event event : events) {
      if (event.getCode().equals(code)) {
        return true;
      }
    }
    return false;
  }

  public ArrayList<Event> getAllEvents() {
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
