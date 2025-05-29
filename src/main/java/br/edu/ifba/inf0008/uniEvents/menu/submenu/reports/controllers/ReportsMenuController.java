package br.edu.ifba.inf0008.uniEvents.menu.submenu.reports.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import br.edu.ifba.inf0008.uniEvents.forms.CommonForms;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.services.IManager;
import br.edu.ifba.inf0008.uniEvents.services.ReportsGenerator;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;
import br.edu.ifba.inf0008.uniEvents.utils.json.LocalDateAdapter;

public class ReportsMenuController {
  public static void fullReport(IManager<Event> eventManager){
    List<Event> events = eventManager.getAll().values().stream().toList();
    
    String detailed = CommonForms.getYN("Do you want a detailed report?", "n");
    System.out.println(ReportsGenerator.generateReport(events, "Full Report", detailed.equalsIgnoreCase("y")));
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
    System.out.println(ReportsGenerator.generateReport(events, "Report by Type: " + type, detailed.equalsIgnoreCase("y")));
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
    System.out.println(ReportsGenerator.generateReport(events, "Report by Date: " + Utils.stringToDate(date).format(LocalDateAdapter.DATE_FORMATTER), detailed.equalsIgnoreCase("y")));
  }
  
}
