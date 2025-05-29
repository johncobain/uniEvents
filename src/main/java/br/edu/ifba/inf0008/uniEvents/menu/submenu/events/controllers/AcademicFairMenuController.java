package br.edu.ifba.inf0008.uniEvents.menu.submenu.events.controllers;

import br.edu.ifba.inf0008.uniEvents.model.events.AcademicFair;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.services.IManager;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class AcademicFairMenuController {
  public static AcademicFair getForm(IManager<Event> eventManager, String name, String description, String location, String date, int capacity, String modality, String code) {
    int stands = EventForms.getNumber("Stands Number");
    if (stands == -1) return null;

    String centralTheme = EventForms.getText("Central Theme");
    if(centralTheme.equalsIgnoreCase("cancel")) return null;
    
    String finalDate = EventForms.getText("Final Date");
    if(finalDate.equalsIgnoreCase("cancel")) return null;
    
    AcademicFair academicFair = new AcademicFair(name, description, location, Utils.stringToDate(date), capacity, Modality.fromDescription(modality), code, stands, centralTheme, Utils.stringToDate(finalDate));
    return academicFair;
  }
}
