package br.edu.ifba.inf0008.uniEvents.model.participants;

import java.time.LocalDate;

public class Teacher extends Participant{
  private final String participantTypeJson = "Teacher";

  public Teacher(String name, String cpf, String email, String phone, LocalDate birthDate, String gender) {
    super(name, cpf, email, phone, birthDate, gender);
  }
  public Teacher() {
    super();
  } //Gson


  @Override
  public String getParticipantType() {
    return participantTypeJson;
  }
}
