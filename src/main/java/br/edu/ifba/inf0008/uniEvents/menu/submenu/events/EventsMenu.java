package br.edu.ifba.inf0008.uniEvents.menu.submenu.events;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class EventsMenu extends Menu {
  private final List<String> options = new ArrayList<>();

  public EventsMenu() {
      super("Events Management", Colors.YELLOW_BOLD);
      options.add("Exit");
      options.add("Create Event");
      options.add("Remove Event");
      options.add("Update Event");
      options.add("List All Events");
      options.add("List Events by Type");
      options.add("Show Event Details");
  }

  @Override
  public void show() {
    int response;
    // Menu submenu;
    do { 
      response = super.menuResponse(options);

      switch (response) {
          case 0 -> {
              return;
          }
          case 1 -> {
            // submenu = new CreateEventMenu();
            // submenu.show();
            System.out.println("Create Event Menu");
          }
          case 2 -> {
            // submenu = new RemoveEventMenu();
            // submenu.show();
            System.out.println("Remove Event Menu");
          }
          case 3 -> {
            // submenu = new UpdateEventMenu();
            // submenu.show();
            System.out.println("Update Event Menu");
          }
          case 4 -> {
            // submenu = new ListAllEventsMenu();
            // submenu.show();
            System.out.println("List All Events Menu");
          }
          case 5 -> {
            // submenu = new ListEventsByTypeMenu();
            // submenu.show();
            System.out.println("List Events by Type Menu");
          }
          case 6 -> {
            // submenu = new ShowEventDetailsMenu();
            // submenu.show();
            System.out.println("Show Event Details Menu");
          }
          default -> throw new AssertionError();
      }
    } while (true);
  }
}
