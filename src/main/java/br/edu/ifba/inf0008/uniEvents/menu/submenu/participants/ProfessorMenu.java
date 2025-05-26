package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants;

import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.controllers.ParticipantMenuController;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class ProfessorMenu extends Menu{
  private final ParticipantMenuController participantMenuController = new ParticipantMenuController();

  public ProfessorMenu(ParticipantManager participantManager, EventManager eventManager) {
    super("Professor Management", Colors.BLUE_BOLD);
    super.addOption("Exit to Participant Menu");
    super.addOption("Create Professor");
    super.addOption("Show Professor");
    super.addOption("List All Professors");
    super.addOption("Clear All Professors");
    
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
            participantMenuController.create("Professor");
          }
          case 2 -> {
            participantMenuController.get("Professor");
          }
          case 3 -> {
            participantMenuController.listType("Professor");
          }
          case 4 -> {
            participantMenuController.clearAll("Professor");
          }
          default -> throw new AssertionError();
      }
    }
  }
  
}
