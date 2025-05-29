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

4. Custom Exceptions & Validation

   - Replace catch(Exception e){ println errorLine(e.getMessage()); } with domain-specific exceptions (e.g. CapacityExceededException) so you can fail fast from any service layer.
   - Put input-validation (e.g. “capacity must be ≥0”) into your model constructors or builders—not only in the UI layer.

5. Write Focused Unit Tests
   - Move all business logic (e.g. Event.addParticipant, Participant.clearCertificates) out of menu controllers into services, so you can cover them with JUnit—menu I/O tests can be minimal “smoke” tests.
