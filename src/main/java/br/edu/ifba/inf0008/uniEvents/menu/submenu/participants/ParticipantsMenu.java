package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants;
import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class ParticipantsMenu extends Menu {
  private final ParticipantMenuController participantMenuController = new ParticipantMenuController();

  public ParticipantsMenu(ParticipantManager participantManager, EventManager eventManager) {
      super("Participants Management", Colors.BLUE_BOLD);
      super.addOption("Exit to Main Menu");
      super.addOption("Create Participant");
      super.addOption("Remove Participant");
      super.addOption("Update Participant");
      super.addOption("List All Participants");
      super.addOption("List Participants by Type");
      super.addOption("Show Participant Events");
      super.addOption("Clear All Participants");
      super.addOption("Add Participant to Event");
      super.addOption("Remove Participant from Event");

      participantMenuController.setParticipantManager(participantManager);
      participantMenuController.setEventManager(eventManager);
  }

  @Override
  public void show() {
    int response;
    do { 
      response = super.menuResponse();

      switch (response) {
          case 0 -> {
            return;
          }
          case 1 -> {
            participantMenuController.create();
          }
          case 2 -> {
            participantMenuController.remove();
          }
          case 3 -> {
            participantMenuController.update();
          }
          case 4 -> {
            participantMenuController.listAll();
          }
          case 5 -> {
            participantMenuController.listByType();
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
    } while (true);
  }
}
