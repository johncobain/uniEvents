package br.edu.ifba.inf0008.uniEvents.model.participants;

import java.time.LocalDate;

public class Student extends Participant{
  private final String participantTypeJson = "Student";

  public Student(String name, String cpf, String email, String phone, LocalDate birthDate, String gender) {
    super(name, cpf, email, phone, birthDate, gender);
  }
  public Student() {
    super();
  } //Gson

  @Override
  public String getParticipantType() {
    return participantTypeJson;
  }
}
