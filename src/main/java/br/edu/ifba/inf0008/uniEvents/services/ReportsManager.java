package br.edu.ifba.inf0008.uniEvents.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;


public class ReportsManager {
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

  public String generateReport(List<Event> events, String reportTitle){
    StringBuilder report = new StringBuilder();
    report.append(Lines.doubleLine());
    report.append(Lines.titleLine("\n"));
    report.append(Lines.titleLine(String.format("Report: %s\n", reportTitle)));
    report.append(Lines.titleLine(String.format("Created at: %s\n", LocalDateTime.now().format(DATE_FORMATTER))));
    report.append(Lines.doubleLine());
    report.append("\n");
    report.append(Lines.titleLine("Events List\n"));
    report.append(Lines.straightLine());
    report.append("\n");
    if(events.isEmpty()){
      report.append("No events found for this report.\n");
    } else {
      for (Event event : events) {
        report.append(Lines.leftText(String.format("Title: %s\n", event.getName())));
        report.append(Lines.leftText(String.format("Description: %s\n", event.getDescription())));
        report.append(Lines.leftText(String.format("Modality: %s\n", event.getModality())));
        report.append(Lines.leftText(String.format("Location: %s\n", event.getLocation())));
        report.append(Lines.leftText(String.format("Date: %s\n", event.getDate().format(DATE_FORMATTER))));
        report.append(Lines.leftText(String.format("Capacity: %d\n", event.getCapacity())));
        report.append(Lines.leftText(String.format("Code: %s\n", event.getCode())));
        report.append(Lines.leftText(String.format("Participants: %d\n", event.getParticipants().size())));
        for(Participant participant: event.getParticipants()){
          report.append(Lines.leftText(String.format("\nParticipant: %s\n", participant.getName())));
          report.append(Lines.leftText(String.format("Email: %s\n", participant.getEmail())));
          report.append(Lines.leftText(String.format("Phone: %s\n", participant.getPhone())));
          report.append(Lines.leftText(String.format("Age: %s\n", participant.getAge())));
          report.append(Lines.leftText(String.format("Gender: %s\n", participant.getGender())));
          // report.append(Lines.leftText(String.format("Registration Date: %s\n", participant.getRegistrationDate().format(DATE_FORMATTER))));
        }
        report.append(Lines.straightLine());
        report.append("\n");
      }
    }
    report.append(Lines.titleLine("End of Report\n"));
    report.append(Lines.doubleLine());
    report.append("\n");
    return report.toString();
  }
}