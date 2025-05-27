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

public class DummyGenerator {
  public static void generateDummyData(EventManager eventManager, ParticipantManager participantManager) {
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
    participantManager.createStudent(
      "Mary Smith",
      "400.231.804-47",
      "mary@smith.com",
      "71 99394-9192",
      LocalDate.parse("20/01/2003", LocalDateAdapter.DATE_FORMATTER),
      "20211231234",
      Course.COMPUTER_ENGINEERING,
      6,
      AcademicStatus.ACTIVE,
      7.5,
      "UFBA",
      LocalDate.parse("20/08/2020", LocalDateAdapter.DATE_FORMATTER)
    );
    Student mary = (Student) participantManager.get("400.231.804-47");
    mary.addInterest("Operating Systems");
    mary.addInterest("Programming");

    participantManager.createStudent(
      "Josh Robert",
      "704.524.296-41",
      "ohmyjosh@gmail.com",
      "71 98185-9394",
      LocalDate.parse("12/12/2012", LocalDateAdapter.DATE_FORMATTER),
      "11223344556",
      Course.COMPUTER_ENGINEERING,
      8,
      AcademicStatus.ACTIVE,
      7,
      "UFBA",
      LocalDate.parse("10/02/2019", LocalDateAdapter.DATE_FORMATTER)
    );

    participantManager.createStudent(
      "Ana Paula",
      "592.201.489-78",
      "ana.paula@email.com",
      "71 91234-5678",
      LocalDate.parse("15/03/2001", LocalDateAdapter.DATE_FORMATTER),
      "20210010001",
      Course.INFORMATION_SYSTEMS,
      4,
      AcademicStatus.ACTIVE,
      8.7,
      "IFBA",
      LocalDate.parse("01/03/2019", LocalDateAdapter.DATE_FORMATTER)
    );
    Student ana = (Student) participantManager.get("592.201.489-78");
    ana.addInterest("Databases");
    ana.addInterest("Web Development");

    participantManager.createStudent(
      "Carlos Silva",
      "161.075.797-10",
      "carlos.silva@hotmail.com",
      "71 99876-5432",
      LocalDate.parse("22/07/2000", LocalDateAdapter.DATE_FORMATTER),
      "20209998877",
      Course.COMPUTER_SCIENCE,
      7,
      AcademicStatus.ACTIVE,
      7.9,
      "UFBA",
      LocalDate.parse("10/02/2018", LocalDateAdapter.DATE_FORMATTER)
    );
    Student carlos = (Student) participantManager.get("161.075.797-10");
    carlos.addInterest("Artificial Intelligence");
    carlos.addInterest("Machine Learning");

    //Professors
    participantManager.createProfessor(
      "Robert Allan",
      "393.910.582-13",
      "robert@ifba.edu.br",
      "71 99897-9695",
      LocalDate.parse("02/02/1970", LocalDateAdapter.DATE_FORMATTER),
      "12345678900",
      Course.COMPUTER_SCIENCE,
      "IFBA",
      AcademicTitle.MSC,
      "Network"
    );
    Professor robert = (Professor) participantManager.get("393.910.582-13");
    robert.addResearchArea("BlockChain");
    robert.addResearchArea("Criptography");
    robert.addPublication("Criptography and Security in Advanced Computing (2016)");

    participantManager.createProfessor(
      "Maria Fernanda",
      "987.898.480-04",
      "maria.fernanda@ifba.edu.br",
      "71 91234-5678",
      LocalDate.parse("10/05/1980", LocalDateAdapter.DATE_FORMATTER),
      "98765432100",
      Course.INFORMATION_SYSTEMS,
      "IFBA",
      AcademicTitle.PHD,
      "Software Engineering"
    );
    Professor maria = (Professor) participantManager.get("987.898.480-04");
    maria.addResearchArea("Software Architecture");
    maria.addResearchArea("Agile Methods");
    maria.addPublication("Modern Software Engineering Practices (2018)");

    participantManager.createProfessor(
      "Patricia Souza",
      "072.608.242-20",
      "patricia.souza@ifba.edu.br",
      "71 93456-7890",
      LocalDate.parse("05/09/1985", LocalDateAdapter.DATE_FORMATTER),
      "22334455667",
      Course.INFORMATION_SYSTEMS,
      "IFBA",
      AcademicTitle.MSC,
      "Data Science"
    );
    Professor patricia = (Professor) participantManager.get("072.608.242-20");
    patricia.addResearchArea("Big Data");
    patricia.addResearchArea("Data Mining");

    participantManager.createProfessor(
      "Fernando Lima",
      "434.866.551-67",
      "fernando.lima@ifba.edu.br",
      "71 94567-8901",
      LocalDate.parse("18/03/1968", LocalDateAdapter.DATE_FORMATTER),
      "33445566778",
      Course.COMPUTER_SCIENCE,
      "IFBA",
      AcademicTitle.PHD,
      "Artificial Intelligence"
    );
    Professor fernando = (Professor) participantManager.get("434.866.551-67");
    fernando.addResearchArea("Machine Learning");
    fernando.addResearchArea("Natural Language Processing");
    fernando.addPublication("AI and Society: Challenges and Opportunities (2021)");
    fernando.addPublication("Neural Networks for Natural Language Processing (2022)");

    //Externals
    participantManager.createExternal(
      "Lisa Tyler",
      "045.464.455-80",
      "lisa@gmail.com",
      "71 95454-5454",
      LocalDate.parse("02/02/1990", LocalDateAdapter.DATE_FORMATTER),
      "MultiCom",
      "Developer",
      "Full-stack developer with 10+ years of experience in web and mobile application development. Passionate about building scalable solutions and mentoring junior developers.",
      true
    );
    External lisa = (External) participantManager.get("045.464.455-80");
    lisa.addExpertiseArea("Java");
    lisa.addExpertiseArea("Python");
    lisa.addExpertiseArea("React");

    participantManager.createExternal(
      "Bruno Costa",
      "576.154.684-18",
      "bruno.costa@gmail.com",
      "71 91234-1111",
      LocalDate.parse("10/11/1988", LocalDateAdapter.DATE_FORMATTER),
      "TechSolutions",
      "Project Manager",
      "Experienced project manager with a background in software development and agile methodologies.",
      true
    );
    External bruno = (External) participantManager.get("576.154.684-18");
    bruno.addExpertiseArea("Project Management");
    bruno.addExpertiseArea("Agile");

    participantManager.createExternal(
      "Sofia Mendes",
      "158.176.709-92",
      "sofia.mendes@outlook.com",
      "71 92345-2222",
      LocalDate.parse("25/06/1995", LocalDateAdapter.DATE_FORMATTER),
      "DataCorp",
      "Data Analyst",
      "Data analyst with expertise in data visualization and statistical analysis.",
      true
    );
    External sofia = (External) participantManager.get("158.176.709-92");
    sofia.addExpertiseArea("Data Visualization");
    sofia.addExpertiseArea("Statistics");

    participantManager.createExternal(
      "Eduardo Ramos",
      "864.726.822-91",
      "eduardo.ramos@yahoo.com",
      "71 93456-3333",
      LocalDate.parse("03/04/1982", LocalDateAdapter.DATE_FORMATTER),
      "CyberSec",
      "Security Consultant",
      "Cybersecurity consultant with over 15 years of experience in network and application security.",
      false
    );
    External eduardo = (External) participantManager.get("864.726.822-91");
    eduardo.addExpertiseArea("Cybersecurity");
    eduardo.addExpertiseArea("Network Security");

    //Lectures

    //Workshops

    //Short Courses

    //Academic Fairs
    
    System.out.println(Lines.clear());
    System.out.println(Lines.successLine("Dummy Data saved (" + participantManager.getAll().size() + " participants and " + eventManager.getAll().size() + " events)!"));
      
  }
}
