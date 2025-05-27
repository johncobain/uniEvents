package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.controllers;

import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.model.participants.Student;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.AcademicStatus;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.Course;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class StudentMenuController {
  public static Boolean create(ParticipantManager participantManager, String name, String cpf, String email, String phone, String birthDateString) {
    String studentId;
    while (true) { 
      studentId = ParticipantForms.getId("student id");
      if (participantManager.isIdAlreadyInUse(studentId)) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine("Student ID '" + studentId + "' is already in use! Please try again."));
        continue;
      }
      break;
    }
    if (studentId.equalsIgnoreCase("cancel")) return false;

    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    for(Course course : Course.getAll())options.add(course.getDescription());

    String course = ParticipantForms.getOption(options, "Course");
    if (course.equalsIgnoreCase("cancel")) return false;

    int currentSemester = ParticipantForms.getSemester();
    if (currentSemester == 0) return false;

    options = new ArrayList<>();
    options.add("Cancel");
    for(AcademicStatus status : AcademicStatus.getAll())options.add(status.getDescription());
    
    String status = ParticipantForms.getOption(options, "Academic Status");
    if (status.equalsIgnoreCase("cancel")) return false;

    double gpa = ParticipantForms.getGpa();
    if (gpa == 0) return false;

    String campus = ParticipantForms.getName("campus");
    if (campus.equalsIgnoreCase("cancel")) return false;

    String enrollmentDateString = ParticipantForms.getDate("enrollment date");
    if (enrollmentDateString.equalsIgnoreCase("cancel")) return false;

    participantManager.createStudent(name, cpf, email, phone, Utils.stringToDate(birthDateString), studentId, Course.fromDescription(course), currentSemester, AcademicStatus.fromDescription(status), gpa, campus, Utils.stringToDate(enrollmentDateString));
    return true;
  }
  
  public static Student update(ParticipantManager participantManager, String name, String cpf, String email, String phone, String birthDateString) {
    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    for(Course course : Course.getAll())options.add(course.getDescription());

    String course = ParticipantForms.getOption(options, "Course");
    if (course.equalsIgnoreCase("cancel")) return null;

    int currentSemester = ParticipantForms.getSemester();
    if (currentSemester == 0) return null;

    options = new ArrayList<>();
    options.add("Cancel");
    for(AcademicStatus status : AcademicStatus.getAll())options.add(status.getDescription());

    String status = ParticipantForms.getOption(options, "Academic Status");
    if (status.equalsIgnoreCase("cancel")) return null;

    double gpa = ParticipantForms.getGpa();
    if (gpa == 0) return null;

    String campus = ParticipantForms.getName("campus");
    if (campus.equalsIgnoreCase("cancel")) return null;

    String enrollmentDateString = ParticipantForms.getDate("enrollment date");
    if (enrollmentDateString.equalsIgnoreCase("cancel")) return null;

    return new Student(name, cpf, email, phone, Utils.stringToDate(birthDateString), ((Student)participantManager.get(cpf)).getStudentId(), Course.fromDescription(course), currentSemester, AcademicStatus.fromDescription(status), gpa, campus, Utils.stringToDate(enrollmentDateString));
  }

  public static void addInterest(ParticipantManager participantManager, String cpf){
    String interest = ParticipantForms.getName("interest");
    if (interest.equalsIgnoreCase("cancel")) return;
    try {
      Student student = (Student)participantManager.get(cpf);
      student.addInterest(interest);
      participantManager.update(cpf, student);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Interest added!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public static void removeInterest(ParticipantManager participantManager, String cpf){
    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    options.addAll(((Student)participantManager.get(cpf)).getInterests());
    String interest = ParticipantForms.getOption(options, "Interest");
    if (interest.equalsIgnoreCase("cancel")) return;
    try {
      Student student = (Student)participantManager.get(cpf);
      student.removeInterest(interest);
      participantManager.update(cpf, student);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Interest removed!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }
}
