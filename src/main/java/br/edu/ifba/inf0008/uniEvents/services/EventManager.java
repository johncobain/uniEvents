package br.edu.ifba.inf0008.uniEvents.services;

import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.repository.EventRepository;

public class EventManager {
  private final EventRepository eventRepository;

  public static ArrayList<Event> events = new ArrayList<>();

  public EventManager(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  public void addEvent(Event event){
    events.add(event);
    eventRepository.addEvent(event);
  }

  public void addParticipantToEvent(Event event, Participant participant){
    event.addParticipant(participant);
    // eventRepository.addParticipantToEvent(event, participant);
  }
    

  public void removeEvent(Event event){
    events.remove(event);
    eventRepository.removeEvent(event);
  }

  public void updateEvent(Event event){
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

  public ArrayList<Event> getAllEvents() {
    return events;
  }
}
