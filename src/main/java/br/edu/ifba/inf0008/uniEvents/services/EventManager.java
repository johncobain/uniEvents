package br.edu.ifba.inf0008.uniEvents.services;

import java.time.LocalDate;
import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.Modality;
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

  public Boolean addParticipantToEvent(String eventCode, String participantCpf){
    Event event = getEvent(eventCode);
    if (event == null) {
      return false;
    }
    Participant participant = participantRepository.getParticipant(participantCpf);
    if (participant == null) {
      return false;
    }

    event.addParticipantCpf(participant.getCpf());
    event.populateParticipants(participantRepository);
    eventRepository.saveEvents();
    return true;
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
}
