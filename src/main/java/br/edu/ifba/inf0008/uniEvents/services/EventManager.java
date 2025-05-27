package br.edu.ifba.inf0008.uniEvents.services;

import java.time.LocalDate;
import java.util.LinkedHashMap;

import br.edu.ifba.inf0008.uniEvents.model.events.AcademicFair;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.Lecture;
import br.edu.ifba.inf0008.uniEvents.model.events.ShortCourse;
import br.edu.ifba.inf0008.uniEvents.model.events.Workshop;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
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
    events = eventRepository.getAll();
  }

  public void add(Event event){
    events.put(event.getCode(), event);
    eventRepository.add(event);
  }    

  public void remove(String code){
    events.remove(code);
    eventRepository.remove(code);
  }

  public void update(String code, Event updatedEvent) {
    events.put(code, updatedEvent);
    eventRepository.update(updatedEvent, code);
  }

  public Event get(String code){
    return events.get(code);
  }

  public void addParticipant(String code, String cpf){
    if (get(code) == null) {
      throw new IllegalArgumentException("Event not found");
    }

    if (participantRepository.get(cpf) == null) {
      throw new IllegalArgumentException("Participant not found");
    }

    if(get(code) instanceof ShortCourse && !(participantRepository.get(cpf) instanceof Student)){
      throw new IllegalArgumentException("Only students can participate in short courses");
    }

    if(get(code).isFull()){
      throw new IllegalArgumentException("Event is full");
    }

    if(get(code).isParticipantRegistered(cpf)){
      throw new IllegalArgumentException("Participant is already registered in this event");
    }

    get(code).addParticipantCpf(cpf);
    get(code).addParticipant(participantRepository.get(cpf));
    eventRepository.save();
  }

  public void removeParticipant(String code, String cpf){
    if (get(code) == null) {
      throw new IllegalArgumentException("Event not found");
    }
    if (participantRepository.get(cpf) == null) {
      throw new IllegalArgumentException("Participant not found");
    }

    if(!get(code).isParticipantRegistered(cpf)){
      throw new IllegalArgumentException("Participant is not registered in this event");
    }

    get(code).removeParticipantCpf(cpf);
    get(code).removeParticipant(cpf);
    eventRepository.save();
  }

  public void clearParticipants(String code) {
    if (get(code) == null) {
      throw new IllegalArgumentException("Event not found");
    }

    for (String cpf : get(code).getParticipants().keySet()) {
      get(code).removeParticipantCpf(cpf);
      get(code).removeParticipant(cpf);
    }
    eventRepository.save();
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

   public void clear() {
    events.clear();
    eventRepository.clear();
  }

  public Boolean isCodeAlreadyInUse(String code) {
    return events.get(code) != null;
  }

  public LinkedHashMap<String, Event> getAll() {
    return events;
  }

  public void createLecture(String name, String location, String description, LocalDate date, int capacity, Modality modality, String code) {
    Lecture event = new Lecture(name, location, description, date, capacity, modality, code);
    add(event);
  }

  public void createWorkshop(String name, String location, String description, LocalDate date, int capacity, Modality modality, String code) {
    Workshop event = new Workshop(name, location, description, date, capacity, modality, code);
    add(event);
  }

  public void createShortCourse(String name, String location, String description, LocalDate date, int capacity, Modality modality, String code) {
    ShortCourse event = new ShortCourse(name, location, description, date, capacity, modality, code);
    add(event);
  }

  public void createAcademicFair(String name, String location, String description, LocalDate date, int capacity, Modality modality, String code) {
    AcademicFair event = new AcademicFair(name, location, description, date, capacity, modality, code);
    add(event);
  }
}
