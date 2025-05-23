package br.edu.ifba.inf0008.uniEvents.menu.submenu.events;

import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;

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
    do { 
      response = super.menuResponse();

      switch (response) {
          case 0 -> {
              return;
          }
          case 1 -> {
            Boolean created = eventMenuController.create();
            if (created) {
              System.out.println(Lines.clear());
              System.out.println(Lines.straightLine());
              System.out.println(Lines.successLine("Event created!"));
              System.out.println(Lines.straightLine());
            }else{
              System.out.println(Lines.clear());
              System.out.println(Lines.straightLine());
              System.out.println(Lines.errorLine("Error creating event!"));
              System.out.println(Lines.straightLine());
            }
          }
          case 2 -> {
            Boolean removed = eventMenuController.remove();
            if (removed) {
              System.out.println(Lines.clear());
              System.out.println(Lines.straightLine());
              System.out.println(Lines.successLine("Event removed!"));
              System.out.println(Lines.straightLine());
            }else{
              System.out.println(Lines.straightLine());
              System.out.println(Lines.errorLine("Error removing event!"));
              System.out.println(Lines.straightLine());
            }
          }
          case 3 -> {
            Boolean updated = eventMenuController.update();
            if (updated) {
              System.out.println(Lines.clear());
              System.out.println(Lines.straightLine());
              System.out.println(Lines.successLine("Event updated!"));
              System.out.println(Lines.straightLine());
            }else{
              System.out.println(Lines.straightLine());
              System.out.println(Lines.errorLine("Error updating event!"));
              System.out.println(Lines.straightLine());
            }
          }
          case 4 -> {
            eventMenuController.listAll();
          }
          case 5 -> {
            eventMenuController.listByType();
          }
          case 6 -> {
            eventMenuController.showEventParticipants();
          }
          case 7 -> {
            Boolean cleared = eventMenuController.clearAll();
            if (cleared) {
              System.out.println(Lines.clear());
              System.out.println(Lines.straightLine());
              System.out.println(Lines.successLine("All events cleared!"));
              System.out.println(Lines.straightLine());
            }else{
              System.out.println(Lines.clear());
              System.out.println(Lines.straightLine());
              System.out.println(Lines.errorLine("Error clearing events!"));
              System.out.println(Lines.straightLine());
            }
          }
          default -> throw new AssertionError();
      }
    } while (true);
  }
}
