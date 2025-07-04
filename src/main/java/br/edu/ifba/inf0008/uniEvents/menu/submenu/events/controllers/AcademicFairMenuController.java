package br.edu.ifba.inf0008.uniEvents.menu.submenu.events.controllers;

import br.edu.ifba.inf0008.uniEvents.forms.CommonForms;
import br.edu.ifba.inf0008.uniEvents.model.events.AcademicFair;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.services.managers.IManager;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class AcademicFairMenuController {
  public static AcademicFair getForm(IManager<Event> eventManager, String name, String description, String location, String date, int capacity, String modality, double totalHours, String code) {
    int stands = CommonForms.getNumber("Stands Number");
    if (stands == -1) return null;

    String centralTheme = CommonForms.getText("Event Central Theme");
    if(centralTheme.equalsIgnoreCase("cancel")) return null;
    
    String finalDate = CommonForms.getText("Event Final Date");
    if(finalDate.equalsIgnoreCase("cancel")) return null;
    
    AcademicFair academicFair = new AcademicFair(name, description, location, Utils.stringToDate(date), capacity, Modality.fromDescription(modality), totalHours, code, stands, centralTheme, Utils.stringToDate(finalDate));
    return academicFair;
  }
}
