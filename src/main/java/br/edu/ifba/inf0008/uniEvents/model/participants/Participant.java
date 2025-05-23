package br.edu.ifba.inf0008.uniEvents.model.participants;

import java.time.LocalDate;

public abstract class Participant {
  private String name;
  private String cpf;
  private String email;
  private String phone;
  private LocalDate birthDate;
  private String gender;

  public Participant( String name, String cpf, String email, String phone, LocalDate birthDate, String gender) {
    this.name = name;
    this.cpf = cpf;
    this.email = email;
    this.phone = phone;
    this.birthDate = birthDate;
    this.gender = gender;
  }

  protected Participant(){} //Gson

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public String getCpf() {
    return cpf;
  }
  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
  public LocalDate getBirthDate() {
    return birthDate;
  }
  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }
  

  public double getAge(){
    LocalDate today = LocalDate.now();
    int age = today.getYear() - birthDate.getYear();
    if (today.getDayOfYear() < birthDate.getDayOfYear()) {
      age--;
    }
    return age;
  }


  public abstract String getParticipantType();
}
