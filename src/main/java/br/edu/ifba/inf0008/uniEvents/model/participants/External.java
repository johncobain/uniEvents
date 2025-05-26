package br.edu.ifba.inf0008.uniEvents.model.participants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class External extends Participant {
  private final String participantTypeJson = "External";
  //TODO: implement external specific attributes
  private String affiliation;
  private String job;
  private String bio; //TODO: implement bio as a text, max 60 chars per line, method see Bio responsible for this
  private Boolean isPresenter;
  private List<String> expertiseAreas = new ArrayList<>();

  public External(String name, String cpf, String email, String phone, LocalDate birthDate) {
    super(name, cpf, email, phone, birthDate);
  }
  
  public External() {
    super();
  } //Gson

  public String getParticipantTypeJson() {
    return participantTypeJson;
  }

  public String getAffiliation() {
    return affiliation;
  }

  public void setAffiliation(String affiliation) {
    this.affiliation = affiliation;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public Boolean getIsPresenter() {
    return isPresenter;
  }

  public void setIsPresenter(Boolean isPresenter) {
    this.isPresenter = isPresenter;
  }

  public List<String> getExpertiseAreas() {
    return expertiseAreas;
  }

  public void setExpertiseAreas(List<String> expertiseAreas) {
    this.expertiseAreas = expertiseAreas;
  }

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
