package br.edu.ifba.inf0008.uniEvents.menu.submenu.events;

import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.menu.submenu.events.controllers.EventMenuController;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class EventsMenu extends Menu {
  private final EventMenuController eventMenuController = new EventMenuController();
  EventManager eventManager;

  public EventsMenu(EventManager eventManager) {
      super("Events Management", Colors.YELLOW_BOLD);
      super.addOption("Exit to Main Menu");
      super.addOption("Workshop Management");
      super.addOption("Lecture Management");
      super.addOption("Short Course Management");
      super.addOption("Academic Fair Management");
      super.addOption("Show Event");
      super.addOption("List All Events");
      super.addOption("Clear All Events");

      this.eventManager = eventManager;

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
            new EventSubMenu(eventManager, "Workshop").show();
          }
          case 2 -> {
            new EventSubMenu(eventManager, "Lecture").show();
          }
          case 3 -> {
            new EventSubMenu(eventManager, "Short Course").show();
          }
          case 4 -> {
            new EventSubMenu(eventManager, "Academic Fair").show();
          }
          case 5 -> {
            eventMenuController.get("Event");
          }
          case 6 -> {
            eventMenuController.list();
          }
          case 7 -> {
            eventMenuController.clear();
          }
          default -> throw new AssertionError();
      }
    }
  }
}
