package br.edu.ifba.inf0008.uniEvents.model.participants;

import java.time.LocalDate;

public class External extends Participant {
  private final String participanTypeJson = "External";

  public External(String name, String cpf, String email, String phone, LocalDate birthDate) {
    super(name, cpf, email, phone, birthDate);
  }
  
  public External() {
    super();
  } //Gson


  @Override
  public String getType() {
    return participanTypeJson;
  }
}
