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

import br.edu.ifba.inf0008.uniEvents.model.participants.External;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.model.participants.Student;
import br.edu.ifba.inf0008.uniEvents.model.participants.Teacher;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.json.LocalDateAdapter;
import br.edu.ifba.inf0008.uniEvents.utils.json.gsonextras.RuntimeTypeAdapterFactory;

public class ParticipantRepository {
  private static final String PARTICIPANT_FILE = "data/participants.json";
  private LinkedHashMap<String, Participant> participantsSaved;
  private Gson gson;
  
  public ParticipantRepository(){
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
    gsonBuilder.setPrettyPrinting();

    RuntimeTypeAdapterFactory<Participant> participantAdapterFactory = RuntimeTypeAdapterFactory
    .of(Participant.class, "participantTypeJson")
    .registerSubtype(Student.class, "Student")
    .registerSubtype(Teacher.class, "Teacher")
    .registerSubtype(External.class, "External");

    gsonBuilder.registerTypeAdapterFactory(participantAdapterFactory);

    this.gson = gsonBuilder.create();
    this.participantsSaved = loadParticipants();

    try {
        Files.createDirectories(Paths.get("data"));
    } catch (IOException e) {
      System.err.println(Lines.errorLine("Error creating data directory: " + e.getMessage()));
    }
  }

  private LinkedHashMap<String, Participant> loadParticipants(){
    LinkedHashMap<String, Participant> loadedParticipants;
    try (FileReader reader = new FileReader(PARTICIPANT_FILE)){
      Type participantListType = new TypeToken<ArrayList<Participant>>(){}.getType();
      List<Participant> participants = gson.fromJson(reader, participantListType);
      if (participants != null) {
        loadedParticipants = new LinkedHashMap<>();
        for (Participant participant : participants) {
          loadedParticipants.put(participant.getCpf(), participant);
        }
      } else {
        loadedParticipants = new LinkedHashMap<>();
      }
      return loadedParticipants;
    } catch (IOException e) {
      System.err.println(Lines.warningLine("Participants file not found, creating a new one"));
      return new LinkedHashMap<>();
    }
  }

  public void saveParticipants(){
    List<Participant> participantsList = new ArrayList<>(participantsSaved.values());
    try (FileWriter writer = new FileWriter(PARTICIPANT_FILE)){
      gson.toJson(participantsList, writer);
      System.out.println("Participants saved in " + PARTICIPANT_FILE);
    } catch (IOException e) {
      System.err.println(Lines.errorLine("Error saving participants: " + e.getMessage()));
    }
  }

  public void addParticipant(Participant participant){
    participantsSaved.put(participant.getCpf(), participant);
    saveParticipants();
  }
  public void removeParticipant(Participant participant){
    participantsSaved.remove(participant.getCpf());
    saveParticipants();
  }

  public void clearAllParticipants(){
    participantsSaved.clear();
    saveParticipants();
  }

  public void updateParticipant(Participant participant, String cpf){
    if (participantsSaved.get(cpf) == null) {
      System.err.println(Lines.errorLine("Participant with CPF " + cpf + " not found."));
      return;
    }
    participantsSaved.put(cpf, participant);
    saveParticipants();
  }

  public Participant getParticipant(String cpf){
    return participantsSaved.get(cpf);
  }

  public LinkedHashMap<String, Participant> getParticipants(){
    if (participantsSaved == null) {
      return new LinkedHashMap<>();
    }
    return participantsSaved;    
  }
}
