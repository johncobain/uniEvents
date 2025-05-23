package br.edu.ifba.inf0008.uniEvents;

import br.edu.ifba.inf0008.uniEvents.menu.MainMenu;
import br.edu.ifba.inf0008.uniEvents.menu.menuInterface.IMenu;
import br.edu.ifba.inf0008.uniEvents.repository.EventRepository;
import br.edu.ifba.inf0008.uniEvents.repository.ParticipantRepository;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.FileGenerator;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.services.ReportsManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        // Initialize repositories
        ParticipantRepository participantRepository = new ParticipantRepository();
        EventRepository eventRepository = new EventRepository(participantRepository);

        // Initialize services
        ParticipantManager participantManager = new ParticipantManager(participantRepository);
        EventManager eventManager = new EventManager(eventRepository, participantRepository);
        ReportsManager reportsManager = new ReportsManager();
        FileGenerator fileGenerator = new FileGenerator();

        // // Example of creating an event
        // ArrayList<Event> events = new ArrayList<>();

        // events.add(new Workshop("Workshop de Java", "Aprenda Java do zero", "Auditório 1", LocalDate.of(2023, 10, 15), 50, Modality.INPERSON, "W001"));

        // events.add(new Lecture("Palestra de Python", "Aprenda Python do zero", "Auditório 2", LocalDate.of(2023, 10, 20), 100, Modality.ONLINE, "L001"));

        // eventManager.addEvent(events.get(0));
        // eventManager.addEvent(events.get(1));

        // // Example of creating a participant
        // ArrayList<Participant> participants = new ArrayList<>();

        // participants.add(new Student("John Doe", "123.456.789-00", "jdoe@me.com", "(11) 1234-5678", LocalDate.of(1990, 5, 15), "Male"));

        // participants.add(new Teacher("Jane Doe", "987.654.321-00", "jane@me.com", "(11) 9876-5432", LocalDate.of(1980, 12, 25), "Female"));

        // participants.add(new Student("Alice Smith", "111.222.333-44", "alice@me.com", "(11) 1111-2222", LocalDate.of(1995, 8, 10), "Female"));

        // participantManager.addParticipant(participants.get(0));
        // eventManager.addParticipantToEvent(events.get(0).getCode(), participants.get(0).getCpf());

        // participantManager.addParticipant(participants.get(1));
        // participantManager.addParticipant(participants.get(2));
        // eventManager.addParticipantToEvent(events.get(1).getCode(), participants.get(1).getCpf());
        // eventManager.addParticipantToEvent(events.get(1).getCode(), participants.get(2).getCpf());

        // Initialize menu
        System.out.println(Lines.mixedLines());
        System.out.println(Lines.titleLine("Welcome to UniEvents!", Colors.GREEN_BOLD));
        System.out.println(Lines.mixedLines());

        IMenu menu = new MainMenu(eventManager, participantManager, reportsManager, fileGenerator);
        menu.show();
        
        System.out.println(Lines.mixedLines());
        System.out.println(Lines.titleLine("Exiting UniEvents...", Colors.GREEN_BOLD));
        System.out.println(Lines.mixedLines());


        // Example of generating a report
        String detailedReport = reportsManager.generateReport(eventManager.getAllEvents(), "Detailed Report", true);
        System.out.println(detailedReport);
        System.out.println(Lines.mixedLines());
        String summaryReport = reportsManager.generateReport(eventManager.getAllEvents(), "Summary Report", false);
        System.out.println(summaryReport);
    }
}
