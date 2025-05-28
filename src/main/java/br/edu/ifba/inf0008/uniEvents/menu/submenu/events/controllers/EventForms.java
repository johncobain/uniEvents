package br.edu.ifba.inf0008.uniEvents.menu.submenu.events.controllers;

import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.menu.submenu.BaseMenu;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;
import br.edu.ifba.inf0008.uniEvents.utils.Validation;

public class EventForms {
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

  protected static int getNumber(String text){
    int number;
    while(true) { 
      System.out.print("Enter event " + text.toLowerCase() + " (\"cancel\" to exit)>> ");
      String numberStr = Utils.scanner.nextLine();
      if (numberStr.equals("cancel")) return -1;
      try {
        Validation.isInteger(numberStr);
        number = Integer.parseInt(numberStr);
        if (number <= 0) {
          throw new Exception(text + " must be greater than 0!");
        }
        break;
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine(e.getMessage()));
      }
    }
    System.out.println(Lines.clear());
    return number;
  }

  protected static String getOption(ArrayList<String> options, String title){
    BaseMenu baseMenu;
    baseMenu = new BaseMenu("Select " + title, options);

    int response = baseMenu.getResponse();
    if (response == 0) return "cancel";
    return options.get(response);
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
    return code.toUpperCase();
  }

  protected static String getYN(String text, String defaultValue) {
  String response;
  while (true) {
    System.out.print(text);
    if(defaultValue.equalsIgnoreCase("y")) {
      System.out.print("[Y/n](\"cancel\" to exit)>> ");
    } else {
      System.out.print("[y/N](\"cancel\" to exit)>> ");
    }
    response = Utils.scanner.nextLine().trim().toLowerCase();
    if (response.equalsIgnoreCase("cancel")) {
      System.out.println(Lines.clear());
      return "cancel";
    }
    if (response.isEmpty()) {
      response = defaultValue.toLowerCase();
      break;
    } else if (!response.equals("y") && !response.equals("n")) {
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Invalid input! Please enter 'y' or 'n'."));
    }else{
      break;
    }
  }
    return response;
  }
}
