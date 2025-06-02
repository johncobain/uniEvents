package br.edu.ifba.inf0008.uniEvents.utils.json;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.google.gson.GsonBuilder;

import br.edu.ifba.inf0008.uniEvents.model.events.AcademicFair;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.Lecture;
import br.edu.ifba.inf0008.uniEvents.model.events.ShortCourse;
import br.edu.ifba.inf0008.uniEvents.model.events.Workshop;
import br.edu.ifba.inf0008.uniEvents.model.participants.External;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.model.participants.Professor;
import br.edu.ifba.inf0008.uniEvents.model.participants.Student;
import br.edu.ifba.inf0008.uniEvents.utils.json.gsonextras.RuntimeTypeAdapterFactory;

public class JsonFactory {
  private final GsonBuilder gsonBuilder;

  public JsonFactory(){
    gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
    gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
    gsonBuilder.setPrettyPrinting();

    RuntimeTypeAdapterFactory<Event> eventAdapterFactory = RuntimeTypeAdapterFactory
    .of(Event.class, "eventTypeJson")
    .registerSubtype(Lecture.class, "Lecture")
    .registerSubtype(Workshop.class, "Workshop")
    .registerSubtype(ShortCourse.class, "Short Course")
    .registerSubtype(AcademicFair.class, "Academic Fair");

    gsonBuilder.registerTypeAdapterFactory(eventAdapterFactory);

    RuntimeTypeAdapterFactory<Participant> participantAdapterFactory = RuntimeTypeAdapterFactory
    .of(Participant.class, "participantTypeJson")
    .registerSubtype(Student.class, "Student")
    .registerSubtype(Professor.class, "Professor")
    .registerSubtype(External.class, "External");

    gsonBuilder.registerTypeAdapterFactory(participantAdapterFactory);
  }

  public GsonBuilder getGsonBuilder() {
    return gsonBuilder;
  }
}
