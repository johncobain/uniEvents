package br.edu.ifba.inf0008.uniEvents.menu;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf0008.uniEvents.menu.menuInterface.IMenu;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;
import br.edu.ifba.inf0008.uniEvents.utils.Validation;

public abstract class Menu implements IMenu {
  protected String title;
  protected String color;

  private final List<String> options = new ArrayList<>();

  public Menu(String title){
    this.title = title;
  }

  public Menu(String title, String color){
    this.title = title;
    this.color = color;
  }

  public void addOption(String option){
    options.add(option);
  }

  protected int menuResponse(){
    while(true) { 
      System.out.println(Lines.doubleLine());
      if(color != null) System.out.println(Lines.titleLine(title, color));
      else System.out.println(Lines.titleLine(title));
      System.out.println(Lines.doubleLine());

      for(int i = 0; i < options.size(); i++){
        System.out.println(Lines.leftText("" + i + " - " + options.get(i)));
      }
      System.out.println(Lines.doubleLine());
      System.out.print(">> ");
      String choice = Utils.scanner.nextLine();
      int response;
      try {
        Validation.isInteger(choice);
        response = processOption(choice);
        if(response < 0 || response >= options.size()){
          throw new Exception("Invalid option! Please try again.");
        }
        System.out.println(Lines.clear());
        return response;
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine(e.getMessage()));
      }
    }
  }

  @Override
  public int processOption(String option){
    if(option == null || option.isEmpty()){
      return -1;
    }
    try {
        int optionInt = Integer.parseInt(option);
        return optionInt;
    } catch (NumberFormatException e) {
        return -1;
    }
  }
}
