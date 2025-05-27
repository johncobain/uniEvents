package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.controllers;

import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.model.participants.Professor;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.AcademicTitle;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.Course;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class ProfessorMenuController {

  public static Boolean create(ParticipantManager participantManager, String name, String cpf, String email, String phone, String birthDateString) {
    String employeeId;
    while (true) { 
      employeeId = ParticipantForms.getId("employee id");
      if (participantManager.isIdAlreadyInUse(employeeId)) {
        System.out.println("Employee ID '" + employeeId + "' is already in use! Please try again.");
        continue;
      }
      break;
    }
    if (employeeId.equalsIgnoreCase("cancel")) return false;

    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    for (Course course : Course.getAll()) options.add(course.getDescription());

    String department = ParticipantForms.getOption(options, "Department");
    if (department.equalsIgnoreCase("cancel")) return false;

    String campus = ParticipantForms.getName("Campus");
    if (campus.equalsIgnoreCase("cancel")) return false;

    options = new ArrayList<>();
    options.add("Cancel");
    for (AcademicTitle title : AcademicTitle.getAll()) options.add(title.getDescription());
    String academicTitle = ParticipantForms.getOption(options, "Academic Title");
    if (academicTitle.equalsIgnoreCase("cancel")) return false;

    String specialization = ParticipantForms.getName("specialization");
    if (specialization.equalsIgnoreCase("cancel")) return false;

    participantManager.createProfessor(name, cpf, email, phone, Utils.stringToDate(birthDateString), employeeId, Course.fromDescription(department), campus, AcademicTitle.fromDescription(academicTitle), specialization);
    return true;
  }
  
  public static Professor update(ParticipantManager participantManager, String name, String cpf, String email, String phone, String birthDateString) {
    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    for (Course course : Course.getAll())options.add(course.getDescription());

    String department = ParticipantForms.getOption(options, "Department");
    if (department.equalsIgnoreCase("cancel")) return null;

    options = new ArrayList<>();
    options.add("Cancel");
    for (AcademicTitle title : AcademicTitle.getAll())options.add(title.getDescription());
    String academicTitle = ParticipantForms.getOption(options, "Academic Title");
    if (academicTitle.equalsIgnoreCase("cancel")) return null;

    String campus = ParticipantForms.getName("Campus");
    if (campus.equalsIgnoreCase("cancel")) return null;

    String specialization = ParticipantForms.getName("specialization");
    if (specialization.equalsIgnoreCase("cancel")) return null;

    return new Professor(name, cpf, email, phone, Utils.stringToDate(birthDateString), ((Professor) participantManager.get(cpf)).getEmployeeId(), Course.fromDescription(department), campus, AcademicTitle.fromDescription(academicTitle), specialization);
  }

  public static void addResearchArea(ParticipantManager participantManager, String cpf) {
    String researchArea = ParticipantForms.getName("research area");
    if (researchArea.equalsIgnoreCase("cancel")) return;
    try {
      Professor professor = (Professor)participantManager.get(cpf);
      professor.addResearchArea(researchArea);
      participantManager.update(cpf, professor);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Research area added successfully!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public static void removeResearchArea(ParticipantManager participantManager, String cpf) {
    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    options.addAll(((Professor)participantManager.get(cpf)).getResearchAreas());
    String researchArea = ParticipantForms.getOption(options, "Research Area");
    if (researchArea.equalsIgnoreCase("cancel")) return;
    try {
      Professor professor = (Professor)participantManager.get(cpf);
      professor.removeResearchArea(researchArea);
      participantManager.update(cpf, professor);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Research area removed!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public static void addPublication(ParticipantManager participantManager, String cpf) {
    String publication = ParticipantForms.getName("publication");
    if (publication.equalsIgnoreCase("cancel")) return;

    String year = ParticipantForms.getYear("publication year");
    if (year.equalsIgnoreCase("cancel")) return;
    publication += " (" + year + ")";
    try {
      Professor professor = (Professor)participantManager.get(cpf);
      professor.addPublication(publication);
      participantManager.update(cpf, professor);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Publication added successfully!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public static void removePublication(ParticipantManager participantManager, String cpf) {
    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    options.addAll(((Professor)participantManager.get(cpf)).getPublications());
    String publication = ParticipantForms.getOption(options, "Publication");
    if (publication.equalsIgnoreCase("cancel")) return;
    try {
      Professor professor = (Professor)participantManager.get(cpf);
      professor.removePublication(publication);
      participantManager.update(cpf, professor);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Publication removed!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }
}
