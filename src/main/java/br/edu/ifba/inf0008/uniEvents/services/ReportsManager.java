package br.edu.ifba.inf0008.uniEvents.services;

import java.time.format.DateTimeFormatter;

import br.edu.ifba.inf0008.uniEvents.repository.EventRepository;
import br.edu.ifba.inf0008.uniEvents.repository.ParticipantRepository;

public class ReportsManager {
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public ReportsManager(EventRepository eventRepository, ParticipantRepository participantRepository) {
    
  }
}
