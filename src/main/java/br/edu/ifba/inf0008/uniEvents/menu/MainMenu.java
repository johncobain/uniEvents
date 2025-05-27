package br.edu.ifba.inf0008.uniEvents.menu;

import br.edu.ifba.inf0008.uniEvents.menu.menuInterface.IMenu;
import br.edu.ifba.inf0008.uniEvents.menu.submenu.events.EventsMenu;
import br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.ParticipantMenu;
import br.edu.ifba.inf0008.uniEvents.menu.submenu.reports.ReportsMenu;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.FileGenerator;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.services.ReportsManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class MainMenu extends Menu{
  private final EventManager eventManager;
  private final ParticipantManager participantManager;
  private final ReportsManager reportsManager;
  private final FileGenerator fileGenerator;
  
  public MainMenu( EventManager eventManager, ParticipantManager participantManager, ReportsManager reportsManager, FileGenerator fileGenerator) {
    super( "Main Menu", Colors.PURPLE_BOLD);
    super.addOption("Exit UniEvents");
    super.addOption("Participants Management");
    super.addOption("Events Management");
    super.addOption("See Reports");
    super.addOption("Generate Dummy Data");

    this.eventManager = eventManager;
    this.participantManager = participantManager;
    this.reportsManager = reportsManager;
    this.fileGenerator = fileGenerator;
  }
  
  @Override
  public void show() {
    int response;
    IMenu submenu;
    while(true){
      response = super.menuResponse();

      switch (response) {
          case 0 -> {
              return;
          }
          case 1 -> {
            submenu = new ParticipantMenu(participantManager, eventManager);
            submenu.show();
          }
          case 2 -> {
            submenu = new EventsMenu(eventManager, participantManager);
            submenu.show();
          }
          case 3 -> {
            submenu = new ReportsMenu(eventManager, participantManager, reportsManager, fileGenerator);
            submenu.show();
          }
          case 4 -> {
            fileGenerator.generateDummyData(eventManager, participantManager);
          }
          default -> throw new AssertionError();
      }
    }
  }
}
