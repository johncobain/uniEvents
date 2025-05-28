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
  private int totalCount;
  private int onlineCount;
  private Modality modality;
  private final ArrayList<String> participantsCpfs;
  private final ArrayList<String> onlineParticipantsCpfs;
  private transient LinkedHashMap<String, Participant> participants;
  private transient LinkedHashMap<String, Participant> onlineParticipants;
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
    this.onlineParticipantsCpfs = new ArrayList<>();
    this.onlineParticipants = new LinkedHashMap<>();

    this.totalCount = 0;
    this.onlineCount = 0;
  }

  protected  Event(){
    this.participantsCpfs = new ArrayList<>();
    this.participants = new LinkedHashMap<>();
    this.onlineParticipantsCpfs = new ArrayList<>();
    this.onlineParticipants = new LinkedHashMap<>();
    this.totalCount = 0;
    this.onlineCount = 0;
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

  public void populateParticipants(ParticipantRepository participantRepository) {//TODO: don't use participantRepository
    if(this.participantsCpfs != null && participantRepository != null){
      for (String cpf : this.participantsCpfs) {
        Participant participant = participantRepository.get(cpf);
        if (participant != null) {
          this.participants.put(cpf, participant);
        }
      }
    }
    if(this.onlineParticipantsCpfs != null && participantRepository != null){
      for (String cpf : this.onlineParticipantsCpfs) {
        Participant participant = participantRepository.get(cpf);
        if (participant != null) {
          this.onlineParticipants.put(cpf, participant);
        }
      }
    }
  }

  public LinkedHashMap<String, Participant> getParticipants() {
    LinkedHashMap<String, Participant> totalParticipants = new LinkedHashMap<>();
    totalParticipants.putAll(this.participants);
    totalParticipants.putAll(this.onlineParticipants);
    return totalParticipants;
  }

  public void setParticipants(LinkedHashMap<String, Participant> participants, LinkedHashMap<String, Participant> onlineParticipants, int totalCount, int onlineCount) {
    this.totalCount = totalCount;
    this.onlineCount = onlineCount;
    
    this.participantsCpfs.clear();
    for (Participant participant : participants.values()) {
      this.participantsCpfs.add(participant.getCpf());
    }
    this.participants = participants;

    this.onlineParticipantsCpfs.clear();
    for (Participant participant : onlineParticipants.values()) {
      this.onlineParticipantsCpfs.add(participant.getCpf());
    }
    this.onlineParticipants = onlineParticipants;
  }

  public void addParticipant(Participant participant, Modality modality) {
    if(modality == Modality.INPERSON){
      if(this.isFull()) throw new RuntimeException("Event is full!");
      this.participantsCpfs.add(participant.getCpf());
      this.participants.put(participant.getCpf(), participant);
      this.totalCount++;
    } else if(modality == Modality.ONLINE){
      this.onlineParticipantsCpfs.add(participant.getCpf());
      this.onlineParticipants.put(participant.getCpf(), participant);
      this.totalCount++;
      this.onlineCount++;
    }
  }
  public void removeParticipant(String cpf) {
    if(!this.participantsCpfs.contains(cpf) && !this.onlineParticipantsCpfs.contains(cpf)) return;
    this.participantsCpfs.remove(cpf);
    this.participants.remove(cpf);

    this.onlineParticipantsCpfs.remove(cpf);
    this.onlineParticipants.remove(cpf);
  }

  public boolean isFull() {
    return this.totalCount - this.onlineCount >= capacity;
  }
  public boolean isParticipantRegistered(String cpf) {
    return participants.containsKey(cpf) || onlineParticipants.containsKey(cpf);
  }  

  public abstract String getType();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Lines.multiLineText(String.format("Event: %s", getType()))).append("\n");
        sb.append(Lines.multiLineText(String.format("Code: %s", code))).append("\n");
        sb.append(Lines.multiLineText(String.format("Title: %s", name))).append("\n");
        sb.append(Lines.multiLineText(String.format("Description: %s", description))).append("\n");
        sb.append(Lines.multiLineText(String.format("Modality: %s", modality.getDescription()))).append("\n");
        sb.append(Lines.multiLineText(String.format("Location: %s", location))).append("\n");
        sb.append(Lines.multiLineText(String.format("Date: %s", this.getDate().format(LocalDateAdapter.DATE_FORMATTER)))).append("\n");
        if(this.modality == Modality.INPERSON || this.modality == Modality.HYBRID){
          sb.append(Lines.multiLineText(String.format("Capacity: %d", capacity))).append("\n");
          sb.append(Lines.multiLineText(String.format("Total Participants: %d", totalCount))).append("\n");
        }
        if(this.modality == Modality.ONLINE || this.modality == Modality.HYBRID){
          sb.append(Lines.multiLineText(String.format("Online Participants: %d", onlineCount))).append("\n");
        } 

        return sb.toString();
    }

  
}
