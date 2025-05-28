package br.edu.ifba.inf0008.uniEvents.services;

import java.util.LinkedHashMap;

import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.repository.ParticipantRepository;

public class ParticipantManager implements IManager<Participant> {
  private final ParticipantRepository participantRepository;
  private final IManager<Event> eventManager;

  public static LinkedHashMap<String, Participant> participants;
  
  public ParticipantManager(ParticipantRepository participantRepository, IManager<Event> eventManager) {
    this.participantRepository = participantRepository;
    this.eventManager = eventManager;
    participants = participantRepository.getAll();
  }

  @Override
  public void add(Participant participant){
    participants.put(participant.getCpf(), participant);
    participantRepository.add(participant);
  }

  @Override
  public void remove(String cpf) {
    clearParticipantInEvents(cpf, eventManager);
    participants.remove(cpf);
    participantRepository.remove(cpf);
  }

  @Override
  public void update(String cpf, Participant updatedParticipant) {
    participants.put(cpf, updatedParticipant);
    participantRepository.update(updatedParticipant, cpf);
  }

  @Override
  public Participant get(String cpf){
    if(participants.get(cpf) == null) {
      return null;
    }
    return participants.get(cpf);
  }

  @Override
  public void clear() {
    clearParticipantsInEvents(eventManager);
    participants.clear();
    participantRepository.clear();
  }

  @Override
  public LinkedHashMap<String, Participant> getAll() {
    return participants;
  }

  protected void clearParticipantsInEvents(IManager<Event> eventManager) {
    for (String cpf : participants.keySet()) {
      clearParticipantInEvents(cpf, eventManager);
    }
  }

  protected void clearParticipantInEvents(String cpf, IManager<Event> eventManager) {
    for (Event event : EventManager.events.values()) {
      if (event.getParticipants().containsKey(cpf)) {
        event.removeParticipant(cpf);
        eventManager.update(event.getCode(), event);
      }
    }
  }
}