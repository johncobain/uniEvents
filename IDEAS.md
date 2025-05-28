# IDEAS

- refactor EVERYTHING

- TODO: REFACTOR EVENTMANAGER AND EVENTMENUCONTROLLER

- TODO: Maybe add `addToEvent` method in interface or something like that

- give some behaviour changes depending on modality - in-person: ask for capacity and add to it when registering - online: no capacity, no limits - hybrid: ask for capacity and add to it when registering only if the participant is registering for the in-person part

- find a way to make the code more readable

- Press Enter to exit views

- TODO: student must have a required gpa for course

- TODO: advanced workshop must have a required gpa for Student

1. Generate Certificates (text)
   Relevant code:

   - `src/main/java/br/edu/ifba/inf0008/uniEvents/model/events/Certificate.java` (empty class) -`src/main/java/br/edu/ifba/inf0008/uniEvents/model/participants/Participant.java` (has `ArrayList<Certificate>` certificates, methods to add/get certificates, but no logic for generating certificate text or files)
   - `src/main/java/br/edu/ifba/inf0008/uniEvents/services/FileGenerator.java` (only a TODO comment)
     HELP.txt (describes certificate generation as a required action)
     No code found that generates or saves certificate text files.

   Missing:

   - No implementation for generating certificate text or saving certificates as files.

2. Generate Reports by Event Type and Date
   Relevant code:

   - `src/main/java/br/edu/ifba/inf0008/uniEvents/services/ReportsManager.java` (has a generateReport method that builds a report string for a list of events, but no code found for filtering by event type or date)
   - No UI/menu code found that lets the user select type/date for reports.

   Missing:

   - No filtering or report generation by event type or date in the UI or service layer.

3. Add support for online, in-person and hybrid events
   Relevant code:

   - No code found for event modality (online, in-person, hybrid) in event models or forms.
   - No logic for handling different modalities in event creation or display.

   Missing:

   - No modality attribute or logic in event classes or event creation.

4. Use dynamic linking (runtime polymorphism)
   Relevant code:

   - There is an event and participant class hierarchy (abstract classes, inheritance).
   - No code found that demonstrates dynamic method dispatch (e.g., calling overridden methods via a superclass reference in a polymorphic context).

   Missing:

   - No explicit demonstration of dynamic linking/polymorphism in use (e.g., iterating over a collection of Event or Participant and calling overridden methods).

5. Method overloading
   Relevant code:

   - No overloaded methods found in the codebase (methods with the same name but different parameter lists).

   Missing:

   - No method overloading implemented.

6. Utilize a collection more appropriate for each situation
   Relevant code:

   - LinkedHashMap is used for participants and events.
   - No evidence of using other collection types (e.g., HashSet, TreeMap, etc.) where appropriate.

   Missing:

   -No demonstration of choosing different collection types for different needs.

7. Demonstrate safe casting and correct usage of hierarchy collections
   Relevant code:

   - No code found that demonstrates safe casting or use of collections with superclass references holding subclass objects.

   Missing:

   - No explicit demonstration of safe casting or hierarchy collection usage.
