package br.edu.ifba.inf0008.uniEvents.services;

import java.util.LinkedHashMap;

import br.edu.ifba.inf0008.uniEvents.model.events.Event;
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

  public void clearParticipants(String code) {
    if (get(code) == null) {
      throw new IllegalArgumentException("Event not found");
    }

    for (String cpf : get(code).getParticipants().keySet()) {
      get(code).removeParticipant(cpf);
    }
    eventRepository.save();
  }
}