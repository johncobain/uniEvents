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

import br.edu.ifba.inf0008.uniEvents.model.participants.External;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.model.participants.Student;
import br.edu.ifba.inf0008.uniEvents.model.participants.Teacher;
import br.edu.ifba.inf0008.uniEvents.utils.json.LocalDateAdapter;
import br.edu.ifba.inf0008.uniEvents.utils.json.gsonextras.RuntimeTypeAdapterFactory;

public class ParticipantRepository {
  private static final String PARTICIPANT_FILE = "data/participants.json";
  private List<Participant> participantsSaved;
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
      System.err.println("Error creating data directory: " + e.getMessage());
    }
  }

  private List<Participant> loadParticipants(){
    try (FileReader reader = new FileReader(PARTICIPANT_FILE)){
      Type participantListType = new TypeToken<ArrayList<Participant>>(){}.getType();
      List<Participant> participants = gson.fromJson(reader, participantListType);
      return participants != null ? participants: new ArrayList<>();
    } catch (IOException e) {
      System.err.println("Participants file not found, creating a new one: " + e.getMessage());
      return new ArrayList<>();
    }
  }

  public void saveParticipants(){
    try (FileWriter writer = new FileWriter(PARTICIPANT_FILE)){
      gson.toJson(participantsSaved, writer);
      System.out.println("Participants saved in " + PARTICIPANT_FILE);
    } catch (IOException e) {
      System.err.println("Error saving participants: " + e.getMessage());
    }
  }

  public void addParticipant(Participant participant){
    participantsSaved.add(participant);
    saveParticipants();
  }
  public void removeParticipant(Participant participant){
    participantsSaved.remove(participant);
    saveParticipants();
  }

  public void clearAllParticipants(){
    participantsSaved.clear();
    saveParticipants();
  }

  public void updateParticipant(Participant participant, String cpf){
    for (int i = 0; i < participantsSaved.size(); i++){
      if (participantsSaved.get(i).getCpf().equals(cpf)){
        participantsSaved.set(i, participant);
        break;
      }
    }
    saveParticipants();
  }

  public Participant getParticipant(String cpf){
    for (Participant participant : participantsSaved) {
      if (participant.getCpf().equals(cpf)) {
        return participant;
      }
    }
    return null;
  }

  public List<Participant> getParticipants(){
    return new ArrayList<>(participantsSaved);
  }
}
