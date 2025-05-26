package br.edu.ifba.inf0008.uniEvents.model.participants;

import java.time.LocalDate;

public class External extends Participant {
  private final String participantTypeJson = "External";

  public External(String name, String cpf, String email, String phone, LocalDate birthDate) {
    super(name, cpf, email, phone, birthDate);
  }
  
  public External() {
    super();
  } //Gson


  @Override
  public String getType() {
    return participantTypeJson;
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    
    sb.append(certificatesSummary());
    return sb.toString();
  }
}
