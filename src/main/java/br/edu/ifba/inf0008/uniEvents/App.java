package br.edu.ifba.inf0008.uniEvents;

import br.edu.ifba.inf0008.uniEvents.menu.MainMenu;
import br.edu.ifba.inf0008.uniEvents.menu.menuInterface.IMenu;
import br.edu.ifba.inf0008.uniEvents.repository.EventRepository;
import br.edu.ifba.inf0008.uniEvents.repository.ParticipantRepository;
import br.edu.ifba.inf0008.uniEvents.services.EventManager;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Colors;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;

public class App {
    public static void main(String[] args) {
        System.out.println(Lines.clear());
        
        ParticipantRepository participantRepository = new ParticipantRepository();
        EventRepository eventRepository = new EventRepository(participantRepository);

        EventManager eventManager = new EventManager(eventRepository);
        ParticipantManager participantManager = new ParticipantManager(participantRepository, eventManager);
        
        System.out.println(Lines.mixedLines());
        System.out.println(Lines.titleLine("Welcome to UniEvents!", Colors.GREEN_BOLD));
        System.out.println(Lines.mixedLines());
        System.out.println(Lines.straightLine());
        System.out.println(Lines.multiLineText("UniEvents is a system designed to manage events and participants in a university setting. You can create, update, and delete events and participants, as well as generate reports."));
        System.out.println(Lines.straightLine());

        IMenu menu = new MainMenu(eventManager, participantManager);
        menu.show();
        
        System.out.println(Lines.mixedLines());
        System.out.println(Lines.titleLine("Exiting UniEvents...", Colors.GREEN_BOLD));
        System.out.println(Lines.mixedLines());
    }
}
