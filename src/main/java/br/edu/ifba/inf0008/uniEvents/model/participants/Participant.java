package br.edu.ifba.inf0008.uniEvents.model.participants;

import java.time.LocalDate;
import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.exceptions.UniEventsException;
import br.edu.ifba.inf0008.uniEvents.model.events.Certificate;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public abstract class Participant {
  private String name;
  private String cpf;
  private String email;
  private String phone;
  private LocalDate birthDate;
  private ArrayList<Certificate> certificates = new ArrayList<>();

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

  public ArrayList<Certificate> getCertificates() {
    return certificates;
  }

  public void setCertificates(ArrayList<Certificate> certificates) {
    this.certificates = certificates;
  }

  public void addCertificate(Certificate certificate) throws UniEventsException {
    if(certificates.contains(certificate)) throw new UniEventsException("Certificate already exists!");
    this.certificates.add(certificate);
  }

  public abstract String getType();

  public String certificatesSummary(){
    StringBuilder sb = new StringBuilder();
    if(this.getCertificates().isEmpty()){
      sb.append(Lines.multiLineText("Certificates: None")).append("\n");
    }else{
      sb.append(Lines.multiLineText("Certificates:")).append("\n");
      for (Certificate certificate : this.getCertificates()) {
        sb.append(Lines.multiLineText((String.format("  - %s", certificate.summary())))).append("\n");
      }
    }
    return sb.toString();
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append(Lines.multiLineText(String.format("Name: %s", name))).append("\n");
    sb.append(Lines.multiLineText(String.format("CPF: %s", cpf))).append("\n");
    sb.append(Lines.multiLineText(String.format("Email: %s", email))).append("\n");
    sb.append(Lines.multiLineText(String.format("Phone: %s", phone))).append("\n");
    sb.append(Lines.multiLineText(String.format("Birthdate: %s", Utils.dateToString(birthDate)))).append("\n");
    return sb.toString();
  }

}
