package br.edu.ifba.inf0008.uniEvents.repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.edu.ifba.inf0008.uniEvents.model.events.AcademicFair;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.Lecture;
import br.edu.ifba.inf0008.uniEvents.model.events.ShortCourse;
import br.edu.ifba.inf0008.uniEvents.model.events.Workshop;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.json.LocalDateAdapter;
import br.edu.ifba.inf0008.uniEvents.utils.json.gsonextras.RuntimeTypeAdapterFactory;

public class EventRepository {
  private static final String EVENTS_FILE = "data/events.json";
  private ParticipantRepository participantRepository;
  private LinkedHashMap<String, Event> eventsSaved;
  private Gson gson;
  
  public EventRepository(ParticipantRepository participantRepository) {
    this.participantRepository = participantRepository;
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
    gsonBuilder.setPrettyPrinting();

    RuntimeTypeAdapterFactory<Event> eventAdapterFactory = RuntimeTypeAdapterFactory
    .of(Event.class, "eventTypeJson")
    .registerSubtype(Lecture.class, "Lecture")
    .registerSubtype(Workshop.class, "Workshop")
    .registerSubtype(ShortCourse.class, "Short Course")
    .registerSubtype(AcademicFair.class, "Academic Fair");

    gsonBuilder.registerTypeAdapterFactory(eventAdapterFactory);

    this.gson = gsonBuilder.create();
    this.eventsSaved = load();

    try {
        Files.createDirectories(Paths.get("data"));
    } catch (IOException e) {
      System.err.println(Lines.errorLine("Error creating data directory: " + e.getMessage()));
    }
  }

  private LinkedHashMap<String, Event> load(){
    LinkedHashMap<String, Event> loadedEvents;
    try (FileReader reader = new FileReader(EVENTS_FILE)){
      Type eventListType = new TypeToken<ArrayList<Event>>(){}.getType();
      List<Event> events = gson.fromJson(reader, eventListType);
      if(events != null){
        loadedEvents = new LinkedHashMap<>();
        for (Event event : events) {
          loadedEvents.put(event.getCode(), event);
        }
      } else {
        loadedEvents = new LinkedHashMap<>();
      }
    } catch (IOException e) {
      System.err.println(Lines.warningLine("Events file not found, creating a new one"));
      return new LinkedHashMap<>();
    }

    if(this.participantRepository != null){
      for (Event event : loadedEvents.values()){
        event.populateParticipants(this.participantRepository);
      }
    }
    return loadedEvents;
  }

  public void save(){
    List<Event> eventsList = new ArrayList<>(eventsSaved.values());
    try (FileWriter writer = new FileWriter(EVENTS_FILE)){
      gson.toJson(eventsList, writer);
      System.out.println("Events saved in " + EVENTS_FILE);
    } catch (IOException e) {
      System.err.println(Lines.errorLine("Error saving events: " + e.getMessage()));
    }
  }

  public void add(Event event){
    eventsSaved.put(event.getCode(), event);
    save();
  }
  public void remove(String code){
    eventsSaved.remove(code);
    save();
  }

  public void clear(){
    eventsSaved.clear();
    save();
  }

  public void update(Event event, String code){
    if(eventsSaved.get(code) == null){
      System.out.println(Lines.errorLine("Event with code " + code + " not found!"));
    }
    eventsSaved.put(code, event);
    save();
  }

  public Event get(String code){
    return eventsSaved.get(code);
  }

  public LinkedHashMap<String, Event> getAll(){
    if(eventsSaved == null){
      return new LinkedHashMap<>();
    }
    return eventsSaved;
  }

}
