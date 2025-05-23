package br.edu.ifba.inf0008.uniEvents.services;

import java.time.LocalDate;
import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.participants.External;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.model.participants.Student;
import br.edu.ifba.inf0008.uniEvents.model.participants.Teacher;
import br.edu.ifba.inf0008.uniEvents.repository.ParticipantRepository;

public class ParticipantManager {
  private final ParticipantRepository participantRepository;

  public static ArrayList<Participant> participants = new ArrayList<>();
  
  public ParticipantManager(ParticipantRepository participantRepository) {
    this.participantRepository = participantRepository;
    participants = participantRepository.getParticipants();
  }

  public void addParticipant(Participant participant){
    participants.add(participant);
    participantRepository.addParticipant(participant);
  }

  public void removeParticipant(Participant participant){
    participants.remove(participant);
    participantRepository.removeParticipant(participant);
  }

  public void updateParticipant(Participant participant, String name, String email, String phone, LocalDate birthDate) {
    participant.setName(name);
    participant.setEmail(email);
    participant.setPhone(phone);
    participant.setBirthDate(birthDate);
    participantRepository.updateParticipant(participant, participant.getCpf());
  }

  public Participant getParticipant(String cpf){
    for (Participant participant : participants) {
      if (participant.getCpf().equals(cpf)) {
        return participant;
      }
    }
    return null;
  }

  public ArrayList<Event> getEventsByParticipant(Participant participant){
    ArrayList<Event> events = new ArrayList<>();
    for (Event event : EventManager.events) {
      if (event.getParticipants().contains(participant)) {
        events.add(event);
      }
    }
    return events;
  }

  public void clearParticipantsInEvents(Participant participant){
    for (Event event : EventManager.events) {
      if (event.getParticipants().contains(participant)) {
        event.removeParticipant(participant);
      }
    }
  }

  public void clearAllParticipants() {
    for(Participant participant : participants){
      clearParticipantsInEvents(participant);
    }
    participants.clear();
    participantRepository.clearAllParticipants();
  }

  public Boolean isCpfAlreadyInUse(String cpf) {
    for (Participant participant : participants) {
      if (participant.getCpf().equals(cpf)) {
        return true;
      }
    }
    return false;
  }

  public ArrayList<Participant> getAllParticipants() {
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