package br.edu.ifba.inf0008.uniEvents.forms;

import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class EventForms {
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
