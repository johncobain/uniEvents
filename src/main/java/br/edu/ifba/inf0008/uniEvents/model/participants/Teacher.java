package br.edu.ifba.inf0008.uniEvents.model.participants;

import java.time.LocalDate;

public class Teacher extends Participant{
  private String participantTypeJson = "Teacher";

  public Teacher(String name, String cpf, String email, String phone, LocalDate birthDate, String gender) {
    super(name, cpf, email, phone, birthDate, gender);
  }
}
