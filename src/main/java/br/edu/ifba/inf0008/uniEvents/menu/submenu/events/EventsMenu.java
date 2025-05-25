package br.edu.ifba.inf0008.uniEvents.menu.submenu.events;

import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class EventsMenu extends Menu {
  private final EventMenuController eventMenuController = new EventMenuController();

  public EventsMenu(EventManager eventManager) {
      super("Events Management", Colors.YELLOW_BOLD);
      super.addOption("Exit to Main Menu");
      super.addOption("Create Event");
      super.addOption("Remove Event");
      super.addOption("Update Event");
      super.addOption("List All Events");
      super.addOption("List Events by Type");
      super.addOption("Show Event Participants");
      super.addOption("Clear All Events");

      eventMenuController.setEventManager(eventManager);
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
            eventMenuController.create();
          }
          case 2 -> {
            eventMenuController.remove();
          }
          case 3 -> {
            eventMenuController.update();
          }
          case 4 -> {
            eventMenuController.listAll();
          }
          case 5 -> {
            eventMenuController.listByType();
          }
          case 6 -> {
            eventMenuController.showParticipants();
          }
          case 7 -> {
            eventMenuController.clearAll();
          }
          default -> throw new AssertionError();
      }
    }
  }
}
