package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants;

import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.controllers.ParticipantMenuController;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class ParticipantSubMenu extends Menu{
  private final ParticipantMenuController participantMenuController;
  private final String participantType;

  public ParticipantSubMenu(ParticipantMenuController participantMenuController, String participantType) {
    super(participantType + " Management", Colors.BLUE_BOLD);
    super.addOption("Exit to Participant Menu");
    super.addOption("Create " + participantType);
    super.addOption("Show " + participantType);
    super.addOption("List All " + participantType + "s");
    super.addOption("Clear All " + participantType + "s");

    this.participantType = participantType;
    this.participantMenuController = participantMenuController;
  }

  @Override
  public void show() {
    int response;
    while(true){ 
      response = super.menuResponse();

      switch (response) {
          case 0 -> {
            return;
          }
          case 1 -> {
            participantMenuController.create(participantType);
          }
          case 2 -> {
            participantMenuController.get(participantType);
          }
          case 3 -> {
            participantMenuController.list(participantType);
          }
          case 4 -> {
            participantMenuController.clear(participantType);
          }
          default -> throw new AssertionError();
      }
    }
  }
  
}
