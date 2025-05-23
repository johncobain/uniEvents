package br.edu.ifba.inf0008.uniEvents.model.participants;

import java.time.LocalDate;

public class External extends Participant {
  private String participanTypeJson = "External";

  public External(String name, String cpf, String email, String phone, LocalDate birthDate, String gender) {
    super(name, cpf, email, phone, birthDate, gender);
  }
  
}
