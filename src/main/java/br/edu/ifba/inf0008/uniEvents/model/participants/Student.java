package br.edu.ifba.inf0008.uniEvents.model.participants;

import java.time.LocalDate;

public class Student extends Participant{
  private String participantTypeJson = "Student";

  public Student(String name, String cpf, String email, String phone, LocalDate birthDate) {
    super(name, cpf, email, phone, birthDate);
  }
  
  
}
