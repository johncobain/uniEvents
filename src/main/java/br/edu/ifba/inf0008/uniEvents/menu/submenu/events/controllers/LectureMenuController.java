package br.edu.ifba.inf0008.uniEvents.menu.submenu.events.controllers;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf0008.uniEvents.model.events.Lecture;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class LectureMenuController {
  public static Boolean create(EventManager eventManager, String name, String description, String location, String date, int capacity, String modality, String code) {
    String maintTopic = EventForms.getText("Main topic");
    if(maintTopic.equalsIgnoreCase("cancel")) return false;

    String subTopicsStr = EventForms.getText("Sub topics[';' separated]");
    if(subTopicsStr.equalsIgnoreCase("cancel")) return false;
    List<String> subTopics = new ArrayList<>(List.of(subTopicsStr.split(";")));

    String objectivesStr = EventForms.getText("Objectives[';' separated]");
    if(objectivesStr.equalsIgnoreCase("cancel")) return false;
    List<String> objectives = new ArrayList<>(List.of(objectivesStr.split(";")));
    
    String language = EventForms.getText("Language");
    if(language.equalsIgnoreCase("cancel")) return false;

    int duration = EventForms.getNumber("Duration in minutes");
    if (duration == -1) return false;
    
    Lecture event = new Lecture(name, description, location, Utils.stringToDate(date), capacity, Modality.fromDescription(modality), code, maintTopic, subTopics, objectives, language, duration);
    eventManager.add(event);
    return true;
  }

  public static Boolean update(EventManager eventManager, String code, String name, String description, String location, String date, int capacity, String modality) {
    String maintTopic = EventForms.getText("Main topic");
    if(maintTopic.equalsIgnoreCase("cancel")) return false;

    String subTopicsStr = EventForms.getText("Sub topics[';' separated]");
    if(subTopicsStr.equalsIgnoreCase("cancel")) return false;
    List<String> subTopics = new ArrayList<>(List.of(subTopicsStr.split(";")));

    String objectivesStr = EventForms.getText("Objectives[';' separated]");
    if(objectivesStr.equalsIgnoreCase("cancel")) return false;
    List<String> objectives = new ArrayList<>(List.of(objectivesStr.split(";")));
    
    String language = EventForms.getText("Language");
    if(language.equalsIgnoreCase("cancel")) return false;

    int duration = EventForms.getNumber("Duration in minutes");
    if (duration == -1) return false;
    
    Lecture event = new Lecture(name, description, location, Utils.stringToDate(date), capacity, Modality.fromDescription(modality), code, maintTopic, subTopics, objectives, language, duration);
    eventManager.update(code, event);
    return true;
  }
}
