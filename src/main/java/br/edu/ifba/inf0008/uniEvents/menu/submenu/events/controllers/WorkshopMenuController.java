package br.edu.ifba.inf0008.uniEvents.menu.submenu.events.controllers;

import br.edu.ifba.inf0008.uniEvents.forms.CommonForms;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.Workshop;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.services.managers.IManager;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class WorkshopMenuController {
  public static Workshop getForm(IManager<Event> eventManager, String name, String description, String location, String date, int capacity, String modality, double totalHours, String code) {
    String activitiesSummary = CommonForms.getText("Event's Activities summary");
    if(activitiesSummary.equalsIgnoreCase("cancel")) return null;

    Workshop workshop = new Workshop(name, description, location, Utils.stringToDate(date), capacity, Modality.fromDescription(modality), totalHours, code, activitiesSummary);
    return workshop;
  }
}
