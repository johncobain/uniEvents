package br.edu.ifba.inf0008.uniEvents.menu.submenu.events.controllers;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf0008.uniEvents.forms.CommonForms;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.Lecture;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.services.managers.IManager;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class LectureMenuController {
  public static Lecture getForm(IManager<Event> eventManager, String name, String description, String location, String date, int capacity, String modality, double totalHours, String code) {
    String maintTopic = CommonForms.getText("Event's Main topic");
    if(maintTopic.equalsIgnoreCase("cancel")) return null;

    String subTopicsStr = CommonForms.getText("Event's Sub topics[';' separated]");
    if(subTopicsStr.equalsIgnoreCase("cancel")) return null;
    List<String> subTopics = new ArrayList<>(List.of(subTopicsStr.split(";")));

    String objectivesStr = CommonForms.getText("Event's Objectives[';' separated]");
    if(objectivesStr.equalsIgnoreCase("cancel")) return null;
    List<String> objectives = new ArrayList<>(List.of(objectivesStr.split(";")));
    
    String language = CommonForms.getText("Event's Language");
    if(language.equalsIgnoreCase("cancel")) return null;
    
    Lecture lecture = new Lecture(name, description, location, Utils.stringToDate(date), capacity, Modality.fromDescription(modality), totalHours, code, maintTopic, subTopics, objectives, language);
    return lecture;
  }
}
