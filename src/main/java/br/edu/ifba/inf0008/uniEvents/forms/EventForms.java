package br.edu.ifba.inf0008.uniEvents.forms;

import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;
import br.edu.ifba.inf0008.uniEvents.utils.Validation;

public class EventForms {
  public static String getText(String text){
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

  public static int getNumber(String text){
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

  public static double getDouble(String text){
    double number;
    while(true) { 
      System.out.print("Enter event " + text.toLowerCase() + " (\"cancel\" to exit)>> ");
      String numberStr = Utils.scanner.nextLine();
      if (numberStr.equals("cancel")) return -1;
      try {
        Validation.isDouble(numberStr);
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
}
