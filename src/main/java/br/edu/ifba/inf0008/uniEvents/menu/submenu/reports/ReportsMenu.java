package br.edu.ifba.inf0008.uniEvents.menu.submenu.reports;
import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.FileGenerator;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.services.ReportsManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class ReportsMenu extends Menu {

  public ReportsMenu(EventManager eventManager, ParticipantManager participantManager, ReportsManager reportsManager, FileGenerator fileGenerator) {
    super("Reports Management", Colors.RED_BOLD);
    super.addOption("Exit to Main Menu");
    super.addOption("Generate Report by Type");
    super.addOption("Generate Report by Date");
    super.addOption("Generate Report by Location");    
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
            // submenu = new GenerateReportByTypeMenu();
            // submenu.show();
            System.out.println("Generate Report by Type Menu");
          }
          case 2 -> {
            // submenu = new GenerateReportByDateMenu();
            // submenu.show();
            System.out.println("Generate Report by Date Menu");
          }
          case 3 -> {
            // submenu = new GenerateReportByLocationMenu();
            // submenu.show();
            System.out.println("Generate Report by Location Menu");
          }
          default -> throw new AssertionError();
      }
    } while (true);
  }  
}
