# Task Tracker CLI

The Task Tracker CLI is a command-line program meant for creating, updating, completing and deleting your tasks.
The tasks are stored locally in a "file.json" file, so that data persists throughout multiple sessions.

## Features

- Add new tasks
- Update task descriptions
- Mark tasks as todo, in progress or done
- Delete tasks
- List all tasks
- Filter tasks by status
- Persistent storage with JSON

## Tech Stack

- Java (JDK 26)
- Maven
- Jackson Databind for JSON serialization/deserialization
- Jackson JSR310 module for Java Time API Support

## Dependencies

This project uses:

- `jackson-databind` for reading and writing task data as JSON
- `jackson-datatype-jsr310` for serializing Java date/time classes such as `LocalDateTime`

## Installation

### Prerequisites

- Java (JDK 26)
- Maven

### Clone the repository

```bash
git clone https://github.com/noctraX/task-tracker.git
cd task-tracker
```

## Running the project

### METHOD 1
```bash
mvn exec:java
```

### METHOD 2
You can also use the generated JAR file:
Jar allows you to execute the program without installing Maven, downloading dependecies manually
or understanding my project structure.

#### Build the Project

```bash
mvn package
```
This command compiles the project, runs tests and creates a JAR in target/

####  Run the generated jar

```bash
java -jar target/task-tracker.jar
```

## Design Decisions

- JSON was chosen for storage to keep the project lightweight and easy to run
- Jackson was used to simplify serialization and deserialization
- Maven manages dependencies and project builds
- Java Time API is used for safer date handling

### Data Persistence JSON And OOP

Data persists throughout multiple sessions as they are saved inside json files. 
Data persistence is modeled using a dedicated Task class with defined fields, constructors, getters and setters.
When new data needs to be saved, stored in memory tasks are converted to a list of Task objects (Deserialization).
After that the task can be updated, deleted or the user can add new tasks. 

After modifications are made, the updated list of Task objects is serialized back into JSON and saved to the file.

## Usage

Add a task:

```bash
mvn exec:java -Dexec.args='add "Buy groceries"'
```

List tasks:
```bash
mvn exec:java -Dexec.args="list"
```

Mark as done:
```bash
mvn exec:java -Dexec.args="mark-done 64656"
```

## Project Structure

```text
.idea/
src/
├── main/
│   ├── java/
│   └── resources/
│   └── com.projects/
│   └────── Main.java
│   └────── Commands.java
│   └────── TaskStatus.java
│   └────── Tasks.java
target/
pom.xml
file.json
README.md
```

## Project Inspiration

This project was built as part of the Task Tracker project from roadmap.sh.

https://roadmap.sh/projects/task-tracker

## License

This project is licensed under the MIT License.
