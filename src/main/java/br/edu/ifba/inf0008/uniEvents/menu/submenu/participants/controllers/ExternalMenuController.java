package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.controllers;

import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.model.participants.External;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class ExternalMenuController {

  public Boolean create(ParticipantManager participantManager, String name, String cpf, String email, String phone, String birthDateString) {
    String affiliation = ParticipantForms.getName("affiliation");  
    if (affiliation.equalsIgnoreCase("cancel")) return false;
    
    String job = ParticipantForms.getName("job");
    if (job.equalsIgnoreCase("cancel")) return false;

    String bio = ParticipantForms.getName("bio");
    if (bio.equalsIgnoreCase("cancel")) return false;

    String isPresenterString = ParticipantForms.getYN("Can the external be a presenter", "y");
    if (isPresenterString.equalsIgnoreCase("cancel")) return false;
    Boolean isPresenter = isPresenterString.equalsIgnoreCase("y");
    if (isPresenterString.equalsIgnoreCase("n")) isPresenter = false;
    
    participantManager.createExternal(name, cpf, email, phone, Utils.stringToDate(birthDateString), affiliation, job, bio, isPresenter);
    return true;
  }

  public External update(ParticipantManager participantManager, String name, String cpf, String email, String phone, String birthDateString) {
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

    return new External(name, cpf, email, phone, Utils.stringToDate(birthDateString), organization, jobRole, bio, isPresenter);
  }

  public void addExpertiseArea(ParticipantManager participantManager, String cpf){
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

  public void removeExpertiseArea(ParticipantManager participantManager, String cpf){
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
