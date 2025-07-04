package br.edu.ifba.inf0008.uniEvents.model.participants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf0008.uniEvents.model.participants.enums.AcademicTitle;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.Course;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;

public class Professor extends Participant{
  private final String participantTypeJson = "Professor";

  private String employeeId;
  private Course department;
  public String campus;
  private AcademicTitle academicTitle;
  private String specialization;
  List<String> researchAreas = new ArrayList<>();
  List<String> publications = new ArrayList<>();

  public Professor(String name, String cpf, String email, String phone, LocalDate birthDate, String employeeId, Course department, String campus, AcademicTitle academicTitle, String specialization) {
    super(name, cpf, email, phone, birthDate);
    this.employeeId = employeeId;
    this.department = department;
    this.campus = campus;
    this.academicTitle = academicTitle;
    this.specialization = specialization;
  }
  public Professor() {
    super();
  } //Gson

  public String getEmployeeId() {
    return employeeId;
  }
  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }
  public Course getDepartment() {
    return department;
  }
  public void setDepartment(Course department) {
    this.department = department;
  }
  public String getCampus() {
    return campus;
  }
  public void setCampus(String campus) {
    this.campus = campus;
  }
  public AcademicTitle getAcademicTitle() {
    return academicTitle;
  }
  public void setAcademicTitle(AcademicTitle academicTitle) {
    this.academicTitle = academicTitle;
  }
  public String getSpecialization() {
    return specialization;
  }
  public void setSpecialization(String specialization) {
    this.specialization = specialization;
  }
  public List<String> getResearchAreas() {
    return researchAreas;
  }
  public void setResearchAreas(List<String> researchAreas) {
    this.researchAreas = researchAreas;
  }
  
  public void addResearchArea(String researchArea) {
    this.researchAreas.add(researchArea);
  }

  public void removeResearchArea(String researchArea) {
    this.researchAreas.remove(researchArea);
  }

  public List<String> getPublications() {
    return publications;
  }
  public void setPublications(List<String> publications) {
    this.publications = publications;
  }
  public void addPublication(String publication) {
    this.publications.add(publication);
  }
  public void removePublication(String publication) {
    this.publications.remove(publication);
  }

  @Override
  public String getType() {
    return participantTypeJson;
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append(Lines.multiLineText(String.format("Employee ID: %s", employeeId))).append("\n");
    sb.append(Lines.multiLineText(String.format("Department: %s", department.getDescription()))).append("\n");
    sb.append(Lines.multiLineText(String.format("Campus: %s", campus))).append("\n");
    sb.append(Lines.multiLineText(String.format("Academic Title: %s", academicTitle.getDescription()))).append("\n");
    sb.append(Lines.multiLineText(String.format("Specialization: %s", specialization))).append("\n");
    if(researchAreas.isEmpty()) {
      sb.append(Lines.multiLineText("Research Areas: None")).append("\n");
    } else {
      sb.append(Lines.multiLineText("Research Areas: ")).append("\n");
      for (String area : researchAreas) {
        sb.append(Lines.multiLineText(String.format("  - %s", area))).append("\n");
      }
    }
    if(publications.isEmpty()) {
      sb.append(Lines.multiLineText("Publications: None")).append("\n");
    } else {
      sb.append(Lines.multiLineText("Publications: ")).append("\n");
      for (String publication : publications) {
        sb.append(Lines.multiLineText(String.format("  - %s", publication))).append("\n");
      }
    }
    sb.append(certificatesSummary());
    return sb.toString();
  }
}
