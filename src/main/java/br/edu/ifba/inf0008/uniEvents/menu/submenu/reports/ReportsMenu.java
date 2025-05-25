package br.edu.ifba.inf0008.uniEvents.menu.submenu.reports;
import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.FileGenerator;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.services.ReportsManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class ReportsMenu extends Menu {
  private final EventManager eventManager;
  private final ReportsManager reportsManager;

  public ReportsMenu(EventManager eventManager, ParticipantManager participantManager, ReportsManager reportsManager, FileGenerator fileGenerator) {
    super("Reports Management", Colors.RED_BOLD);
    super.addOption("Exit to Main Menu");
    super.addOption("Generate Report by Type");
    super.addOption("Generate Report by Date");
    super.addOption("Generate Report by Location");
    super.addOption("Generate Detailed Report");
    super.addOption("Generate Summary Report");    

    this.eventManager = eventManager;
    this.reportsManager = reportsManager;
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
          case 4 -> {
            String detailedReport = reportsManager.generateReport(eventManager.getAllEvents(), "Detailed Report", true);
            System.out.println(detailedReport);
          }
          case 5 -> {
            String summaryReport = reportsManager.generateReport(eventManager.getAllEvents(), "Summary Report", false);
            System.out.println(summaryReport);
          }
          default -> throw new AssertionError();
      }
    }
  }  
}
