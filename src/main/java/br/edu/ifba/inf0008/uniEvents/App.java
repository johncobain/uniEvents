package br.edu.ifba.inf0008.uniEvents;

import java.time.LocalDate;
import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.menu.MainMenu;
import br.edu.ifba.inf0008.uniEvents.menu.menuInterface.IMenu;
import br.edu.ifba.inf0008.uniEvents.model.events.Event;
import br.edu.ifba.inf0008.uniEvents.model.events.Modality;
import br.edu.ifba.inf0008.uniEvents.model.events.Workshop;
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

        EventRepository eventRepository = new EventRepository();
        ParticipantRepository participantRepository = new ParticipantRepository();

        EventManager eventManager = new EventManager(eventRepository, participantRepository);
        ParticipantManager participantManager = new ParticipantManager(participantRepository);
        ReportsManager reportsManager = new ReportsManager();
        FileGenerator fileGenerator = new FileGenerator();

        System.out.println(Lines.mixedLines());
        System.out.println(Lines.titleLine("Welcome to UniEvents!", Colors.GREEN_BOLD));
        System.out.println(Lines.mixedLines());

        IMenu menu = new MainMenu(eventManager, participantManager, reportsManager, fileGenerator);
        menu.show();
        
        System.out.println(Lines.mixedLines());
        System.out.println(Lines.titleLine("Exiting UniEvents...", Colors.GREEN_BOLD));
        System.out.println(Lines.mixedLines());

        ArrayList<Event> events = new ArrayList<>();
        Event event = new Workshop("Workshop de Java", "Aprenda Java do zero", "Auditório 1", LocalDate.of(2023, 10, 20), 50, Modality.INPERSON, "123456");

        events.add(event);

        System.out.println(reportsManager.generateReport(events, "testing"));
    }
}
