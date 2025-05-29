package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.controllers;

import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.forms.CommonForms;
import br.edu.ifba.inf0008.uniEvents.forms.ParticipantForms;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.model.participants.Professor;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.AcademicTitle;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.Course;
import br.edu.ifba.inf0008.uniEvents.services.IManager;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class ProfessorMenuController {

  public static Professor getForm(IManager<Participant> participantManager, String name, String cpf, String email, String phone, String birthDateString) {
    String employeeId;
    if(participantManager.get(cpf) == null){
    while (true) {
      employeeId = ParticipantForms.getId("Employee ID");
      Boolean isUnique = true;
      for (Participant participant : participantManager.getAll().values().stream().filter(p -> p instanceof Professor).toList()) {
        if (((Professor) participant).getEmployeeId().equals(employeeId)) {
          System.out.println(Lines.clear());
          System.out.println(Lines.errorLine("Employee ID '" + employeeId + "' is already in use! Please try again."));
          isUnique = false;
          break;
        }
      }
      if (isUnique) break;
    }
    if (employeeId.equalsIgnoreCase("cancel")) return null;
    }else{
      employeeId = ((Professor)participantManager.get(cpf)).getEmployeeId();
    }

    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    for (Course course : Course.getAll()) options.add(course.getDescription());

    String department = CommonForms.getOption(options, "Select Department");
    if (department.equalsIgnoreCase("cancel")) return null;

    String campus = CommonForms.getText("Campus");
    if (campus.equalsIgnoreCase("cancel")) return null;

    options = new ArrayList<>();
    options.add("Cancel");
    for (AcademicTitle title : AcademicTitle.getAll()) options.add(title.getDescription());
    String academicTitle = CommonForms.getOption(options, "Select Academic Title");
    if (academicTitle.equalsIgnoreCase("cancel")) return null;

    String specialization = CommonForms.getText("Specialization");
    if (specialization.equalsIgnoreCase("cancel")) return null;

    Professor professor = new Professor(name, cpf, email, phone, Utils.stringToDate(birthDateString), employeeId, Course.fromDescription(department), campus, AcademicTitle.fromDescription(academicTitle), specialization);
    return professor;
  }

  public static void addResearchArea(IManager<Participant> participantManager, String cpf) {
    String researchArea = CommonForms.getText("Research Area");
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

  public static void removeResearchArea(IManager<Participant> participantManager, String cpf) {
    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    options.addAll(((Professor)participantManager.get(cpf)).getResearchAreas());
    String researchArea = CommonForms.getOption(options, "Select Research Area");
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

  public static void addPublication(IManager<Participant> participantManager, String cpf) {
    String publication = CommonForms.getText("Publication");
    if (publication.equalsIgnoreCase("cancel")) return;

    String year = CommonForms.getYear("Publication Year");
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

  public static void removePublication(IManager<Participant> participantManager, String cpf) {
    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    options.addAll(((Professor)participantManager.get(cpf)).getPublications());
    String publication = CommonForms.getOption(options, "Select Publication");
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
