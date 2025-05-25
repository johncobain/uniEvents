package br.edu.ifba.inf0008.uniEvents.services;

import java.time.LocalDate;
import java.util.LinkedHashMap;

import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.participants.External;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.model.participants.Student;
import br.edu.ifba.inf0008.uniEvents.model.participants.Teacher;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.AcademicStatus;
import br.edu.ifba.inf0008.uniEvents.repository.ParticipantRepository;

public class ParticipantManager {
  private final ParticipantRepository participantRepository;

  public static LinkedHashMap<String, Participant> participants;
  
  public ParticipantManager(ParticipantRepository participantRepository) {
    this.participantRepository = participantRepository;
    participants = participantRepository.getAll();
  }

  public void add(Participant participant){
    participants.put(participant.getCpf(), participant);
    participantRepository.add(participant);
  }

  public void remove(String cpf){
    participants.remove(cpf);
    participantRepository.remove(cpf);
  }

  public void update(Participant participant, Participant updatedParticipant) {
    participants.put(participant.getCpf(), updatedParticipant);
    participantRepository.update(participant, participant.getCpf());
  }

  public Participant get(String cpf){
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

  public void clear(EventManager eventManager) {
    for (String cpf : participants.keySet()) {
      clearParticipantsInEvents(cpf, eventManager);
    }
    participants.clear();
    participantRepository.clear();
  }

  public Boolean isCpfAlreadyInUse(String cpf) {
    return participants.get(cpf) != null;
  }

  public LinkedHashMap<String, Participant> getAll() {
    return participants;
  }

  public void createStudent(String name, String cpf, String email, String phone, LocalDate birthDate, String course, int currentSemester, AcademicStatus academicStatus, double gpa, String campus, LocalDate enrollmentDate) {
    Student student = new Student(name, cpf, email, phone, birthDate, course, currentSemester, academicStatus, gpa, campus, enrollmentDate);
    add(student);
  }

  public void createTeacher(String name, String cpf, String email, String phone, LocalDate birthDate) {
    Teacher teacher = new Teacher(name, cpf, email, phone, birthDate);
    add(teacher);
  }

  public void createExternal(String name, String cpf, String email, String phone, LocalDate birthDate) {
    External external = new External(name, cpf, email, phone, birthDate);
    add(external);
  }

}