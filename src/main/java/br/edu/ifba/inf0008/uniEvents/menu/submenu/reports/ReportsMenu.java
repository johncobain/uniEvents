package br.edu.ifba.inf0008.uniEvents.menu.submenu.reports;
import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.menu.submenu.reports.controllers.ReportsMenuController;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.services.IManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class ReportsMenu extends Menu {
  private final IManager<Event> eventManager;

  public ReportsMenu(IManager<Event> eventManager) {
    super("Reports Management", Colors.RED_BOLD);
    super.addOption("Exit to Main Menu");
    super.addOption("Generate Full Report");
    super.addOption("Generate Report by Type");
    super.addOption("Generate Report by Date");

    this.eventManager = eventManager;
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
            ReportsMenuController.fullReport(eventManager);
          }
          case 2 -> {
            ReportsMenuController.reportByType(eventManager);
          }
          case 3 -> {
            ReportsMenuController.reportByDate(eventManager);
          }
          default -> throw new AssertionError();
      }
    }
  }
}
