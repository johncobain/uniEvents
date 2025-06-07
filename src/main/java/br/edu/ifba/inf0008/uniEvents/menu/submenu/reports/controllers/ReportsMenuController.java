package br.edu.ifba.inf0008.uniEvents.menu.submenu.reports.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import br.edu.ifba.inf0008.uniEvents.forms.CommonForms;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.services.generators.FileGenerator;
import br.edu.ifba.inf0008.uniEvents.services.generators.ReportsGenerator;
import br.edu.ifba.inf0008.uniEvents.services.managers.IManager;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class ReportsMenuController {
  public static void fullReport(IManager<Event> eventManager){
    List<Event> events = eventManager.getAll().values().stream().toList();
    
    String detailed = CommonForms.getYN("Do you want a detailed report?", "n");
    String report = ReportsGenerator.generateReport(events, "Full Report", detailed.equalsIgnoreCase("y"));
    System.out.println(report);
    if(CommonForms.getYN("Do you want to save this report to a file?", "n").equalsIgnoreCase("y")){
      FileGenerator fileGenerator = new FileGenerator();
      fileGenerator.saveTextToFile(report, "full_report");
    }
  }
  public static void reportByType(IManager<Event> eventManager){
    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    options.add("Academic Fair");
    options.add("Lecture");
    options.add("Short Course");
    options.add("Workshop");
    String type = CommonForms.getOption(options, "Select the Type of Event to Report");
    if(type.equalsIgnoreCase("Cancel"))return;

    List<Event> events = eventManager.getAll().values().stream()
      .filter(event -> event.getType().equalsIgnoreCase(type))
      .toList();

    String detailed = CommonForms.getYN("Do you want a detailed report?", "n");
    String report = ReportsGenerator.generateReport(events, "Report by Type: " + type, detailed.equalsIgnoreCase("y"));
    System.out.println(report);

    if(CommonForms.getYN("Do you want to save this report to a file?", "n").equalsIgnoreCase("y")){
      FileGenerator fileGenerator = new FileGenerator();
      fileGenerator.saveTextToFile(report, "report_by_type_" + type);
    }
  }
  public static void reportByDate(IManager<Event> eventManager){
    String date = CommonForms.getDate("Enter the date for the report");
    if(date.equalsIgnoreCase("cancel")) return;

    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    options.add("Before");
    options.add("After");
    options.add("On");
    String dateType = CommonForms.getOption(options, "Select the type of date filter");
    if(dateType.equalsIgnoreCase("Cancel")) return;
    List<Event> events = eventManager.getAll().values().stream()
      .filter(event -> {
        return switch (dateType) {
            case "Before" -> event.getDate().isBefore(Utils.stringToDate(date));
            case "After" -> event.getDate().isAfter(Utils.stringToDate(date));
            case "On" -> event.getDate().equals(Utils.stringToDate(date));
            default -> false;
        };
      })
      .sorted(Comparator.comparing(Event::getDate))
      .toList();

    String detailed = CommonForms.getYN("Do you want a detailed report?", "n");
    String report = ReportsGenerator.generateReport(events, "Report by Date: " + Utils.dateToString(Utils.stringToDate(date)), detailed.equalsIgnoreCase("y"));
    System.out.println(report);
    if(CommonForms.getYN("Do you want to save this report to a file?", "n").equalsIgnoreCase("y")){
      FileGenerator fileGenerator = new FileGenerator();
      fileGenerator.saveTextToFile(report, "report_by_date_" + Utils.dateToString(Utils.stringToDate(date)).replace("/", "_"));
    }
  }
}
