package br.edu.ifba.inf0008.uniEvents.services;

import java.time.LocalDate;
import java.util.Arrays;

import br.edu.ifba.inf0008.uniEvents.model.events.AcademicFair;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.Lecture;
import br.edu.ifba.inf0008.uniEvents.model.events.ShortCourse;
import br.edu.ifba.inf0008.uniEvents.model.events.Workshop;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.Modality;
import br.edu.ifba.inf0008.uniEvents.model.events.enums.SkillLevel;
import br.edu.ifba.inf0008.uniEvents.model.participants.External;
import br.edu.ifba.inf0008.uniEvents.model.participants.Participant;
import br.edu.ifba.inf0008.uniEvents.model.participants.Professor;
import br.edu.ifba.inf0008.uniEvents.model.participants.Student;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.AcademicStatus;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.AcademicTitle;
import br.edu.ifba.inf0008.uniEvents.model.participants.enums.Course;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;
import br.edu.ifba.inf0008.uniEvents.utils.json.LocalDateAdapter;

public class DummyGenerator {
  public static void generateDummyData(IManager<Event> eventManager, IManager<Participant> participantManager) {
    System.out.println(Lines.warningLine("WARNING: Any Current Data will be overwritten!"));
    while (true) { 
      System.out.print("Are you sure you want to continue [y/N]? >>");
      String response = Utils.scanner.nextLine().trim().toLowerCase();
      if(response.equals("n") || response.equals("no") || response.isEmpty()){
        System.out.println(Lines.clear());
        System.out.println(Lines.warningLine("Dummy Data not saved!"));
        return;
      }
      if(response.equals("y") || response.equals("yes")) break;
    }
    
    participantManager.clear();
    
    //Students
    participantManager.add(
      new Student(  
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
        )
    );
    Student mary = (Student) participantManager.get("400.231.804-47");
    mary.addInterest("Operating Systems");
    mary.addInterest("Programming");

    participantManager.add(
      new Student(  
        "Josh Robert",
        "704.524.296-41",
        "ohmyjosh@gmail.com",
        "71 98185-9394",
        LocalDate.parse("12/12/2012", LocalDateAdapter.DATE_FORMATTER),
        "11223344556",
        Course.COMPUTER_ENGINEERING,
        8,
        AcademicStatus.ACTIVE,
        6.5,
        "UFBA",
        LocalDate.parse("10/02/2019", LocalDateAdapter.DATE_FORMATTER)
        )
    );

    participantManager.add(
      new Student(  
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
        )
    );
    Student ana = (Student) participantManager.get("592.201.489-78");
    ana.addInterest("Databases");
    ana.addInterest("Web Development");

    participantManager.add(
      new Student(  
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
        )
    );
    Student carlos = (Student) participantManager.get("161.075.797-10");
    carlos.addInterest("Artificial Intelligence");
    carlos.addInterest("Machine Learning");

    //Professors
    participantManager.add(
      new Professor(
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
        )
    );
    Professor robert = (Professor) participantManager.get("393.910.582-13");
    robert.addResearchArea("BlockChain");
    robert.addResearchArea("Criptography");
    robert.addPublication("Criptography and Security in Advanced Computing (2016)");

    participantManager.add(
      new Professor(
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
        )
    );
    Professor maria = (Professor) participantManager.get("987.898.480-04");
    maria.addResearchArea("Software Architecture");
    maria.addResearchArea("Agile Methods");
    maria.addPublication("Modern Software Engineering Practices (2018)");

    participantManager.add(
      new Professor(
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
        )
    );
    Professor patricia = (Professor) participantManager.get("072.608.242-20");
    patricia.addResearchArea("Big Data");
    patricia.addResearchArea("Data Mining");

    participantManager.add(
      new Professor(
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
        )
    );
    Professor fernando = (Professor) participantManager.get("434.866.551-67");
    fernando.addResearchArea("Machine Learning");
    fernando.addResearchArea("Natural Language Processing");
    fernando.addPublication("AI and Society: Challenges and Opportunities (2021)");
    fernando.addPublication("Neural Networks for Natural Language Processing (2022)");

    //Externals
    participantManager.add(
      new External(
        "Lisa Tyler",
        "045.464.455-80",
        "lisa@gmail.com",
        "71 95454-5454",
        LocalDate.parse("02/02/1990", LocalDateAdapter.DATE_FORMATTER),
        "MultiCom",
        "Developer",
        "Full-stack developer with 10+ years of experience in web and mobile application development. Passionate about building scalable solutions and mentoring junior developers.",
        true
        )
    );
    External lisa = (External) participantManager.get("045.464.455-80");
    lisa.addExpertiseArea("Java");
    lisa.addExpertiseArea("Python");
    lisa.addExpertiseArea("React");

    participantManager.add(
      new External(
        "Bruno Costa",
        "576.154.684-18",
        "bruno.costa@gmail.com",
        "71 91234-1111",
        LocalDate.parse("10/11/1988", LocalDateAdapter.DATE_FORMATTER),
        "TechSolutions",
        "Project Manager",
        "Experienced project manager with a background in software development and agile methodologies.",
        true
        )
    );
    External bruno = (External) participantManager.get("576.154.684-18");
    bruno.addExpertiseArea("Project Management");
    bruno.addExpertiseArea("Agile");

    participantManager.add(
      new External(
        "Sofia Mendes",
        "158.176.709-92",
        "sofia.mendes@outlook.com",
        "71 92345-2222",
        LocalDate.parse("25/06/1995", LocalDateAdapter.DATE_FORMATTER),
        "DataCorp",
        "Data Analyst",
        "Data analyst with expertise in data visualization and statistical analysis.",
        true
        )
    );
    External sofia = (External) participantManager.get("158.176.709-92");
    sofia.addExpertiseArea("Data Visualization");
    sofia.addExpertiseArea("Statistics");

    participantManager.add(
      new External(
        "Eduardo Ramos",
        "864.726.822-91",
        "eduardo.ramos@yahoo.com",
        "71 93456-3333",
        LocalDate.parse("03/04/1982", LocalDateAdapter.DATE_FORMATTER),
        "CyberSec",
        "Security Consultant",
        "Cybersecurity consultant with over 15 years of experience in network and application security.",
        false
        )
    );
    External eduardo = (External) participantManager.get("864.726.822-91");
    eduardo.addExpertiseArea("Cybersecurity");
    eduardo.addExpertiseArea("Network Security");

    eventManager.clear();
    // Lectures
    eventManager.add(
      new Lecture(
        "Introduction to Artificial Intelligence",
        "A comprehensive overview of AI concepts, history, and applications.",
        "Auditorium A",
        LocalDate.parse("15/09/2024", LocalDateAdapter.DATE_FORMATTER),
        0,
        Modality.ONLINE,
        "LEC001",
        "Artificial Intelligence",
        Arrays.asList("History of AI", "Machine Learning", "Ethics in AI"),
        Arrays.asList("Understand AI basics", "Explore real-world applications", "Discuss ethical implications"),
        "English",
        90
      )
    );

    eventManager.add(
      new Lecture(
        "Modern Web Development Trends",
        "Exploring the latest trends and technologies in web development.",
        "Room 101",
        LocalDate.parse("20/09/2024", LocalDateAdapter.DATE_FORMATTER),
        80,
        Modality.HYBRID,
        "LEC002",
        "Web Development",
        Arrays.asList("Frontend Frameworks", "Backend Technologies", "Web Security"),
        Arrays.asList("Identify modern tools", "Discuss best practices", "Highlight security concerns"),
        "Portuguese",
        60
      )
    );

    eventManager.add(
      new Lecture(
        "Cloud Computing Essentials",
        "Introduction to cloud computing concepts, services, and deployment models.",
        "Room 105",
        LocalDate.parse("18/09/2024", LocalDateAdapter.DATE_FORMATTER),
        60,
        Modality.INPERSON,
        "LEC003",
        "Cloud Computing",
        Arrays.asList("Cloud Models", "Service Types", "Security in Cloud"),
        Arrays.asList("Understand cloud basics", "Differentiate service models", "Discuss security challenges"),
        "English",
        75
      )
    );

    // Workshops
    eventManager.add(
      new Workshop(
        "Hands-on with Python for Data Science",
        "Practical workshop on using Python for data analysis and visualization.",
        "Lab 3",
        LocalDate.parse("22/09/2024", LocalDateAdapter.DATE_FORMATTER),
        30,
        Modality.INPERSON,
        "WS001",
        40,
        "Participants will learn Python basics, data analysis with pandas, and data visualization using matplotlib and seaborn."
      )
    );

    eventManager.add(
      new Workshop(
      "Agile Project Management",
      "Interactive workshop on agile methodologies and project management tools.",
      "Room 202",
      LocalDate.parse("25/09/2024", LocalDateAdapter.DATE_FORMATTER),
      25,
      Modality.ONLINE,
      "WS002",
      120,
      "Participants will explore Scrum, Kanban, and agile tools through hands-on activities to improve team collaboration and project delivery."
      )
    );

    eventManager.add(
      new Workshop(
        "DevOps Best Practices",
        "Workshop focused on modern DevOps tools, CI/CD pipelines, and automation.",
        "Lab 5",
        LocalDate.parse("27/09/2024", LocalDateAdapter.DATE_FORMATTER),
        20,
        Modality.HYBRID,
        "WS003",
        90,
        "Participants will set up CI/CD pipelines, explore containerization with Docker, and learn about infrastructure as code using tools like Terraform."
      )
    );

    // Short Courses
    eventManager.add(
      new ShortCourse(
      "Introduction to Cybersecurity",
      "Short course covering the fundamentals of cybersecurity.",
      "Room 303",
      LocalDate.parse("28/09/2024", LocalDateAdapter.DATE_FORMATTER),
      40,
      Modality.HYBRID,
      "SC001",
      4,
      Arrays.asList("Network Security Basics", "Malware and Threats", "Best Practices"),
      "Written Exam and Practical Lab",
      SkillLevel.BEGINNER
      )
    );

    eventManager.add(
      new ShortCourse(
      "Mobile App Development with React Native",
      "Learn to build cross-platform mobile apps using React Native.",
      "Lab 2",
      LocalDate.parse("02/10/2024", LocalDateAdapter.DATE_FORMATTER),
      35,
      Modality.INPERSON,
      "SC002",
      3,
      Arrays.asList("React Native Basics", "UI Components", "APIs Integration"),
      "Project Submission",
      SkillLevel.INTERMEDIATE
      )
    );

    eventManager.add(
      new ShortCourse(
        "Advanced Java Programming",
        "Deep dive into advanced Java concepts and best practices.",
        "Room 404",
        LocalDate.parse("05/10/2024", LocalDateAdapter.DATE_FORMATTER),
        25,
        Modality.ONLINE,
        "SC003",
        5,
        Arrays.asList("Generics", "Concurrency", "JVM Internals", "Performance Tuning"),
        "Final Project and Code Review",
        SkillLevel.ADVANCED
      )
    );

    // Academic Fairs
    eventManager.add(
      new AcademicFair(
        "Tech Innovation Fair",
        "Showcase of innovative tech projects by students and professionals.",
        "Main Hall",
        LocalDate.parse("10/10/2024", LocalDateAdapter.DATE_FORMATTER),
        200,
        Modality.INPERSON,
        "AF001",
        50,
        "Innovation and Technology",
        LocalDate.parse("12/10/2024", LocalDateAdapter.DATE_FORMATTER)
      )
    );

    eventManager.add(
      new AcademicFair(
      "Data Science Expo",
      "Exhibition of data science projects and research.",
      "Exhibition Center",
      LocalDate.parse("15/10/2024", LocalDateAdapter.DATE_FORMATTER),
      150,
      Modality.HYBRID,
      "AF002",
      40,
      "Data Science and Analytics",
      LocalDate.parse("17/10/2024", LocalDateAdapter.DATE_FORMATTER)
      )
    );

    eventManager.add(
      new AcademicFair(
      "GreenTech Academic Fair",
      "A fair focused on sustainable technology and green innovations.",
      "Eco Hall",
      LocalDate.parse("20/10/2024", LocalDateAdapter.DATE_FORMATTER),
      120,
      Modality.INPERSON,
      "AF003",
      30,
      "Sustainable Technology",
      LocalDate.parse("22/10/2024", LocalDateAdapter.DATE_FORMATTER)
      )
    );

    //TODO: add participant to events and generate certificates

    System.out.println(Lines.clear());
    System.out.println(Lines.successLine("Dummy Data saved (" + participantManager.getAll().size() + " participants and " + eventManager.getAll().size() + " events)!"));
      
  }
}
