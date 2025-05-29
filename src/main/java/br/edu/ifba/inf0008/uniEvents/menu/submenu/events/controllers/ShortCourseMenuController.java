package br.edu.ifba.inf0008.uniEvents.menu.submenu.events.controllers;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.ShortCourse;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.SkillLevel;
import br.edu.ifba.inf0008.uniEvents.services.IManager;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class ShortCourseMenuController {
  public static ShortCourse getForm(IManager<Event> eventManager, String name, String description, String location, String date, int capacity, String modality, double totalHours, String code) {
    String courseModulesStr = EventForms.getText("Course modules[';' separated]");
    if(courseModulesStr.equalsIgnoreCase("cancel")) return null;
    List<String> courseModules = new ArrayList<>(List.of(courseModulesStr.split(";")));

    String methodOfAssessment = EventForms.getText("Method of assessment");
    if(methodOfAssessment.equalsIgnoreCase("cancel")) return null;

    ArrayList<String> options = new ArrayList<>();
    options.add("Cancel");
    for(SkillLevel skillLevel : SkillLevel.getAll())options.add(skillLevel.getDescription());

    String skillLevel = EventForms.getOption(options, "Target skill level");
    if(skillLevel.equalsIgnoreCase("cancel")) return null;    
    
    ShortCourse shortCourse = new ShortCourse(name, location, description, Utils.stringToDate(date), capacity, Modality.fromDescription(modality), totalHours, code, courseModules, methodOfAssessment, SkillLevel.fromDescription(skillLevel));
    return shortCourse;
  }
}
