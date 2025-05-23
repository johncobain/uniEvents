package br.edu.ifba.inf0008.uniEvents.menu;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf0008.uniEvents.menu.menuInterface.IMenu;
import br.edu.ifba.inf0008.uniEvents.menu.submenu.events.EventsMenu;
import br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.ParticipantsMenu;
import br.edu.ifba.inf0008.uniEvents.menu.submenu.reports.ReportsMenu;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.FileGenerator;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.services.ReportsManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class MainMenu extends Menu{
  private final List<String> options = new ArrayList<>();
  
  public MainMenu( EventManager eventManager, ParticipantManager participantManager, ReportsManager reportsManager, FileGenerator fileGenerator) {
    super( "Main Menu", Colors.PURPLE_BOLD);
    options.add("Exit");
    options.add("Participants Management");
    options.add("Events Management");
    options.add("See Reports");
  }
  
  @Override
  public void show() {
    int response;
    IMenu submenu;
    do { 
      response = super.menuResponse(options);

      switch (response) {
          case 0 -> {
              return;
          }
          case 1 -> {
            submenu = new ParticipantsMenu();
            submenu.show();
          }
          case 2 -> {
            submenu = new EventsMenu();
            submenu.show();
          }
          case 3 -> {
            submenu = new ReportsMenu();
            submenu.show();
          }
          default -> throw new AssertionError();
      }
    } while (true);
  }
}
