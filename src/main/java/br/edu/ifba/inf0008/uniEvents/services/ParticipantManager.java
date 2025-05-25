package br.edu.ifba.inf0008.uniEvents.services;

import java.time.LocalDate;
import java.util.LinkedHashMap;

import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.participants.External;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.model.participants.Student;
import br.edu.ifba.inf0008.uniEvents.model.participants.Teacher;
import br.edu.ifba.inf0008.uniEvents.repository.ParticipantRepository;

public class ParticipantManager {
  private final ParticipantRepository participantRepository;

  public static LinkedHashMap<String, Participant> participants;
  
  public ParticipantManager(ParticipantRepository participantRepository) {
    this.participantRepository = participantRepository;
    participants = participantRepository.getParticipants();
  }

  public void addParticipant(Participant participant){
    participants.put(participant.getCpf(), participant);
    participantRepository.addParticipant(participant);
  }

  public void removeParticipant(String cpf){
    participants.remove(cpf);
    participantRepository.removeParticipant(cpf);
  }

  public void updateParticipant(Participant participant, Participant updatedParticipant) {
    participants.put(participant.getCpf(), updatedParticipant);
    participantRepository.updateParticipant(participant, participant.getCpf());
  }

  public Participant getParticipant(String cpf){
    if(participants.get(cpf) == null) {
      return null;
    }
    return participants.get(cpf);
  }

  public void clearParticipantsInEvents(String cpf, EventManager eventManager) {
    for (Event event : EventManager.events.values()) {
      if (event.getParticipants().containsKey(cpf)) {
        eventManager.removeParticipant(event.getCode(), cpf);
      }
    }
  }

  public void clearAllParticipants(EventManager eventManager) {
    for (String cpf : participants.keySet()) {
      clearParticipantsInEvents(cpf, eventManager);
    }
    participants.clear();
    participantRepository.clearAllParticipants();
  }

  public Boolean isCpfAlreadyInUse(String cpf) {
    return participants.get(cpf) != null;
  }

  public LinkedHashMap<String, Participant> getAllParticipants() {
    return participants;
  }

  public void createStudent(String name, String cpf, String email, String phone, LocalDate birthDate) {
    Student student = new Student(name, cpf, email, phone, birthDate);
    addParticipant(student);
  }

  public void createTeacher(String name, String cpf, String email, String phone, LocalDate birthDate) {
    Teacher teacher = new Teacher(name, cpf, email, phone, birthDate);
    addParticipant(teacher);
  }

  public void createExternal(String name, String cpf, String email, String phone, LocalDate birthDate) {
    External external = new External(name, cpf, email, phone, birthDate);
    addParticipant(external);
  }

}