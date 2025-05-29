package br.edu.ifba.inf0008.uniEvents.services.generators;

import java.util.Arrays;

import br.edu.ifba.inf0008.uniEvents.forms.CommonForms;
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
import br.edu.ifba.inf0008.uniEvents.services.managers.IManager;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class DummyGenerator {
  public static void generateDummyData(IManager<Event> eventManager, IManager<Participant> participantManager) {
    System.out.println(Lines.warningLine("WARNING: Any Current Data will be overwritten!"));
    String confirmation = CommonForms.getYN("Are you sure you want to continue?", "n");
    if (confirmation.equalsIgnoreCase("n")) {
      System.out.println(Lines.clear());
      System.out.println(Lines.warningLine("Dummy Data not saved!"));
      return;
    }
    
    participantManager.clear();
    
    //Students
    participantManager.add(
      new Student(  
        "Mary Smith",
        "400.231.804-47",
        "mary@smith.com",
        "71 99394-9192",
        Utils.stringToDate("20/01/2003"),
        "20211231234",
        Course.COMPUTER_ENGINEERING,
        6,
        AcademicStatus.ACTIVE,
        7.5,
        "UFBA",
        Utils.stringToDate("20/08/2020")
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
        Utils.stringToDate("12/12/2012"),
        "11223344556",
        Course.COMPUTER_ENGINEERING,
        8,
        AcademicStatus.ACTIVE,
        6.5,
        "UFBA",
        Utils.stringToDate("10/02/2019")
        )
    );

    participantManager.add(
      new Student(  
        "Ana Paula",
        "592.201.489-78",
        "ana.paula@email.com",
        "71 91234-5678",
        Utils.stringToDate("15/03/2001"),
        "20210010001",
        Course.INFORMATION_SYSTEMS,
        4,
        AcademicStatus.ACTIVE,
        8.7,
        "IFBA",
        Utils.stringToDate("01/03/2019")
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
        Utils.stringToDate("22/07/2000"),
        "20209998877",
        Course.COMPUTER_SCIENCE,
        7,
        AcademicStatus.ACTIVE,
        7.9,
        "UFBA",
        Utils.stringToDate("10/02/2018")
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
        Utils.stringToDate("02/02/1970"),
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
        Utils.stringToDate("10/05/1980"),
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
        Utils.stringToDate("05/09/1985"),
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
        Utils.stringToDate("18/03/1968"),
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
        Utils.stringToDate("02/02/1990"),
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
        Utils.stringToDate("10/11/1988"),
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
        Utils.stringToDate("25/06/1995"),
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
        Utils.stringToDate("03/04/1982"),
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
        Utils.stringToDate("15/09/2024"),
        0,
        Modality.ONLINE,
        90,
        "LEC001",
        "Artificial Intelligence",
        Arrays.asList("History of AI", "Machine Learning", "Ethics in AI"),
        Arrays.asList("Understand AI basics", "Explore real-world applications", "Discuss ethical implications"),
        "English"
      )
    );

    eventManager.add(
      new Lecture(
        "Modern Web Development Trends",
        "Exploring the latest trends and technologies in web development.",
        "Room 101",
        Utils.stringToDate("20/09/2024"),
        80,
        Modality.HYBRID,
        60,
        "LEC002",
        "Web Development",
        Arrays.asList("Frontend Frameworks", "Backend Technologies", "Web Security"),
        Arrays.asList("Identify modern tools", "Discuss best practices", "Highlight security concerns"),
        "Portuguese"
      )
    );

    eventManager.add(
      new Lecture(
        "Cloud Computing Essentials",
        "Introduction to cloud computing concepts, services, and deployment models.",
        "Room 105",
        Utils.stringToDate("18/09/2024"),
        60,
        Modality.INPERSON,
        75,
        "LEC003",
        "Cloud Computing",
        Arrays.asList("Cloud Models", "Service Types", "Security in Cloud"),
        Arrays.asList("Understand cloud basics", "Differentiate service models", "Discuss security challenges"),
        "English"
      )
    );

    // Workshops
    eventManager.add(
      new Workshop(
        "Hands-on with Python for Data Science",
        "Practical workshop on using Python for data analysis and visualization.",
        "Lab 3",
        Utils.stringToDate("22/09/2024"),
        30,
        Modality.INPERSON,
        40,
        "WS001",
        "Participants will learn Python basics, data analysis with pandas, and data visualization using matplotlib and seaborn."
      )
    );

    eventManager.add(
      new Workshop(
      "Agile Project Management",
      "Interactive workshop on agile methodologies and project management tools.",
      "Room 202",
      Utils.stringToDate("25/09/2024"),
      25,
      Modality.ONLINE,
      120,
      "WS002",
      "Participants will explore Scrum, Kanban, and agile tools through hands-on activities to improve team collaboration and project delivery."
      )
    );

    eventManager.add(
      new Workshop(
        "DevOps Best Practices",
        "Workshop focused on modern DevOps tools, CI/CD pipelines, and automation.",
        "Lab 5",
        Utils.stringToDate("27/09/2024"),
        20,
        Modality.HYBRID,
        90,
        "WS003",
        "Participants will set up CI/CD pipelines, explore containerization with Docker, and learn about infrastructure as code using tools like Terraform."
      )
    );

    // Short Courses
    eventManager.add(
      new ShortCourse(
      "Introduction to Cybersecurity",
      "Short course covering the fundamentals of cybersecurity.",
      "Room 303",
      Utils.stringToDate("28/09/2024"),
      40,
      Modality.HYBRID,
      4,
      "SC001",
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
      Utils.stringToDate("02/10/2024"),
      35,
      Modality.INPERSON,
      3,
      "SC002",
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
        Utils.stringToDate("05/10/2024"),
        25,
        Modality.ONLINE,
        5,
        "SC003",
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
        Utils.stringToDate("10/10/2024"),
        200,
        Modality.INPERSON,
        20,
        "AF001",
        50,
        "Innovation and Technology",
        Utils.stringToDate("12/10/2024")
      )
    );

    eventManager.add(
      new AcademicFair(
      "Data Science Expo",
      "Exhibition of data science projects and research.",
      "Exhibition Center",
      Utils.stringToDate("15/10/2024"),
      150,
      Modality.HYBRID,
      22,
      "AF002",
      40,
      "Data Science and Analytics",
      Utils.stringToDate("17/10/2024")
      )
    );

    eventManager.add(
      new AcademicFair(
      "GreenTech Academic Fair",
      "A fair focused on sustainable technology and green innovations.",
      "Eco Hall",
      Utils.stringToDate("20/10/2024"),
      120,
      Modality.INPERSON,
      15,
      "AF003",
      30,
      "Sustainable Technology",
      Utils.stringToDate("22/10/2024")
      )
    );

    // Get participants by CPF for clarity
    Student maryP = (Student) participantManager.get("400.231.804-47");
    Student joshP = (Student) participantManager.get("704.524.296-41");
    Student anaP = (Student) participantManager.get("592.201.489-78");
    Professor robertP = (Professor) participantManager.get("393.910.582-13");
    Professor patriciaP = (Professor) participantManager.get("072.608.242-20");
    Professor fernandoP = (Professor) participantManager.get("434.866.551-67");
    External lisaP = (External) participantManager.get("045.464.455-80");
    External eduardoP = (External) participantManager.get("864.726.822-91");

    // Get events by code for clarity
    Lecture aiLecture = (Lecture) eventManager.get("LEC001");
    Lecture cloudLecture = (Lecture) eventManager.get("LEC003");
    Workshop devopsWorkshop = (Workshop) eventManager.get("WS003");
    ShortCourse cyberCourse = (ShortCourse) eventManager.get("SC001");
    ShortCourse reactCourse = (ShortCourse) eventManager.get("SC002");
    AcademicFair techFair = (AcademicFair) eventManager.get("AF001");
    AcademicFair greenFair = (AcademicFair) eventManager.get("AF003");

    // Assign participants to events
    aiLecture.addParticipant(maryP, Modality.ONLINE);
    aiLecture.addParticipant(robertP, Modality.ONLINE);
    aiLecture.addParticipant(lisaP, Modality.ONLINE);
    aiLecture.generateCertificate(maryP.getCpf());
    aiLecture.generateCertificate(robertP.getCpf());
    participantManager.update(maryP.getCpf(), maryP);
    participantManager.update(robertP.getCpf(), robertP);
    eventManager.update(aiLecture.getCode(), aiLecture);

    cloudLecture.addParticipant(joshP, Modality.INPERSON);
    cloudLecture.addParticipant(anaP, Modality.INPERSON);
    cloudLecture.generateCertificate(anaP.getCpf());
    participantManager.update(anaP.getCpf(), anaP);
    eventManager.update(cloudLecture.getCode(), cloudLecture);

    devopsWorkshop.addParticipant(anaP, Modality.ONLINE);
    devopsWorkshop.addParticipant(lisaP, Modality.INPERSON);
    devopsWorkshop.addParticipant(joshP, Modality.INPERSON);
    devopsWorkshop.generateCertificate(lisaP.getCpf());
    devopsWorkshop.generateCertificate(joshP.getCpf());
    participantManager.update(lisaP.getCpf(), lisaP);
    participantManager.update(joshP.getCpf(), joshP);
    eventManager.update(devopsWorkshop.getCode(), devopsWorkshop);

    cyberCourse.addParticipant(anaP, Modality.ONLINE);
    eventManager.update(cyberCourse.getCode(), cyberCourse);
 
    reactCourse.addParticipant(maryP, Modality.INPERSON);
    reactCourse.addParticipant(joshP, Modality.INPERSON);
    reactCourse.generateCertificate(maryP.getCpf());
    reactCourse.generateCertificate(joshP.getCpf());
    participantManager.update(maryP.getCpf(), maryP);
    participantManager.update(joshP.getCpf(), joshP);
    eventManager.update(reactCourse.getCode(), reactCourse);

    techFair.addParticipant(lisaP, Modality.INPERSON);
    techFair.addParticipant(eduardoP, Modality.INPERSON);
    eventManager.update(techFair.getCode(), techFair);

    greenFair.addParticipant(patriciaP, Modality.INPERSON);
    greenFair.addParticipant(fernandoP, Modality.INPERSON);
    greenFair.generateCertificate(patriciaP.getCpf());
    participantManager.update(patriciaP.getCpf(), patriciaP);
    eventManager.update(greenFair.getCode(), greenFair);

    System.out.println(Lines.clear());
    System.out.println(Lines.successLine("Dummy Data saved (" + participantManager.getAll().size() + " participants and " + eventManager.getAll().size() + " events)!"));
      
  }
}
