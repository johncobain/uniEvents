package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants;

import br.edu.ifba.inf0008.uniEvents.menu.Menu;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;

public class TeacherMenu extends Menu{
  private final ParticipantMenuController participantMenuController = new ParticipantMenuController();

  public TeacherMenu(ParticipantManager participantManager, EventManager eventManager) {
    super("Teacher Management", Colors.BLUE_BOLD);
    super.addOption("Exit to Participant Menu");
    super.addOption("Create Teacher");
    super.addOption("Remove Teacher");
    super.addOption("Update Teacher");
    super.addOption("List All Teachers");
    super.addOption("Show Teacher");
    super.addOption("Show Teacher Events");
    super.addOption("Clear All Teachers");
    super.addOption("Add Teacher to Event");
    super.addOption("Remove Teacher from Event");
    
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
            participantMenuController.create("Teacher");
          }
          case 2 -> {
            participantMenuController.remove();
          }
          case 3 -> {
            participantMenuController.update("Teacher");
          }
          case 4 -> {
            participantMenuController.listType("Teacher");
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
          case 8 -> {
            participantMenuController.addToEvent();
          }
          case 9 -> {
            participantMenuController.removeFromEvent();
          }
          default -> throw new AssertionError();
      }
    }
  }
  
}
