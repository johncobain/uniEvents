package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.controllers;

import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.forms.CommonForms;
import br.edu.ifba.inf0008.uniEvents.forms.ParticipantForms;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.model.participants.Student;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.AcademicStatus;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.Course;
import br.edu.ifba.inf0008.uniEvents.services.IManager;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class StudentMenuController {
  public static Boolean create(IManager<Participant> participantManager, String name, String cpf, String email, String phone, String birthDateString) {
    String studentId;
    while (true) { 
      studentId = ParticipantForms.getId("student id");
      Boolean isUnique = true;
      for (Participant participant : participantManager.getAll().values()) {
        if (((Student) participant).getStudentId().equals(studentId)) {
          System.out.println(Lines.clear());
          System.out.println(Lines.errorLine("Student ID '" + studentId + "' is already in use! Please try again."));
          isUnique = false;
          break;
        }
      }
      if (isUnique) break;
    }
    if (studentId.equalsIgnoreCase("cancel")) return false;

    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    for(Course course : Course.getAll())options.add(course.getDescription());

    String course = CommonForms.getOption(options, "Select Course");
    if (course.equalsIgnoreCase("cancel")) return false;

    int currentSemester = ParticipantForms.getSemester();
    if (currentSemester == 0) return false;

    options = new ArrayList<>();
    options.add("Cancel");
    for(AcademicStatus status : AcademicStatus.getAll())options.add(status.getDescription());

    String status = CommonForms.getOption(options, "Select Academic Status");
    if (status.equalsIgnoreCase("cancel")) return false;

    double gpa = ParticipantForms.getGpa();
    if (gpa == 0) return false;

    String campus = ParticipantForms.getName("campus");
    if (campus.equalsIgnoreCase("cancel")) return false;

    String enrollmentDateString = CommonForms.getDate("enrollment date");
    if (enrollmentDateString.equalsIgnoreCase("cancel")) return false;

    Student student = new Student(name, cpf, email, phone, Utils.stringToDate(birthDateString), studentId, Course.fromDescription(course), currentSemester, AcademicStatus.fromDescription(status), gpa, campus, Utils.stringToDate(enrollmentDateString));
    participantManager.add(student);
    return true;
  }
  
  public static Boolean update(IManager<Participant> participantManager, String name, String cpf, String email, String phone, String birthDateString) {
    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    for(Course course : Course.getAll())options.add(course.getDescription());

    String course = CommonForms.getOption(options, "Select Course");
    if (course.equalsIgnoreCase("cancel")) return false;

    int currentSemester = ParticipantForms.getSemester();
    if (currentSemester == 0) return false;

    options = new ArrayList<>();
    options.add("Cancel");
    for(AcademicStatus status : AcademicStatus.getAll())options.add(status.getDescription());

    String status = CommonForms.getOption(options, "Select Academic Status");
    if (status.equalsIgnoreCase("cancel")) return false;

    double gpa = ParticipantForms.getGpa();
    if (gpa == 0) return false;

    String campus = ParticipantForms.getName("campus");
    if (campus.equalsIgnoreCase("cancel")) return false;

    String enrollmentDateString = CommonForms.getDate("enrollment date");
    if (enrollmentDateString.equalsIgnoreCase("cancel")) return false;

    Student student = new Student(name, cpf, email, phone, Utils.stringToDate(birthDateString), ((Student)participantManager.get(cpf)).getStudentId(), Course.fromDescription(course), currentSemester, AcademicStatus.fromDescription(status), gpa, campus, Utils.stringToDate(enrollmentDateString));
    participantManager.update(cpf, student);
    return true;
  }

  public static void addInterest(IManager<Participant> participantManager, String cpf){
    String interest = ParticipantForms.getName("interest");
    if (interest.equalsIgnoreCase("cancel")) return;
    try {
      Student student = (Student)participantManager.get(cpf);
      student.addInterest(interest);
      participantManager.update(cpf, student);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Interest added to " + student.getName() + "!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public static void removeInterest(IManager<Participant> participantManager, String cpf){
    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    options.addAll(((Student)participantManager.get(cpf)).getInterests());
    String interest = CommonForms.getOption(options, "Select Interest");
    if (interest.equalsIgnoreCase("cancel")) return;
    try {
      Student student = (Student)participantManager.get(cpf);
      student.removeInterest(interest);
      participantManager.update(cpf, student);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Interest removed from " + student.getName() + "!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }
}
