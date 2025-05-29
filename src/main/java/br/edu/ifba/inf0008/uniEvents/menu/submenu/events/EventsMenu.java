package br.edu.ifba.inf0008.uniEvents.menu.submenu.events;

import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.menu.submenu.events.controllers.EventMenuController;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.services.IManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class EventsMenu extends Menu {
  private final EventMenuController eventMenuController;

  public EventsMenu(IManager<Event> eventManager, IManager<Participant> participantManager) {
      super("Events Management", Colors.YELLOW_BOLD);
      super.addOption("Exit to Main Menu");
      super.addOption("Workshop Management");
      super.addOption("Lecture Management");
      super.addOption("Short Course Management");
      super.addOption("Academic Fair Management");
      super.addOption("Show Event");
      super.addOption("List All Events");
      super.addOption("Clear All Events");

      this.eventMenuController = new EventMenuController(eventManager, participantManager);
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
            new EventSubMenu(eventMenuController, "Workshop").show();
          }
          case 2 -> {
            new EventSubMenu(eventMenuController, "Lecture").show();
          }
          case 3 -> {
            new EventSubMenu(eventMenuController, "Short Course").show();
          }
          case 4 -> {
            new EventSubMenu(eventMenuController, "Academic Fair").show();
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
