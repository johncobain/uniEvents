package br.edu.ifba.inf0008.uniEvents.menu.submenu.events.controllers;

import br.edu.ifba.inf0008.uniEvents.model.events.Workshop;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class WorkshopMenuController {
  public static Boolean create(EventManager eventManager, String name, String description, String location, String date, int capacity, String modality, String code) {
    int totalHours = EventForms.getNumber("Total hours");
    if (totalHours == -1) return false;

    String activitiesSummary = EventForms.getText("Activities summary");
    if(activitiesSummary.equalsIgnoreCase("cancel")) return false;

    Workshop event = new Workshop(name, description, location, Utils.stringToDate(date), capacity, Modality.fromDescription(modality), code, totalHours, activitiesSummary);
    eventManager.add(event);
    return true;
  }

  public static Boolean update(EventManager eventManager, String code, String name, String description, String location, String date, int capacity, String modality) {
    int totalHours = EventForms.getNumber("Total hours");
    if (totalHours == -1) return false;

    String activitiesSummary = EventForms.getText("Activities summary");
    if(activitiesSummary.equalsIgnoreCase("cancel")) return false;
    
    Workshop event = new Workshop(name, description, location, Utils.stringToDate(date), capacity, Modality.fromDescription(modality), code, totalHours, activitiesSummary);
    eventManager.update(code, event);
    return true;
  }
}
