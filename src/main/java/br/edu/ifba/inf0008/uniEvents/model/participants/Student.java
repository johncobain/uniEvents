package br.edu.ifba.inf0008.uniEvents.model.participants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf0008.uniEvents.model.participants.enums.AcademicStatus;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.Course;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.json.LocalDateAdapter;

public class Student extends Participant{
  private final String participantTypeJson = "Student";

  private String studentId;
  private Course course;
  private int currentSemester;
  AcademicStatus academicStatus;
  private double gpa;
  private String campus;
  LocalDate enrollmentDate;
  List<String> interests = new ArrayList<>();


  public Student(String name, String cpf, String email, String phone, LocalDate birthDate, String studentId, Course course, int currentSemester, AcademicStatus academicStatus, double gpa, String campus, LocalDate enrollmentDate) {
    super(name, cpf, email, phone, birthDate);
    this.studentId = studentId;
    this.course = course;
    this.currentSemester = currentSemester;
    this.academicStatus = academicStatus;
    this.gpa = gpa;
    this.campus = campus;
    this.enrollmentDate = enrollmentDate;
  }
  public Student() {
    super();
  } //Gson

  public String getStudentId() {
    return studentId;
  }
  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }
  public Course getCourse() {
    return course;
  }
  public void setCourse(Course course) {
    this.course = course;
  }
  public int getCurrentSemester() {
    return currentSemester;
  }
  public void setCurrentSemester(int currentSemester) {
    this.currentSemester = currentSemester;
  }
  public AcademicStatus getAcademicStatus() {
    return academicStatus;
  }
  public void setAcademicStatus(AcademicStatus academicStatus) {
    this.academicStatus = academicStatus;
  }
  public double getGpa() {
    return gpa;
  }
  public void setGpa(double gpa) {
    this.gpa = gpa;
  }
  public String getCampus() {
    return campus;
  }
  public void setCampus(String campus) {
    this.campus = campus;
  }
  public LocalDate getEnrollmentDate() {
    return enrollmentDate;
  }
  public void setEnrollmentDate(LocalDate enrollmentDate) {
    this.enrollmentDate = enrollmentDate;
  }

  public List<String> getInterests() {
    return interests;
  }
  public void setInterests(List<String> interests) {
    this.interests = interests;
  }

  public void addInterest(String interest) {
    this.interests.add(interest);
  }

  public void removeInterest(String interest) {
    this.interests.remove(interest);
  }
  
  @Override
  public String getType() {
    return participantTypeJson;
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append(Lines.multiLineText(String.format("Student ID: %s", studentId))).append("\n");
    sb.append(Lines.multiLineText(String.format("Course: %s", course.getDescription()))).append("\n");
    sb.append(Lines.multiLineText(String.format("Current Semester: %d", currentSemester))).append("\n");
    sb.append(Lines.multiLineText(String.format("Academic Status: %s", academicStatus.getDescription()))).append("\n");
    sb.append(Lines.multiLineText(String.format("GPA: %.2f", gpa))).append("\n");
    sb.append(Lines.multiLineText(String.format("Campus: %s", campus))).append("\n");
    sb.append(Lines.multiLineText(String.format("Enrollment Date: %s", enrollmentDate.format(LocalDateAdapter.DATE_FORMATTER)))).append("\n");
    if(interests.isEmpty()){
      sb.append(Lines.multiLineText("Interests: None")).append("\n");
    }else{
      sb.append(Lines.multiLineText("Interests:")).append("\n");
      for (String interest : interests) {
        sb.append(Lines.multiLineText(String.format("  - %s", interest))).append("\n");
      }
    }
    sb.append(certificatesSummary());
    return sb.toString();
  }
}
