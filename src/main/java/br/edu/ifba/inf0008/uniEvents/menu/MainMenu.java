package br.edu.ifba.inf0008.uniEvents.menu;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class MainMenu extends Menu{
  private final List<String> options = new ArrayList<>();
  
  public MainMenu(){
    super("Main Menu", Colors.PURPLE_BOLD);
    options.add("Exit");
    options.add("Events Management");
    options.add("Participants Management");
    options.add("See Reports");
  }
  
  @Override
  public void show() {
    int response;
    do { 
        
      response = super.menuResponse(options);
      if(response == 0){
        System.out.println("Goodbye!");
        break;
      }
    } while (true);

    System.out.println("Response: " + options.get(response));
  }
}
