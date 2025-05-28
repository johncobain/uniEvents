package br.edu.ifba.inf0008.uniEvents.menu.submenu.events.controllers;

import br.edu.ifba.inf0008.uniEvents.model.events.AcademicFair;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class AcademicFairMenuController {
  public static Boolean create(EventManager eventManager, String name, String description, String location, String date, int capacity, String modality, String code) {
    int stands = EventForms.getNumber("Stands Number");
    if (stands == -1) return false;

    String centralTheme = EventForms.getText("Central Theme");
    if(centralTheme.equalsIgnoreCase("cancel")) return false;
    
    String finalDate = EventForms.getText("Final Date");
    if(finalDate.equalsIgnoreCase("cancel")) return false;
    
    AcademicFair event = new AcademicFair(name, description, location, Utils.stringToDate(date), capacity, Modality.fromDescription(modality), code, stands, centralTheme, Utils.stringToDate(finalDate));
    eventManager.add(event);
    return true;
  }

  public static Boolean update(EventManager eventManager, String code, String name, String description, String location, String date, int capacity, String modality) {
    int stands = EventForms.getNumber("Stands Number");
    if (stands == -1) return false;

    String centralTheme = EventForms.getText("Central Theme");
    if(centralTheme.equalsIgnoreCase("cancel")) return false;
    
    String finalDate = EventForms.getText("Final Date");
    if(finalDate.equalsIgnoreCase("cancel")) return false;
    
    AcademicFair event = new AcademicFair(name, description, location, Utils.stringToDate(date), capacity, Modality.fromDescription(modality), code, stands, centralTheme, Utils.stringToDate(finalDate));
    eventManager.update(code, event);
    return true;
  }
}
