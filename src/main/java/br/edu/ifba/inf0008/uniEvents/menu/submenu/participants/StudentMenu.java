package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants;

import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.controllers.ParticipantMenuController;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class StudentMenu extends Menu{
  private final ParticipantMenuController participantMenuController = new ParticipantMenuController();

  public StudentMenu(ParticipantManager participantManager, EventManager eventManager) {
    super("Student Management", Colors.BLUE_BOLD);
    super.addOption("Exit to Participant Menu");
    super.addOption("Create Student");
    super.addOption("Show Student");
    super.addOption("List All Students");
    super.addOption("Clear All Students");
    
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
            participantMenuController.get("Student");
          }
          case 3 -> {
            participantMenuController.listType("Student");
          }
          case 4 -> {
            participantMenuController.clearAll("Student");
          }
          default -> throw new AssertionError();
      }
    }
  }
  
}
