package br.edu.ifba.inf0008.uniEvents.menu.submenu.events.controllers;

import br.edu.ifba.inf0008.uniEvents.model.events.ShortCourse;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class ShortCourseMenuController {
  public static Boolean create(EventManager eventManager, String name, String description, String location, String date, int capacity, String modality, String code) {
    
    eventManager.createShortCourse(name, description, location, Utils.stringToDate(date), capacity, Modality.fromDescription(modality), code);
    return true;
  }

  public static ShortCourse update(EventManager eventManager, String code, String name, String description, String location, String date, int capacity, String modality) {
    
    return new ShortCourse(name, description, location, Utils.stringToDate(date), capacity, Modality.fromDescription(modality), code);
  }
}
