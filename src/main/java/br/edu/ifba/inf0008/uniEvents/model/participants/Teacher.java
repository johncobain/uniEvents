package br.edu.ifba.inf0008.uniEvents.model.participants;

import java.time.LocalDate;

public class Teacher extends Participant{
  private final String participantTypeJson = "Teacher";

  public Teacher(String name, String cpf, String email, String phone, LocalDate birthDate) {
    super(name, cpf, email, phone, birthDate);
  }
  public Teacher() {
    super();
  } //Gson


  @Override
  public String getType() {
    return participantTypeJson;
  }
}
