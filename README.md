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

### Functional Requirements

- [x] Create Events (Lectures, Workshops, Short Courses, Academic Fairs)
- [x] Create Participants (Students, Professors, Externals)
- [x] Register Participants in Events (with capacity control)
  - Courses can only have students as participants
- [ ] Generate Certificates (text)
- [ ] Generate Reports by Event Type and Date
- [ ] Add support for online, in-person and hybrid events

### Technical Requirements

- Basic Concepts

  - [x] Classes, Objects and References
  - [x] Attributes, Methods and Visibility (private, protected, public)
  - [x] Packages Organized Logically
  - [x] JAR Files and Classpath Usage
  - [x] Constructor Methods

- Inheritance and Polymorphism

  - [x] Create an hierarchy of classes for events and other entities
  - [x] Use interfaces or abstract classes to define generic behavior
  - [ ] Use dynamic linking

- Collections

  - [ ] Utilize a collection more appropriate for each situation
  - [ ] Demonstrate safe casting and correct usage of hierarchy collections

- Other Requirements
  - [x] Attributes and methods static
  - [ ] Method overloading
  - [x] Structured project with at least one external library
  - [x] Command Line Interface (not necessary graphical interface)

## Library

- [Gson](https://central.sonatype.com/artifact/com.google.code.gson/gson)

## Build Tool

- [Maven](https://maven.apache.org/)

## Steps to run the project

1. If you are on GitHub, clone the repository:

   ```bash
   git clone https://github.com/johncobain/uniEvents.git
   ```

   - Then, navigate to the project directory:

   ```bash
   cd uniEvents
   ```

2. If you have the project on a tar.gz file, extract it:

   ```bash
   tar -xvzf uniEvents.tar.gz
   ```

   - Then, navigate to the project directory:

   ```bash
   cd uniEvents
   ```

3. Run the project:

   - Inside the project directory, run the following command to compile and run the project:

   ```bash
   mvn package
   ```

   ```bash
   mvn exec:java
   ```
