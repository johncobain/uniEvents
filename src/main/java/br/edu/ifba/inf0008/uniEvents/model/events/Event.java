package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.repository.ParticipantRepository;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;

public abstract class Event {
  private String name;
  private String description;
  private String location;
  private LocalDate date;
  private int capacity;
  private Modality modality;
  private ArrayList<String> participantsCpfs;
  private transient ArrayList<Participant> participants;
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
    this.participants = new ArrayList<>();
  }

  protected  Event(){
    this.participantsCpfs = new ArrayList<>();
    this.participants = new ArrayList<>();
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

  public void setParticipantsCpfs(ArrayList<String> participantsCpfs) {
    this.participantsCpfs = participantsCpfs;
  }
  public void addParticipantCpf(String cpf) {
    this.participantsCpfs.add(cpf);
  }
  public void removeParticipantCpf(String cpf) {
    Participant participant = null;
    for (Participant p : this.participants) {
      if (p.getCpf().equals(cpf)) {
        participant = p;
        break;
      }
    }
    this.participantsCpfs.remove(cpf);
    this.removeParticipant(participant);
  }
  public void clearParticipantsCpfs() {
    this.participantsCpfs.clear();
  }

  public void populateParticipants(ParticipantRepository participantRepository) {
    if(this.participantsCpfs != null && participantRepository != null){
      for (String cpf : this.participantsCpfs) {
        Participant participant = participantRepository.getParticipant(cpf);
        if (participant != null) {
          this.participants.add(participant);
        }
      }
    }
  }

  public ArrayList<Participant> getParticipants() {
    return participants;
  }

  public void setParticipants(ArrayList<Participant> participants) {
    this.participants = participants;
  }

  public void addParticipant(Participant participant) {
    this.participants.add(participant);
  }
  public void removeParticipant(Participant participant) {
    this.participants.remove(participant);
  }

  public boolean isFull() {
    return participants.size() >= capacity;
  }
  public boolean isParticipantRegistered(Participant participant) {
    return participants.contains(participant);
  }

  

  public abstract String getType();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Lines.leftText(String.format("Event: %s", getType()))).append("\n");
        sb.append(Lines.leftText(String.format("Title: %s", name))).append("\n");
        sb.append(Lines.leftText(String.format("Description: %s", description))).append("\n");
        sb.append(Lines.leftText(String.format("Modality: %s", modality))).append("\n");
        sb.append(Lines.leftText(String.format("Location: %s", location))).append("\n");
        sb.append(Lines.leftText(String.format("Date: %s", this.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))))).append("\n");
        sb.append(Lines.leftText(String.format("Capacity: %d", capacity))).append("\n");
        sb.append(Lines.leftText(String.format("Code: %s", code))).append("\n");
        sb.append(Lines.leftText(String.format("Participants: %d", participants.size()))).append("\n");
        return sb.toString();
    }

  
}
