package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.SkillLevel;
import br.edu.ifba.inf0008.uniEvents.model.participants.Student;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;

public final class ShortCourse extends Event {
  private final String eventTypeJson = "Short Course";

  // private String instructorCpf;
  private int totalHours;
  private List<String> courseModules;
  private String methodOfAssessment;
  private SkillLevel targetSkillLevel;

  public ShortCourse(String name, String description, String location, LocalDate date, int capacity, Modality modality, String code, 
  // Participant instructor, 
  int totalHours, String courseModules, String methodOfAssessment, SkillLevel targetSkillLevel) {
    super(name, description, location, date, capacity, modality, code);
    // assignInstructor(instructor);
    this.totalHours = totalHours;
    setCourseModules(courseModules);
    this.methodOfAssessment = methodOfAssessment;
    this.targetSkillLevel = targetSkillLevel;
  }

  public ShortCourse() {
    super();
  } //Gson

  public Boolean checkEligibility(ParticipantManager participantManager,String cpf){
    return ((Student)participantManager.get(cpf)).getGpa() >= targetSkillLevel.minimumGpa();
  }

  // public String getInstructorCpf() {
  //   return instructorCpf;
  // }

  // public void setInstructorCpf(String instructorCpf) {
  //   this.instructorCpf = instructorCpf;
  // }

  // public Participant getInstructor(){
  //   if(instructorCpf != null || getParticipants() == null) return null;
  //   return getParticipants().get(instructorCpf);
  // }

  // public void assignInstructor(Participant instructor){
  //   if(instructor instanceof Professor || instructor instanceof External){
  //     addParticipant(instructor);
  //     setInstructorCpf(instructor.getCpf());
  //   }
  // }

  // @Override
  // public void removeParticipant(String cpf) {
  //   if(cpf.equals(instructorCpf)){
  //     throw new IllegalArgumentException("Instructor cannot be removed from short course!");
  //   }
  //   super.removeParticipant(cpf);
  // }

  public int getTotalHours() {
    return totalHours;
  }

  public void setTotalHours(int totalHours) {
    this.totalHours = totalHours;
  }

  public List<String> getCourseModules() {
    return courseModules;
  }

  public void setCourseModules(String courseModules) {
    this.courseModules = new ArrayList<>(List.of(courseModules.split(";")));
  }

  public String getMethodOfAssessment() {
    return methodOfAssessment;
  }

  public void setMethodOfAssessment(String methodOfAssessment) {
    this.methodOfAssessment = methodOfAssessment;
  }

  public SkillLevel getTargetSkillLevel() {
    return targetSkillLevel;
  }

  public void setTargetSkillLevel(SkillLevel targetSkillLevel) {
    this.targetSkillLevel = targetSkillLevel;
  }

  @Override
  public String getType() {
    return eventTypeJson;
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    return sb.toString();
  }
  
}
