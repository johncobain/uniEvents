package br.edu.ifba.inf0008.uniEvents.services;

import java.util.LinkedHashMap;

import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.ShortCourse;
import br.edu.ifba.inf0008.uniEvents.model.participants.Student;
import br.edu.ifba.inf0008.uniEvents.repository.EventRepository;
import br.edu.ifba.inf0008.uniEvents.repository.ParticipantRepository;


public class EventManager implements IManager<Event>{
  private final EventRepository eventRepository;
  private final ParticipantRepository participantRepository;

  public static LinkedHashMap<String, Event> events;

  public EventManager(EventRepository eventRepository, ParticipantRepository participantRepository) {
    this.eventRepository = eventRepository;
    this.participantRepository = participantRepository;
    events = eventRepository.getAll();
  }

  @Override
  public void add(Event event){
    events.put(event.getCode(), event);
    eventRepository.add(event);
  }    

  @Override
  public void remove(String code){
    events.remove(code);
    eventRepository.remove(code);
  }

  @Override
  public void update(String code, Event updatedEvent) {
    events.put(code, updatedEvent);
    eventRepository.update(updatedEvent, code);
  }

  @Override
  public Event get(String code){
    return events.get(code);
  }

  @Override
  public void clear() {
    events.clear();
    eventRepository.clear();
  }

  @Override
  public LinkedHashMap<String, Event> getAll() {
    return events;
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

    get(code).removeParticipant(cpf);
    eventRepository.save();
  }

  public void clearParticipants(String code) {
    if (get(code) == null) {
      throw new IllegalArgumentException("Event not found");
    }

    for (String cpf : get(code).getParticipants().keySet()) {
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

  public Boolean isCodeAlreadyInUse(String code) {
    return events.get(code) != null;
  }
}