package br.edu.ifba.inf0008.uniEvents.menu.submenu.events.controllers;

import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.Workshop;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.services.IManager;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class WorkshopMenuController {
  public static Workshop getForm(IManager<Event> eventManager, String name, String description, String location, String date, int capacity, String modality, String code) {
    int totalHours = EventForms.getNumber("Total hours");
    if (totalHours == -1) return null;

    String activitiesSummary = EventForms.getText("Activities summary");
    if(activitiesSummary.equalsIgnoreCase("cancel")) return null;

    Workshop workshop = new Workshop(name, description, location, Utils.stringToDate(date), capacity, Modality.fromDescription(modality), code, totalHours, activitiesSummary);
    return workshop;
  }
}
