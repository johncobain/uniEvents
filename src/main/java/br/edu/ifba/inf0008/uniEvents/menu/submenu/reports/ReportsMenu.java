package br.edu.ifba.inf0008.uniEvents.menu.submenu.reports;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class ReportsMenu extends Menu {
  private final List<String> options = new ArrayList<>();

  public ReportsMenu() {
    super("Reports Management", Colors.RED_BOLD);
    options.add("Exit");
    options.add("Generate Report by Type");
    options.add("Generate Report by Date");
    options.add("Generate Report by Location");    
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
