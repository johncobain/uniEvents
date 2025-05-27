package br.edu.ifba.inf0008.uniEvents.model.participants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf0008.uniEvents.utils.Colors;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;

public class External extends Participant {
  private final String participantTypeJson = "External";

  private String organization;
  private String jobRole;
  private String bio;
  private Boolean isPresenter;
  private List<String> expertiseAreas = new ArrayList<>();

  public External(String name, String cpf, String email, String phone, LocalDate birthDate, String organization, String jobRole, String bio, Boolean isPresenter) {
    super(name, cpf, email, phone, birthDate);
    this.organization = organization;
    this.jobRole = jobRole;
    this.bio = bio;
    this.isPresenter = isPresenter;
    this.expertiseAreas = new ArrayList<>();
  }
  
  public External() {
    super();
  } //Gson

  public String getParticipantTypeJson() {
    return participantTypeJson;
  }

  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public String getJobRole() {
    return jobRole;
  }

  public void setJobRole(String jobRole) {
    this.jobRole = jobRole;
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

  public void addExpertiseArea(String expertiseArea) {
    this.expertiseAreas.add(expertiseArea);
  }
  public void removeExpertiseArea(String expertiseArea) {
    this.expertiseAreas.remove(expertiseArea);
  }

  public String getFormatedBio(){
    StringBuilder sb = new StringBuilder();
    sb.append(Lines.mixedLines()).append("\n");
    sb.append(Lines.titleLine("Biography", Colors.BLUE_BOLD)).append("\n");
    sb.append(Lines.mixedLines()).append("\n");
    sb.append(Lines.multiLineText(bio)).append("\n");
    sb.append(Lines.mixedLines()).append("\n");

    return sb.toString();
  }

  @Override
  public String getType() {
    return participantTypeJson;
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append(Lines.multiLineText("Organization: " + organization)).append("\n");
    sb.append(Lines.multiLineText("Job Role: " + jobRole)).append("\n");
    sb.append(Lines.multiLineText("Is Presenter: " + (isPresenter ? "Yes" : "No"))).append("\n");
    if(expertiseAreas.isEmpty()){
      sb.append(Lines.multiLineText("Expertise Areas: None")).append("\n");
    } else {
      sb.append(Lines.multiLineText("Expertise Areas: ")).append("\n");
      for (String area : expertiseAreas) {
        sb.append(Lines.multiLineText(String.format("  - %s", area))).append("\n");
    }
    }
    sb.append(certificatesSummary());
    return sb.toString();
  }
}
