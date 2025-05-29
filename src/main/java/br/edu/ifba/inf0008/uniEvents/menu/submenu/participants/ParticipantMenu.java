package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants;
import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.controllers.ParticipantMenuController;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.services.IManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class ParticipantMenu extends Menu {
  private final ParticipantMenuController participantMenuController;

  public ParticipantMenu(IManager<Participant> participantManager, IManager<Event> eventManager) {
      super("Participants Management", Colors.BLUE_BOLD);
      super.addOption("Exit to Main Menu");
      super.addOption("Student Management");
      super.addOption("Professor Management");
      super.addOption("External Management");
      super.addOption("Show Participant");
      super.addOption("List All Participants");
      super.addOption("Clear All Participants");

      this.participantMenuController = new ParticipantMenuController(participantManager, eventManager);
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
            new ParticipantSubMenu(participantMenuController, "Student").show();
          }
          case 2 -> {
            new ParticipantSubMenu(participantMenuController, "Professor").show();
          }
          case 3 -> {
            new ParticipantSubMenu(participantMenuController, "External").show();
          }
          case 4 -> {
              participantMenuController.get("Participant");
          }
          case 5 -> {
              participantMenuController.list();
          }
          case 6 -> {
              participantMenuController.clear();
          }
      }
    }
  }
}
