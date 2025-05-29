# Refactor

1. Centralize JSON Configuration

   - Right now you configure Gson in two places (EventRepository & ParticipantRepository). Extract that into a JsonFactory (or Spring-style Config class) so all repos share the same GsonBuilder registrations (type-adapters, RuntimeTypeAdapterFactory, date formats).

2. Eliminate Scanner Leaks & Duplication

   - Move your single new Scanner(System.in) into one ConsoleUtil class (static, never closed), and centralize all menuResponse, getText(), getNumber() calls there to avoid copy/pasted I/O logic.

3. DRY Up Menu Controllers

   - All your \*MenuController classes do the same “prompt, cancel check, switch‐on‐type, call subcontroller” pattern.
   - Pull a small framework:

   ```java
      abstract class BaseMenuController {
      protected <R> R promptForm(...) { /* common cancel logic */ }
      protected void runLoop(List<String> options, Consumer<String> handler) { /* call handler(option) */ }
      }
   ```

   - Then each subclass only provides the options list and a lambda to handle each choice.

4. Improve ReportsGenerator with a Builder

   - Replace repeated

   ```java
   sb.append(Lines.doubleLine()).append("\n");
   sb.append(Lines.titleLine(...)).append("\n");
   ```

   with tiny helpers:

   ```java
   private static String NL = System.lineSeparator();

   private static void divider(sb){ sb.append(Lines.doubleLine()).append(NL); }
   private static void title(sb,t,c){ sb.append(Lines.titleLine(t,c)).append(NL); }
   ```

   - Or even a fluent `ReportBuilder` so you write:

   ```java
   ReportBuilder.of(title)
     .section("Event: "+e.getType(), YELLOW)
     .text(e.toString())
     .if(detailed, b -> b.section("Participants", BLUE).forEach(pList, p->b.text(p.toString())))
     .build();
   ```

5. Leverage Java 8 Streams & Collectors

   - Anywhere you do a for + if + add to a list or map, switch to:

   ```java
      List<Event> upcoming = events.values().stream()
       .filter(e->e.getDate().isAfter(today))
       .sorted(comparing(Event::getDate))
       .collect(toList());
   ```

6. Extract Common Date/Time Logic
   -You parse dates everywhere with LocalDate.parse(str, LocalDateAdapter.DATE_FORMATTER). Wrap that in a DateUtil.parse(str) that handles “cancel” tokens and invalid input uniformly.

7. Custom Exceptions & Validation

   - Replace catch(Exception e){ println errorLine(e.getMessage()); } with domain-specific exceptions (e.g. CapacityExceededException) so you can fail fast from any service layer.
   - Put input-validation (e.g. “capacity must be ≥0”) into your model constructors or builders—not only in the UI layer.

8. Write Focused Unit Tests
   - Move all business logic (e.g. Event.addParticipant, Participant.clearCertificates) out of menu controllers into services, so you can cover them with JUnit—menu I/O tests can be minimal “smoke” tests.
