package br.edu.ifba.inf0008.uniEvents.menu.submenu.events.controllers;

import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.model.events.ShortCourse;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.SkillLevel;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class ShortCourseMenuController {
  public static Boolean create(EventManager eventManager, ParticipantManager participantManager, String name, String description, String location, String date, int capacity, String modality, String code) {
    int totalHours = EventForms.getNumber("Total hours");
    if (totalHours == -1) return false;

    String courseModules = EventForms.getText("Course modules[';' separated]");
    if(courseModules.equalsIgnoreCase("cancel")) return false;

    String methodOfAssessment = EventForms.getText("Method of assessment");
    if(methodOfAssessment.equalsIgnoreCase("cancel")) return false;

    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    for(SkillLevel skillLevel : SkillLevel.getAll())options.add(skillLevel.getDescription());

    String skillLevel = EventForms.getOption(options, "Target skill level");
    if(skillLevel.equalsIgnoreCase("cancel")) return false;    
    
    eventManager.createShortCourse(name, description, location, Utils.stringToDate(date), capacity, Modality.fromDescription(modality), code, totalHours, courseModules, methodOfAssessment, SkillLevel.fromDescription(skillLevel));
    return true;
  }

  public static ShortCourse update(EventManager eventManager, ParticipantManager participantManager, String code, String name, String description, String location, String date, int capacity, String modality) {
    int totalHours = EventForms.getNumber("Total hours");
    if (totalHours == -1) return null;

    String courseModules = EventForms.getText("Course modules[';' separated]");
    if(courseModules.equalsIgnoreCase("cancel")) return null;

    String methodOfAssessment = EventForms.getText("Method of assessment");
    if(methodOfAssessment.equalsIgnoreCase("cancel")) return null;

    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    for(SkillLevel skillLevel : SkillLevel.getAll())options.add(skillLevel.getDescription());

    String skillLevel = EventForms.getOption(options, "Target skill level");
    if(skillLevel.equalsIgnoreCase("cancel")) return null;
    
    return new ShortCourse(name, description, location, Utils.stringToDate(date), capacity, Modality.fromDescription(modality), code, totalHours, courseModules, methodOfAssessment, SkillLevel.fromDescription(skillLevel));
  }
}
