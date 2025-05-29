package br.edu.ifba.inf0008.uniEvents.model.events;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import br.edu.ifba.inf0008.uniEvents.exceptions.CapacityExceededException;
import br.edu.ifba.inf0008.uniEvents.exceptions.NotFoundException;
import br.edu.ifba.inf0008.uniEvents.exceptions.UniEventsException;
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
  private double totalHours;
  private final ArrayList<String> inPersonParticipantsCpfs;
  private final ArrayList<String> onlineParticipantsCpfs;
  private transient LinkedHashMap<String, Participant> inPersonParticipants;
  private transient LinkedHashMap<String, Participant> onlineParticipants;
  private String code;
  
  protected Event(String name, String description, String location, LocalDate date, int capacity, Modality modality, double totalHours, String code) {
    this.name = name;
    this.description = description;
    this.location = location;
    this.date = date;
    this.capacity = capacity;
    this.modality = modality;
    this.totalHours = totalHours;
    this.code = code;

    this.inPersonParticipantsCpfs = new ArrayList<>();
    this.inPersonParticipants = new LinkedHashMap<>();
    this.onlineParticipantsCpfs = new ArrayList<>();
    this.onlineParticipants = new LinkedHashMap<>();
  }

  protected  Event(){
    this.inPersonParticipantsCpfs = new ArrayList<>();
    this.inPersonParticipants = new LinkedHashMap<>();
    this.onlineParticipantsCpfs = new ArrayList<>();
    this.onlineParticipants = new LinkedHashMap<>();
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

  public double getTotalHours() {
    return totalHours;
  }

  public void setTotalHours(int totalHours) {
    this.totalHours = totalHours;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void populateParticipants(ParticipantRepository participantRepository) {
    if(this.inPersonParticipantsCpfs != null && participantRepository != null){
      for (String cpf : this.inPersonParticipantsCpfs) {
        Participant participant = participantRepository.get(cpf);
        if (participant != null) {
          this.inPersonParticipants.put(cpf, participant);
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
    totalParticipants.putAll(this.inPersonParticipants);
    totalParticipants.putAll(this.onlineParticipants);
    return totalParticipants;
  }

  public LinkedHashMap<String, Participant> getInPersonParticipants() {
    return inPersonParticipants;
  }

  public LinkedHashMap<String, Participant> getOnlineParticipants() {
    return onlineParticipants;
  }

  public void setParticipants(LinkedHashMap<String, Participant> participants, LinkedHashMap<String, Participant> onlineParticipants) {
    if(this.modality != Modality.ONLINE){
      this.inPersonParticipantsCpfs.clear();
      for (Participant participant : participants.values()) {
        this.inPersonParticipantsCpfs.add(participant.getCpf());
      }
      this.inPersonParticipants = participants;
    }

    if(this.modality != Modality.INPERSON){
      this.onlineParticipantsCpfs.clear();
      for (Participant participant : onlineParticipants.values()) {
        this.onlineParticipantsCpfs.add(participant.getCpf());
      }
      this.onlineParticipants = onlineParticipants;
    }
  }

  public void addParticipant(Participant participant, Modality modality) throws UniEventsException{
    if(modality == Modality.INPERSON){
      if(this.isFull()) throw new CapacityExceededException(this.code, this.capacity);

      this.inPersonParticipantsCpfs.add(participant.getCpf());
      this.inPersonParticipants.put(participant.getCpf(), participant);
    } else if(modality == Modality.ONLINE){
      this.onlineParticipantsCpfs.add(participant.getCpf());
      this.onlineParticipants.put(participant.getCpf(), participant);
    }
  }
  public void removeParticipant(String cpf) throws UniEventsException {
    if(!this.inPersonParticipantsCpfs.contains(cpf) && !this.onlineParticipantsCpfs.contains(cpf)) throw new NotFoundException("Participant", cpf);

    this.inPersonParticipantsCpfs.remove(cpf);
    this.inPersonParticipants.remove(cpf);

    this.onlineParticipantsCpfs.remove(cpf);
    this.onlineParticipants.remove(cpf);
  }

  public void clearParticipants(){
    this.inPersonParticipantsCpfs.clear();
    this.inPersonParticipants.clear();
    this.onlineParticipantsCpfs.clear();
    this.onlineParticipants.clear();
  }

  public boolean isFull() {
    return this.inPersonParticipants.size() >= capacity;
  }

  public String getInstructions(){
    return this.modality.getInstructions();
  }

  public boolean isParticipantRegistered(String cpf) {
    return inPersonParticipants.containsKey(cpf) || onlineParticipants.containsKey(cpf);
  } 

  public void updateParticipant(String cpf, Participant participant) throws UniEventsException {
    if(!this.inPersonParticipantsCpfs.contains(cpf) && !this.onlineParticipantsCpfs.contains(cpf)) throw new NotFoundException("Participant", cpf);

    if(this.inPersonParticipantsCpfs.contains(cpf)){
      this.inPersonParticipants.put(cpf, participant);
    } else {
      this.onlineParticipants.put(cpf, participant);
    }
  }

  public void generateCertificate(String cpf) throws UniEventsException{
    Certificate certificate = new Certificate(this, this.getParticipants().get(cpf));
    this.getParticipants().get(cpf).addCertificate(certificate);
  }

  public abstract String getType();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Lines.multiLineText(String.format("Code: %s", code))).append("\n");
        sb.append(Lines.multiLineText(String.format("Title: %s", name))).append("\n");
        sb.append(Lines.multiLineText(String.format("Description: %s", description))).append("\n");
        sb.append(Lines.multiLineText(String.format("Modality: %s", modality.getDescription()))).append("\n");
        sb.append(Lines.multiLineText(String.format("Total Hours: %.2f", totalHours))).append("\n");
        sb.append(Lines.multiLineText(String.format("Location: %s", location))).append("\n");
        sb.append(Lines.multiLineText(String.format("Date: %s", this.getDate().format(LocalDateAdapter.DATE_FORMATTER)))).append("\n");
        if(this.modality == Modality.INPERSON || this.modality == Modality.HYBRID){
          sb.append(Lines.multiLineText(String.format("Capacity: %d", capacity))).append("\n");
          sb.append(Lines.multiLineText(String.format("In-Person Participants: %d", this.inPersonParticipants.size()))).append("\n");
        }
        if(this.modality == Modality.ONLINE || this.modality == Modality.HYBRID){
          sb.append(Lines.multiLineText(String.format("Online Participants: %d", this.onlineParticipants.size()))).append("\n");
        } 

        return sb.toString();
    }

  
}
