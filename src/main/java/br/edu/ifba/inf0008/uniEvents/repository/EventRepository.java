package br.edu.ifba.inf0008.uniEvents.repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.edu.ifba.inf0008.uniEvents.model.events.AcademicFair;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.Lecture;
import br.edu.ifba.inf0008.uniEvents.model.events.ShortCourse;
import br.edu.ifba.inf0008.uniEvents.model.events.Workshop;
import br.edu.ifba.inf0008.uniEvents.utils.json.LocalDateAdapter;
import br.edu.ifba.inf0008.uniEvents.utils.json.gsonextras.RuntimeTypeAdapterFactory;

public class EventRepository {
  private static final String EVENTS_FILE = "data/events.json";
  private List<Event> eventsSaved;
  private Gson gson;
  
  public EventRepository(){
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
    gsonBuilder.setPrettyPrinting();

    RuntimeTypeAdapterFactory<Event> eventAdapterFactory = RuntimeTypeAdapterFactory
    .of(Event.class, "eventTypeJson")
    .registerSubtype(Lecture.class, "Lecture")
    .registerSubtype(Workshop.class, "Workshop")
    .registerSubtype(ShortCourse.class, "ShortCourse")
    .registerSubtype(AcademicFair.class, "AcademicFair");

    gsonBuilder.registerTypeAdapterFactory(eventAdapterFactory);

    this.gson = gsonBuilder.create();
    this.eventsSaved = loadEvents();

    try {
        Files.createDirectories(Paths.get("data"));
    } catch (IOException e) {
      System.err.println("Error creating data directory: " + e.getMessage());
    }
  }

  private List<Event> loadEvents(){
    try (FileReader reader = new FileReader(EVENTS_FILE)){
      Type eventListType = new TypeToken<ArrayList<Event>>(){}.getType();
      List<Event> events = gson.fromJson(reader, eventListType);
      return events != null ? events: new ArrayList<>();
    } catch (IOException e) {
      System.err.println("Events file not found, creating a new one: " + e.getMessage());
      return new ArrayList<>();
    }
  }

  public void saveEvents(){
    try (FileWriter writer = new FileWriter(EVENTS_FILE)){
      gson.toJson(eventsSaved, writer);
      System.out.println("Events saved in " + EVENTS_FILE);
    } catch (IOException e) {
      System.err.println("Error saving events: " + e.getMessage());
    }
  }

  public void addEvent(Event event){
    eventsSaved.add(event);
    saveEvents();
  }
  public void removeEvent(Event event){
    eventsSaved.remove(event);
    saveEvents();
  }

  public void updateEvent(Event event, String code){
    for (int i = 0; i < eventsSaved.size(); i++){
      if (eventsSaved.get(i).getCode().equals(code)){
        eventsSaved.set(i, event);
        break;
      }
    }
    saveEvents();
  }

  public List<Event> getEvents(){
    return new ArrayList<>(eventsSaved);
  }

}
