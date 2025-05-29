package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.controllers;

import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.forms.CommonForms;
import br.edu.ifba.inf0008.uniEvents.forms.ParticipantForms;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.model.participants.Student;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.AcademicStatus;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.Course;
import br.edu.ifba.inf0008.uniEvents.services.managers.IManager;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class StudentMenuController {
  public static Student getForm(IManager<Participant> participantManager, String name, String cpf, String email, String phone, String birthDateString) {
    String studentId;
    if(participantManager.get(cpf) == null){
      while (true) {
        studentId = ParticipantForms.getId("Student ID");
        Boolean isUnique = true;
        for (Participant participant : participantManager.getAll().values().stream().filter(p -> p instanceof Student).toList()) {
          if (((Student) participant).getStudentId().equals(studentId)) {
            System.out.println(Lines.clear());
            System.out.println(Lines.errorLine("Student ID '" + studentId + "' is already in use! Please try again."));
            isUnique = false;
            break;
          }
        }
        if (isUnique) break;
      }
      if (studentId.equalsIgnoreCase("cancel")) return null;
    }else{
      studentId = ((Student)participantManager.get(cpf)).getStudentId();
    }

    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    for(Course course : Course.getAll())options.add(course.getDescription());

    String course = CommonForms.getOption(options, "Select Course");
    if (course.equalsIgnoreCase("cancel")) return null;

    int currentSemester = CommonForms.getNumber("Current Semester");
    if (currentSemester == -1) return null;

    options = new ArrayList<>();
    options.add("Cancel");
    for(AcademicStatus status : AcademicStatus.getAll())options.add(status.getDescription());

    String status = CommonForms.getOption(options, "Select Academic Status");
    if (status.equalsIgnoreCase("cancel")) return null;

    double gpa = ParticipantForms.getGpa();
    if (gpa == 0) return null;

    String campus = CommonForms.getText("Campus");
    if (campus.equalsIgnoreCase("cancel")) return null;

    String enrollmentDateString = CommonForms.getDate("Enrollment Date");
    if (enrollmentDateString.equalsIgnoreCase("cancel")) return null;

    Student student = new Student(name, cpf, email, phone, Utils.stringToDate(birthDateString), studentId, Course.fromDescription(course), currentSemester, AcademicStatus.fromDescription(status), gpa, campus, Utils.stringToDate(enrollmentDateString));
    return student;
  }
  
  public static void addInterest(IManager<Participant> participantManager, String cpf){
    String interest = CommonForms.getText("Interest");
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
