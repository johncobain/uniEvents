package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants;
import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class ParticipantMenu extends Menu {
  private final ParticipantMenuController participantMenuController = new ParticipantMenuController();
  ParticipantManager participantManager;
  EventManager eventManager;

  public ParticipantMenu(ParticipantManager participantManager, EventManager eventManager) {
      super("Participants Management", Colors.BLUE_BOLD);
      super.addOption("Exit to Main Menu");
      super.addOption("Student Management");
      super.addOption("Teacher Management"); // TODO: implement
      super.addOption("External Management"); // TODO: implement
      super.addOption("List All Participants");
      super.addOption("Show Participant");
      super.addOption("Show Participant Events");
      super.addOption("Clear All Participants");


      this.participantManager = participantManager;
      this.eventManager = eventManager;

      participantMenuController.setParticipantManager(participantManager);
      participantMenuController.setEventManager(eventManager);
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
            new StudentMenu(participantManager, eventManager).show();
          }
          case 2 -> {
            new TeacherMenu(participantManager, eventManager).show();
          }
          case 3 -> {
            new ExternalMenu(participantManager, eventManager).show();
          }
          case 4 -> {
            participantMenuController.listAll();
          }
          case 5 -> {
            participantMenuController.get();
          }
          case 6 -> {
            participantMenuController.showEvents();
          }
          case 7 -> {
            participantMenuController.clearAll();
          }
          default -> throw new AssertionError();
      }
    }
  }
}
