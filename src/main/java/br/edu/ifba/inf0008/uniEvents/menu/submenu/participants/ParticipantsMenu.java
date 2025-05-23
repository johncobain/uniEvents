package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.menu.menuInterface.IMenu;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class ParticipantsMenu extends Menu {
  private final List<String> options = new ArrayList<>();

  public ParticipantsMenu() {
      super("Participants Management", Colors.BLUE_BOLD);
      options.add("Exit");
      options.add("Create Participant");
      options.add("Remove Participant");
      options.add("Update Participant");
      options.add("List All Participants");
      options.add("List Participants by Type");
      options.add("Show Participant Details");
  }

  @Override
  public void show() {
    int response;
    // IMenu submenu;
    do { 
      response = super.menuResponse(options);

      switch (response) {
          case 0 -> {
            return;
          }
          case 1 -> {
            // submenu = new CreateParticipantMenu();
            // submenu.show();
            System.out.println("Create Participant Menu");
          }
          case 2 -> {
            // submenu = new RemoveParticipantMenu();
            // submenu.show();
            System.out.println("Remove Participant Menu");
          }
          case 3 -> {
            // submenu = new UpdateParticipantMenu();
            // submenu.show();
            System.out.println("Update Participant Menu");
          }
          case 4 -> {
            // submenu = new ListAllParticipantsMenu();
            // submenu.show();
            System.out.println("List All Participants Menu");
          }
          case 5 -> {
            // submenu = new ListParticipantsByTypeMenu();
            // submenu.show();
            System.out.println("List Participants by Type Menu");
          }
          case 6 -> {
            // submenu = new ShowParticipantDetailsMenu();
            // submenu.show();
            System.out.println("Show Participant Details Menu");
          }
          default -> throw new AssertionError();
      }
    } while (true);
  }
}
