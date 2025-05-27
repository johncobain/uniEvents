package br.edu.ifba.inf0008.uniEvents.services;

import java.time.LocalDate;

import br.edu.ifba.inf0008.uniEvents.model.participants.External;
import br.edu.ifba.inf0008.uniEvents.model.participants.Professor;
import br.edu.ifba.inf0008.uniEvents.model.participants.Student;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.AcademicStatus;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.AcademicTitle;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.Course;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;
import br.edu.ifba.inf0008.uniEvents.utils.json.LocalDateAdapter;

public class FileGenerator {
  public void generateDummyData(EventManager eventManager, ParticipantManager participantManager) {
    //TODO: Implement this method to generate dummy data for events and participants.
    System.out.println(Lines.warningLine("WARNING: Any Current Data will be overwritten!"));
    while (true) { 
      System.out.print("Are you sure you want to continue (y/N)? >>");
      String response = Utils.scanner.nextLine().trim().toLowerCase();
      if(response.equals("n") || response.equals("no") || response.isEmpty()){
        System.out.println(Lines.clear());
        System.out.println(Lines.warningLine("Dummy Data not saved!"));
        return;
      }
      if(response.equals("y") || response.equals("yes")) break;
    }
    
    participantManager.clear(eventManager);
    
    //Students
    participantManager.createStudent("John Doe", "863.860.423-87", "john@dee.com", "75 99192-9394", LocalDate.parse("02/02/2002", LocalDateAdapter.DATE_FORMATTER), "20242020202", Course.COMPUTER_SCIENCE, 3, AcademicStatus.ACTIVE, 8.0, "Ifba", LocalDate.parse("20/02/2020", LocalDateAdapter.DATE_FORMATTER));
    ((Student)participantManager.get("863.860.423-87")).addInterest("Math");
    ((Student)participantManager.get("863.860.423-87")).addInterest("Physics");
    ((Student)participantManager.get("863.860.423-87")).addInterest("Programming");

    participantManager.createStudent("Mary Smith", "192.137.810-75", "mary@smith.com", "71 99394-9192", LocalDate.parse("20/01/2003", LocalDateAdapter.DATE_FORMATTER), "20211231234", Course.COMPUTER_ENGINEERING, 6, AcademicStatus.ACTIVE, 7.5, "UFBA", LocalDate.parse("20/08/2020", LocalDateAdapter.DATE_FORMATTER));
    ((Student)participantManager.get("192.137.810-75")).addInterest("Operating Systems");
    ((Student)participantManager.get("192.137.810-75")).addInterest("Programming");

    participantManager.createStudent("Josh Robert", "302.337.800-25", "ohmyjosh@gmail.com", "71 98185-9394", LocalDate.parse("12/12/2012", LocalDateAdapter.DATE_FORMATTER), "11223344556", Course.COMPUTER_ENGINEERING, 8, AcademicStatus.ACTIVE, 7, "UFBA", LocalDate.parse("10/02/2019", LocalDateAdapter.DATE_FORMATTER));

    //Professors
    participantManager.createProfessor("Robert Allan", "452.848.964-32", "robert@ifba.edu.br", "71 99897-9695", LocalDate.parse("02/02/1970", LocalDateAdapter.DATE_FORMATTER), "12345678900", Course.COMPUTER_SCIENCE, "IFBA", AcademicTitle.MSC, "Network");
    ((Professor)participantManager.get("452.848.964-32")).addResearchArea("BlockChain");
    ((Professor)participantManager.get("452.848.964-32")).addResearchArea("Criptography");
    ((Professor)participantManager.get("452.848.964-32")).addPublication("Criptography and Security in Advanced Computing (2016)");

    //Externals
    participantManager.createExternal("Lisa Tyler", "627.434.187-02", "lisa@gmail.com", "71 95454-5454", LocalDate.parse("02/02/1990", LocalDateAdapter.DATE_FORMATTER), "MultiCom", "Developer", 
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", 
    true);
    ((External)participantManager.get("627.434.187-02")).addExpertiseArea("JavaScript");
    
    System.out.println(Lines.clear());
    System.out.println(Lines.successLine("Dummy Data saved (" + participantManager.getAll().size() + " participants and " + eventManager.getAll().size() + " events)!"));
      
  }

  protected void saveReports(String report) {
    //TODO: Implement this method to save the generated reports to a file.txt
    // This method should save the generated reports to a file.txt
    // Implementation details will depend on the specific requirements.
    // select file path and save the content
    // Should only be called by ReportsManager
  }
}
