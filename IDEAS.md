# IDEAS

- TODO: refactor EVERYTHING

- TODO: REFACTOR EVENTMANAGER AND EVENTMENUCONTROLLER

- TODO: Maybe add `addToEvent` method in interface or something like that

- TODO: Custom Errors

- TODO: find a way to make the code more readable

- TODO: Press Enter to exit views

- TODO: student must have a required gpa for course

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
