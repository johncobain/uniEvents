# IDEAS

- use dynamic linking for Participants

- Short Course must have a Teacher as teacher of the event

- Press Enter to exit views

- better iterator

```java
participantsMap.forEach((cpf, participant) -> {
    // Faça algo com cpf e participant
});
```

- better filtering

```java
List<Student> students = participantsMap.values().stream()  // 1. Pega um Stream dos valores
    .filter(p -> p instanceof Student)    // 2. Filtra: mantém apenas quem é Student
    // OU: .filter(Student.class::isInstance)
    .map(p -> (Student) p)                // 3. Mapeia/Transforma: faz o cast para Student
    // OU: .map(Student.class::cast)
    .collect(Collectors.toList());        // 4. Coleta: junta os resultados em uma nova List
```

- add `addInterest` to Participant menu (for Student)

- student must have a required gpa for course

- maybe another submenu for Participant menu?

- change Teacher to Professor
