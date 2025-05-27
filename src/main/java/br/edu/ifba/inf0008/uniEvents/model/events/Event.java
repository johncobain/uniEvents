package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.repository.ParticipantRepository;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.json.LocalDateAdapter;

public abstract class Event {
  private String name;
  private String description;
  private String location;
  private LocalDate date;
  private int capacity;
  private Modality modality;
  private final ArrayList<String> participantsCpfs;
  private transient LinkedHashMap<String, Participant> participants;
  private String code;
  
  protected Event(String name, String description, String location, LocalDate date, int capacity, Modality modality, String code) {
    this.name = name;
    this.description = description;
    this.location = location;
    this.date = date;
    this.capacity = capacity;
    this.modality = modality;
    this.code = code;
    this.participantsCpfs = new ArrayList<>();
    this.participants = new LinkedHashMap<>();
  }

  protected  Event(){
    this.participantsCpfs = new ArrayList<>();
    this.participants = new LinkedHashMap<>();
  } //Gson

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public Modality getModality() {
    return modality;
  }

  public void setModality(Modality modality) {
    this.modality = modality;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ArrayList<String> getParticipantsCpfs() {
    return participantsCpfs;
  }

  public void clearParticipantsCpfs() {
    this.participantsCpfs.clear();
  }

  public void populateParticipants(ParticipantRepository participantRepository) {
    if(this.participantsCpfs != null && participantRepository != null){
      for (String cpf : this.participantsCpfs) {
        Participant participant = participantRepository.get(cpf);
        if (participant != null) {
          this.participants.put(cpf, participant);
        }
      }
    }
  }

  public LinkedHashMap<String, Participant> getParticipants() {
    return participants;
  }

  public void setParticipants(LinkedHashMap<String, Participant> participants) {
    this.participants = participants;
  }

  public void addParticipant(Participant participant) {
    this.participantsCpfs.add(participant.getCpf());
    this.participants.put(participant.getCpf(), participant);
  }
  public void removeParticipant(String cpf) {
    if(!this.participantsCpfs.contains(cpf)) return;
    this.participantsCpfs.remove(cpf);
    this.participants.remove(cpf);
  }

  public boolean isFull() {
    return participants.size() >= capacity;
  }
  public boolean isParticipantRegistered(String cpf) {
    return participants.containsKey(cpf);
  }

  

  public abstract String getType();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Lines.multiLineText(String.format("Event: %s", getType()))).append("\n");
        sb.append(Lines.multiLineText(String.format("Title: %s", name))).append("\n");
        sb.append(Lines.multiLineText(String.format("Description: %s", description))).append("\n");
        sb.append(Lines.multiLineText(String.format("Modality: %s", modality.getDescription()))).append("\n");
        sb.append(Lines.multiLineText(String.format("Location: %s", location))).append("\n");
        sb.append(Lines.multiLineText(String.format("Date: %s", this.getDate().format(LocalDateAdapter.DATE_FORMATTER)))).append("\n");
        sb.append(Lines.multiLineText(String.format("Capacity: %d", capacity))).append("\n");
        sb.append(Lines.multiLineText(String.format("Code: %s", code))).append("\n");
        sb.append(Lines.multiLineText(String.format("Participants: %d", participants.size()))).append("\n");
        return sb.toString();
    }

  
}
