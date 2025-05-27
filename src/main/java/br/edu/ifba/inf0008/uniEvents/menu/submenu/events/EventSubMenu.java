package br.edu.ifba.inf0008.uniEvents.menu.submenu.events;

import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.menu.submenu.events.controllers.EventMenuController;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class EventSubMenu extends Menu{
  private final EventMenuController eventMenuController;
  private final String eventType;

  public EventSubMenu(EventMenuController eventMenuController, String eventType) {
    super(eventType + " Management", Colors.YELLOW_BOLD);
    super.addOption("Exit to Event Menu");
    super.addOption("Create " + eventType);
    super.addOption("Show " + eventType);
    super.addOption("List All " + eventType + "s");
    super.addOption("Clear All " + eventType + "s");

    this.eventMenuController = eventMenuController;
    this.eventType = eventType;
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
            eventMenuController.create(eventType);
          }
          case 2 -> {
            eventMenuController.get(eventType);
          }
          case 3 -> {
            eventMenuController.list(eventType);
          }
          case 4 -> {
            eventMenuController.clear(eventType);
          }
          default -> throw new AssertionError();
      }
    }
  }
  
}
