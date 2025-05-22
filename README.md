# UniEvents

UniEvents is a system created to simulate the management of Events in a University focused in the study of Object-Oriented Programming (OOP) concepts like inheritance, interfaces, overloading, polymorphism, collections and organization of modules.

## Description

The UniEvents creates academic events, including:

- Lectures
- Workshops
- Short Courses
- Academic Fairs

Each event has specific features, but they all share common elements, such as title, date, location, capacity of participants and description.

The project simulates the management of events in a university, with the following requirements:

## Library

- [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson)

## Steps to run the project

```bash
mvn package
```

```bash
mvn exec:java
```

3. Requisitos Funcionais
   O sistema deve permitir:
4. Cadastro de eventos de diferentes tipos (palestras, cursos, workshops, feiras).
5. Associação de participantes a eventos (com controle de vagas disponíveis).
6. Geração de certificados (texto) com dados do evento e do participante.
7. Participantes podem ser alunos, professores ou externos, com dados específicos para cada
   categoria. Os participantes dos cursos devem ser exclusivamente alunos (não podem ser
   professores ou externos).
8. Suporte a eventos híbridos (presenciais ou online), com comportamentos diferentes para o
   processo de inscrição.
9. Relatório de eventos por tipo e data.
