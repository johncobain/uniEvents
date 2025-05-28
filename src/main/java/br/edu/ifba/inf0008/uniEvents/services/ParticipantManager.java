package br.edu.ifba.inf0008.uniEvents.services;

import java.time.LocalDate;
import java.util.LinkedHashMap;

import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.participants.External;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.model.participants.Professor;
import br.edu.ifba.inf0008.uniEvents.model.participants.Student;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.AcademicStatus;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.AcademicTitle;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.Course;
import br.edu.ifba.inf0008.uniEvents.repository.ParticipantRepository;

public class ParticipantManager implements IManager<Participant> {
  private final ParticipantRepository participantRepository;

  public static LinkedHashMap<String, Participant> participants;
  
  public ParticipantManager(ParticipantRepository participantRepository) {
    this.participantRepository = participantRepository;
    participants = participantRepository.getAll();
  }

  @Override
  public void add(Participant participant){
    participants.put(participant.getCpf(), participant);
    participantRepository.add(participant);
  }

  @Override
  public void remove(String cpf) {
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
    participants.clear();
    participantRepository.clear();
  }

  @Override
  public LinkedHashMap<String, Participant> getAll() {
    return participants;
  }

  public void clearParticipantsInEvents(EventManager eventManager) {
    for (String cpf : participants.keySet()) {
      clearParticipantInEvents(cpf, eventManager);
    }
  }

  public void clearParticipantInEvents(String cpf, EventManager eventManager) {
    for (Event event : EventManager.events.values()) {
      if (event.getParticipants().containsKey(cpf)) {
        eventManager.removeParticipant(event.getCode(), cpf);
      }
    }
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

  public void createStudent(String name, String cpf, String email, String phone, LocalDate birthDate, String studentId, Course course, int currentSemester, AcademicStatus academicStatus, double gpa, String campus, LocalDate enrollmentDate) {
    Student student = new Student(name, cpf, email, phone, birthDate, studentId, course, currentSemester, academicStatus, gpa, campus, enrollmentDate);
    add(student);
  }

  public void createProfessor(String name, String cpf, String email, String phone, LocalDate birthDate, String employeeId, Course department, String campus, AcademicTitle academicTitle, String specialization) {
    Professor professor = new Professor(name, cpf, email, phone, birthDate, employeeId, department, campus, academicTitle, specialization);
    add(professor);
  }

  public void createExternal(String name, String cpf, String email, String phone, LocalDate birthDate, String organization, String jobRole, String bio, Boolean isPresenter) {
    External external = new External(name, cpf, email, phone, birthDate, organization, jobRole, bio, isPresenter);
    add(external);
  }

}