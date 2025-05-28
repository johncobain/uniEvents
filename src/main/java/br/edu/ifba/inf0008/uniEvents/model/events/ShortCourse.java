package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;
import java.util.List;

import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.SkillLevel;
import br.edu.ifba.inf0008.uniEvents.model.participants.Student;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;

public class ShortCourse extends Event {
  private final String eventTypeJson = "Short Course";

  private int totalHours;
  private List<String> courseModules;
  private String methodOfAssessment;
  private SkillLevel targetSkillLevel;

  public ShortCourse(String name, String description, String location, LocalDate date, int capacity, Modality modality, String code, 
  int totalHours, List<String> courseModules, String methodOfAssessment, SkillLevel targetSkillLevel) {
    super(name, description, location, date, capacity, modality, code);
    this.totalHours = totalHours;
    this.courseModules = courseModules;
    this.methodOfAssessment = methodOfAssessment;
    this.targetSkillLevel = targetSkillLevel;
  }

  public ShortCourse() {
    super();
  } //Gson

  public Boolean checkEligibility(ParticipantManager participantManager,String cpf){
    return ((Student)participantManager.get(cpf)).getGpa() >= targetSkillLevel.minimumGpa();
  }

  @Override
  public String getType() {
    return eventTypeJson;
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append(Lines.multiLineText(String.format("Total Hours: %d", totalHours))).append("\n");
    sb.append(Lines.multiLineText(String.format("Course Modules: %s", String.join(" - ", courseModules)))).append("\n");
    sb.append(Lines.multiLineText(String.format("Method of Assessment: %s", methodOfAssessment))).append("\n");
    sb.append(Lines.multiLineText(String.format("Target Skill Level: %s", targetSkillLevel.getDescription()))).append("\n");
    return sb.toString();
  }
  
}
