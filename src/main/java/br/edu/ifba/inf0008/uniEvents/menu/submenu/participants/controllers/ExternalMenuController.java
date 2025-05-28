package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.controllers;

import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.model.participants.External;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.services.IManager;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class ExternalMenuController {

  public static Boolean create(IManager<Participant> participantManager, String name, String cpf, String email, String phone, String birthDateString) {
    String affiliation = ParticipantForms.getName("affiliation");  
    if (affiliation.equalsIgnoreCase("cancel")) return false;
    
    String job = ParticipantForms.getName("job");
    if (job.equalsIgnoreCase("cancel")) return false;

    String bio = ParticipantForms.getName("bio");
    if (bio.equalsIgnoreCase("cancel")) return false;

    String isPresenterString = ParticipantForms.getYN("Can the external be a presenter?", "y");
    if (isPresenterString.equalsIgnoreCase("cancel")) return false;
    Boolean isPresenter = isPresenterString.equalsIgnoreCase("y");
    if (isPresenterString.equalsIgnoreCase("n")) isPresenter = false;
    
    External external = new External(name, cpf, email, phone, Utils.stringToDate(birthDateString), affiliation, job, bio, isPresenter);
    participantManager.add(external);
    return true;
  }

  public static Boolean update(IManager<Participant> participantManager, String name, String cpf, String email, String phone, String birthDateString) {
    String organization = ParticipantForms.getName("organization");  
    if (organization.equalsIgnoreCase("cancel")) return null;
    
    String jobRole = ParticipantForms.getName("jobRole");
    if (jobRole.equalsIgnoreCase("cancel")) return null;

    String bio = ParticipantForms.getName("bio");
    if (bio.equalsIgnoreCase("cancel")) return null;

    String isPresenterString = ParticipantForms.getYN("Can the external be a presenter", "y");
    if (isPresenterString.equalsIgnoreCase("cancel")) return null;
    Boolean isPresenter = isPresenterString.equalsIgnoreCase("y");
    if (isPresenterString.equalsIgnoreCase("n")) isPresenter = false;

    External external = new External(name, cpf, email, phone, Utils.stringToDate(birthDateString), organization, jobRole, bio, isPresenter);
    participantManager.update(cpf, external);
    return true;
  }

  public static void addExpertiseArea(IManager<Participant> participantManager, String cpf){
    String expertiseArea = ParticipantForms.getName("expertise area");
    if (expertiseArea.equalsIgnoreCase("cancel")) return;
    try {
      External external = (External) participantManager.get(cpf);
      external.addExpertiseArea(expertiseArea);
      participantManager.update(cpf, external);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Expertise area added successfully!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
    }
  }

  public static void removeExpertiseArea(IManager<Participant> participantManager, String cpf){
    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    options.addAll(((External)participantManager.get(cpf)).getExpertiseAreas());
    String expertiseArea = ParticipantForms.getOption(options, "Expertise Area");
    if (expertiseArea.equalsIgnoreCase("cancel")) return;
    try {
      External external = (External) participantManager.get(cpf);
      external.removeExpertiseArea(expertiseArea);
      participantManager.update(cpf, external);
      System.out.println(Lines.clear());
      System.out.println(Lines.successLine("Expertise area removed successfully!"));
    } catch (Exception e) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(e.getMessage()));
   }
  }
  
}
