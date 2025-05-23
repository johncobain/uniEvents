package br.edu.ifba.inf0008.uniEvents.model.participants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.edu.ifba.inf0008.uniEvents.utils.Lines;

public abstract class Participant {
  private String name;
  private String cpf;
  private String email;
  private String phone;
  private LocalDate birthDate;

  public Participant( String name, String cpf, String email, String phone, LocalDate birthDate) {
    this.name = name;
    this.cpf = cpf;
    this.email = email;
    this.phone = phone;
    this.birthDate = birthDate;
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
  

  public int getAge(){
    LocalDate today = LocalDate.now();
    int age = today.getYear() - birthDate.getYear();
    if (today.getDayOfYear() < birthDate.getDayOfYear()) {
      age--;
    }
    return age;
  }

  public abstract String getType();

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append(Lines.leftText(String.format("Participant: %s", getType()))).append("\n");
    sb.append(Lines.leftText(String.format("Name: %s", name))).append("\n");
    sb.append(Lines.leftText(String.format("CPF: %s", cpf))).append("\n");
    sb.append(Lines.leftText(String.format("Email: %s", email))).append("\n");
    sb.append(Lines.leftText(String.format("Phone: %s", phone))).append("\n");
    sb.append(Lines.leftText(String.format("Birthdate: %s", birthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))))).append("\n");
    return sb.toString();
  }
}
