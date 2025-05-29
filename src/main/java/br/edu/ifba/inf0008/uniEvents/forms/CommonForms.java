package br.edu.ifba.inf0008.uniEvents.forms;

import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.exceptions.InvalidInputException;
import br.edu.ifba.inf0008.uniEvents.menu.submenu.BaseMenu;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;
import br.edu.ifba.inf0008.uniEvents.utils.Validation;

public class CommonForms {
  public static String getYN(String text, String defaultValue) {
  String response;
  while (true) {
    System.out.print(text);
    if(defaultValue.equalsIgnoreCase("y")) {
      System.out.print("[Y/n]>> ");
    } else {
      System.out.print("[y/N]>> ");
    }
    response = Utils.scanner.nextLine().trim().toLowerCase();
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

  public static String getOption(ArrayList<String> options, String title){
    BaseMenu baseMenu;
    baseMenu = new BaseMenu(title, options);

    int response = baseMenu.getResponse();
    if (response == 0) return "cancel";
    return options.get(response);
  }

  public static String getText(String text){
    String response;
    while(true) {
      System.out.print("Enter " + text.toLowerCase() + "  (\"cancel\" to exit)>> ");
      response = Utils.scanner.nextLine();
      if (!response.isEmpty() || !response.isBlank()) break;
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine(text + " cannot be empty!"));
    }
    System.out.println(Lines.clear());
    return response;
  }

   public static String getDate(String type) {
    String dateString;
    while(true){
      System.out.print("Enter " + type.toLowerCase() + " (dd/MM/yyyy or ddMMyyyy) (\"cancel\" to exit)>> ");
      dateString = Utils.scanner.nextLine();
      if(dateString.equalsIgnoreCase("cancel")){
        System.out.println(Lines.clear());
        return "cancel";
      }
      try {
        Validation.validateDate(dateString);
        break;
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine(e.getMessage()));
      }
    }

    System.out.println(Lines.clear());
    return dateString;
  }

  public static String getYear(String type) {
    String year;
    while (true) { 
      System.out.print("Enter " + type.toLowerCase() + " (\"cancel\" to exit)>> ");
      year = Utils.scanner.nextLine();
      if(year.equalsIgnoreCase("cancel")) {
        System.out.println(Lines.clear());
        return "cancel";
      }
      try {
        Validation.isInteger(year);
        if (year.length() != 4) {
          throw new Exception("Year must be 4 digits long!");
        }
        break;
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine(e.getMessage()));
      }
    }
    System.out.println(Lines.clear());
    return year;
  }

  public static int getNumber(String text){
    int number;
    while(true) { 
      System.out.print("Enter " + text.toLowerCase() + " (\"cancel\" to exit)>> ");
      String numberStr = Utils.scanner.nextLine();
      if (numberStr.equals("cancel")) return -1;
      try {
        Validation.isInteger(numberStr);
        number = Integer.parseInt(numberStr);
        if (number <= 0) {
          throw new InvalidInputException(text + " must be greater than 0!");
        }
        break;
      } catch (InvalidInputException e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine(e.getMessage()));
      }
    }
    System.out.println(Lines.clear());
    return number;
  }

  public static double getDouble(String text){
    double number;
    while(true) { 
      System.out.print("Enter " + text.toLowerCase() + " (\"cancel\" to exit)>> ");
      String numberStr = Utils.scanner.nextLine();
      if (numberStr.equals("cancel")) return -1;
      try {
        Validation.isDouble(numberStr);
        number = Integer.parseInt(numberStr);
        if (number <= 0) {
          throw new InvalidInputException(text + " must be greater than 0!");
        }
        break;
      } catch (InvalidInputException e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine(e.getMessage()));
      }
    }
    System.out.println(Lines.clear());
    return number;
  }
}
