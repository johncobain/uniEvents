package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants;

import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class StudentMenu extends Menu{
  private final ParticipantMenuController participantMenuController = new ParticipantMenuController();

  public StudentMenu(ParticipantManager participantManager, EventManager eventManager) {
    super("Student Management", Colors.BLUE_BOLD);
    super.addOption("Exit to Participant Menu");
    super.addOption("Create Student");
    super.addOption("Remove Student");
    super.addOption("Update Student");
    super.addOption("List All Students");
    super.addOption("Show Student");
    super.addOption("Show Student Events");
    super.addOption("Clear All Students");
    super.addOption("Add Student to Event");
    super.addOption("Remove Student from Event");
    super.addOption("Add Student Interest");
    super.addOption("Remove Student Interest");
    
    participantMenuController.setParticipantManager(participantManager);
    participantMenuController.setEventManager(eventManager);
  }

  @Override
  public void show() {
    int response;
    while(true){ 
      response = super.menuResponse();

      switch (response) {
          case 0 -> {
            return;
          }
          case 1 -> {
            participantMenuController.create("Student");
          }
          case 2 -> {
            participantMenuController.remove();
          }
          case 3 -> {
            participantMenuController.update("Student");
          }
          case 4 -> {
            participantMenuController.listAll();
          }
          case 5 -> {
            participantMenuController.get();
          }
          case 6 -> {
            participantMenuController.showEvents();
          }
          case 7 -> {
            participantMenuController.clearAll();
          }
          case 8 -> {
            participantMenuController.addToEvent();
          }
          case 9 -> {
            participantMenuController.removeFromEvent();
          }
          case 10 -> {
            participantMenuController.addInterest();
          }
          case 11 -> {
            participantMenuController.removeInterest();
          }
          default -> throw new AssertionError();
      }
    }
  }
  
}
