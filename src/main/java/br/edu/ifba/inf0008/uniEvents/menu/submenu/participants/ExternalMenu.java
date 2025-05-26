package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants;

import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.controllers.ParticipantMenuController;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class ExternalMenu extends Menu{
  private final ParticipantMenuController participantMenuController = new ParticipantMenuController();

  public ExternalMenu(ParticipantManager participantManager, EventManager eventManager) {
    super("External Management", Colors.BLUE_BOLD);
    super.addOption("Exit to Participant Menu");
    super.addOption("Create External");
    super.addOption("Show External");
    super.addOption("List All Externals");
    super.addOption("Clear All Externals");
    
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
            participantMenuController.create("External");
          }
          case 2 -> {
            participantMenuController.get("External");
          }
          case 3 -> {
            participantMenuController.listType("External");
          }
          case 4 -> {
            participantMenuController.clearAll("External");
          }
          default -> throw new AssertionError();
      }
    }
  }
  
}
