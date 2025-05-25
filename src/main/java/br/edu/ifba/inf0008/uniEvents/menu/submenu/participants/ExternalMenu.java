package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants;

import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class ExternalMenu extends Menu{
  private final ParticipantMenuController participantMenuController = new ParticipantMenuController();

  public ExternalMenu(ParticipantManager participantManager, EventManager eventManager) {
    super("External Management", Colors.BLUE_BOLD);
    super.addOption("Exit to Participant Menu");
    super.addOption("Create External");
    super.addOption("Remove External");
    super.addOption("Update External");
    super.addOption("List All Externals");
    super.addOption("Show External");
    super.addOption("Show External Events");
    super.addOption("Clear All Externals");
    super.addOption("Add External to Event");
    super.addOption("Remove External from Event");
    
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
            participantMenuController.remove();
          }
          case 3 -> {
            participantMenuController.update("External");
          }
          case 4 -> {
            participantMenuController.listType("External");
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
          default -> throw new AssertionError();
      }
    }
  }
  
}
