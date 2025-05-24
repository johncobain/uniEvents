package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants;
import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;

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
    // IMenu submenu;
    do { 
      response = super.menuResponse();

      switch (response) {
          case 0 -> {
            return;
          }
          case 1 -> {
            Boolean created = participantMenuController.create();
            if (created) {
              System.out.println(Lines.clear());
              System.out.println(Lines.straightLine());
              System.out.println(Lines.successLine("Participant created!"));
              System.out.println(Lines.straightLine());
            }else{
              System.out.println(Lines.clear());
              System.out.println(Lines.straightLine());
              System.out.println(Lines.errorLine("Error creating participant!"));
              System.out.println(Lines.straightLine());
            }
          }
          case 2 -> {
            Boolean removed = participantMenuController.remove();
            if (removed) {
              System.out.println(Lines.clear());
              System.out.println(Lines.straightLine());
              System.out.println(Lines.successLine("Participant removed!"));
              System.out.println(Lines.straightLine());
            }else{
              System.out.println(Lines.clear());
              System.out.println(Lines.straightLine());
              System.out.println(Lines.errorLine("Error removing participant!"));
              System.out.println(Lines.straightLine());
            }
          }
          case 3 -> {
            Boolean updated = participantMenuController.update();
            if (updated) {
              System.out.println(Lines.clear());
              System.out.println(Lines.straightLine());
              System.out.println(Lines.successLine("Participant updated!"));
              System.out.println(Lines.straightLine());
            }else{
              System.out.println(Lines.clear());
              System.out.println(Lines.straightLine());
              System.out.println(Lines.errorLine("Error updating participant!"));
              System.out.println(Lines.straightLine());
            }
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
            Boolean cleared = participantMenuController.clearAll();
            if (cleared) {
              System.out.println(Lines.clear());
              System.out.println(Lines.straightLine());
              System.out.println(Lines.successLine("All participants cleared!"));
              System.out.println(Lines.straightLine());
            }else{
              System.out.println(Lines.clear());
              System.out.println(Lines.straightLine());
              System.out.println(Lines.errorLine("Error clearing participants!"));
              System.out.println(Lines.straightLine());
            }
          }
          case 8 -> {
            Boolean added = participantMenuController.addToEvent();
            if (added) {
              System.out.println(Lines.clear());
              System.out.println(Lines.straightLine());
              System.out.println(Lines.successLine("Participant added to event!"));
              System.out.println(Lines.straightLine());
            }else{
              System.out.println(Lines.straightLine());
              System.out.println(Lines.errorLine("Error adding participant to event!"));
              System.out.println(Lines.straightLine());
            }
          }
          case 9 -> {
            Boolean removed = participantMenuController.removeFromEvent();
            if (removed) {
              System.out.println(Lines.clear());
              System.out.println(Lines.straightLine());
              System.out.println(Lines.successLine("Participant removed from event!"));
              System.out.println(Lines.straightLine());
            }else{
              System.out.println(Lines.straightLine());
              System.out.println(Lines.errorLine("Error removing participant from event!"));
              System.out.println(Lines.straightLine());
            }
          }
          default -> throw new AssertionError();
      }
    } while (true);
  }
}
