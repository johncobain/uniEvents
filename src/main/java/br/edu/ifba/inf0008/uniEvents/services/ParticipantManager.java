package br.edu.ifba.inf0008.uniEvents.services;

import java.time.LocalDate;
import java.util.LinkedHashMap;

import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.participants.External;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.model.participants.Professor;
import br.edu.ifba.inf0008.uniEvents.model.participants.Student;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.AcademicStatus;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.Course;
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

  public void remove(String cpf, EventManager eventManager) {
    clearParticipantsInEvents(cpf, eventManager);
    participants.remove(cpf);
    participantRepository.remove(cpf);
  }

  public void update(String cpf, Participant updatedParticipant) {
    participants.put(cpf, updatedParticipant);
    participantRepository.update(updatedParticipant, cpf);
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

  public Boolean isIdAlreadyInUse(String id) {
    for (Participant participant : participants.values()) {
      if (participant instanceof Student && ((Student) participant).getStudentId().equals(id)) {
        return true;
      }
    }
    return false;
  }

  public LinkedHashMap<String, Participant> getAll() {
    return participants;
  }

  public void createStudent(String name, String cpf, String email, String phone, LocalDate birthDate, String studentId, Course course, int currentSemester, AcademicStatus academicStatus, double gpa, String campus, LocalDate enrollmentDate) {
    Student student = new Student(name, cpf, email, phone, birthDate, studentId, course, currentSemester, academicStatus, gpa, campus, enrollmentDate);
    add(student);
  }

  public void createProfessor(String name, String cpf, String email, String phone, LocalDate birthDate) {
    Professor professor = new Professor(name, cpf, email, phone, birthDate);
    add(professor);
  }

  public void createExternal(String name, String cpf, String email, String phone, LocalDate birthDate) {
    External external = new External(name, cpf, email, phone, birthDate);
    add(external);
  }

}