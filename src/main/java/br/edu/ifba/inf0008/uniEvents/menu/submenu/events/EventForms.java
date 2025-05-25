package br.edu.ifba.inf0008.uniEvents.menu.submenu.events;

import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.menu.submenu.BaseMenu;
import br.edu.ifba.inf0008.uniEvents.model.events.Modality;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;
import br.edu.ifba.inf0008.uniEvents.utils.Validation;

public class EventForms {
  protected static String getType(){
    BaseMenu baseMenu;
    ArrayList<String> eventTypes = new ArrayList<>();
    {
      eventTypes.add("Cancel");
      eventTypes.add("Lecture");
      eventTypes.add("Workshop");
      eventTypes.add("Short Course");
      eventTypes.add("Academic Fair");
    }
    baseMenu = new BaseMenu("Select event type", eventTypes);
    int response = baseMenu.getResponse();
    if (response == 0) return "cancel";
    return eventTypes.get(response);
  }

  protected static String getText(String text){
    String response;
    while(true) {
      System.out.print("Enter event " + text.toLowerCase() + "  (\"cancel\" to exit)>> ");
      response = Utils.scanner.nextLine();
      if (!response.isEmpty() || !response.isBlank()) break;
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("" + text + " cannot be empty!"));
    }
    System.out.println(Lines.clear());
    return response;
  }

  protected static String getDate(){
    String date;
    while(true){
      System.out.print("Enter event date (dd/MM/yyyy or ddMMyyyy) (\"cancel\" to exit)>> ");
      date = Utils.scanner.nextLine();
      if (date.equalsIgnoreCase("cancel")) {
        System.out.println(Lines.clear());
        return "cancel";
      }
      try {
        Validation.validateDate(date);
        break;
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine(e.getMessage()));
      }
    }

    System.out.println(Lines.clear());
    return date;
  }

  protected static int getCapacity(){
    int capacity;
    while(true) { 
      System.out.print("Enter event capacity (\"cancel\" to exit)>> ");
      String capacityStr = Utils.scanner.nextLine();
      if (capacityStr.equals("cancel")) return -1;
      try {
        capacity = Validation.isInteger(capacityStr);
        if (capacity <= 0) {
          throw new Exception("Capacity must be greater than 0!");
        }
        break;
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine(e.getMessage()));
      }
    }
    System.out.println(Lines.clear());
    return capacity;
  }

  protected static int getModality(){
    BaseMenu baseMenu;
    ArrayList<String> modalities = new ArrayList<>();
    modalities.add("Cancel");
    modalities.add(Modality.INPERSON.toString());
    modalities.add(Modality.ONLINE.toString());
    modalities.add(Modality.HYBRID.toString());

    baseMenu = new BaseMenu("Select event modality", modalities);
    int response = baseMenu.getResponse();
    return response;
  }

  public static String getCode(){
    String code;
    while (true){
      System.out.print("Enter event code (\"cancel\" to exit)>> ");
      code = Utils.scanner.nextLine();
      if (code.isEmpty() || code.isBlank()) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine("Code cannot be empty!"));
        continue;
      }
      break;
    }
    System.out.println(Lines.clear());
    return code;
  }
}
