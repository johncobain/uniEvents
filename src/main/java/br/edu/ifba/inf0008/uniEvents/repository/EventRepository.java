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
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.json.LocalDateAdapter;
import br.edu.ifba.inf0008.uniEvents.utils.json.gsonextras.RuntimeTypeAdapterFactory;

public class EventRepository {
  private static final String EVENTS_FILE = "data/events.json";
  private ParticipantRepository participantRepository;
  private List<Event> eventsSaved;
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
    this.eventsSaved = loadEvents();

    try {
        Files.createDirectories(Paths.get("data"));
    } catch (IOException e) {
      System.err.println(Lines.errorLine("Error creating data directory: " + e.getMessage()));
    }
  }

  private List<Event> loadEvents(){
    List<Event> events;
    try (FileReader reader = new FileReader(EVENTS_FILE)){
      Type eventListType = new TypeToken<ArrayList<Event>>(){}.getType();
      events = gson.fromJson(reader, eventListType);
      if (events == null) {
        events = new ArrayList<>();
      }
    } catch (IOException e) {
      System.err.println(Lines.warningLine("Events file not found, creating a new one: " + e.getMessage()));
      events = new ArrayList<>();
    }

    if(this.participantRepository != null){
      for (Event event : events){
        event.populateParticipants(this.participantRepository);
      }
    }
    return events;
  }

  public void saveEvents(){
    try (FileWriter writer = new FileWriter(EVENTS_FILE)){
      gson.toJson(eventsSaved, writer);
      System.out.println(Lines.leftText("Events saved in " + EVENTS_FILE));
    } catch (IOException e) {
      System.err.println(Lines.errorLine("Error saving events: " + e.getMessage()));
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

  public void clearAllEvents(){
    eventsSaved.clear();
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

  public void addParticipantToEvent(Event event, Participant participant){
    for (Event e: eventsSaved){
      if(e.getCode().equals(event.getCode())){
        e.addParticipant(participant);
        break;
      }
    }
    saveEvents();
  }

  public ArrayList<Event> getEvents(){
    return new ArrayList<>(eventsSaved);
  }

}
