# Group Formation
This project is developed as part of Advance Topics in Software Development course at Dalhousie University. It uses the best coding practices, which makes the code clean and easy to modify, incase more functionalities are added to the project.

Course Work: Advanced Topics in Software Development
Programming Language: Java
Framework: Spring Boot
Database: MySQL

## Features
1. Admin can assign multiple role to users (Student, Teaching Assistant, Course Instrucor).
2. Admin can assign user Course Instructor, who can assign Teaaching Assistant for their course.
3. Admin can create a new course or delete the course.

## Code structure
* <a href='https://github.com/ruminder-hub/group_formation/tree/master/src/main'>[main] </a> package: It contains the source code of the application.
* <a href='https://github.com/ruminder-hub/group_formation/tree/master/src/test'>[test] </a> package: It consists of test cases of source code.

## Architecture
The application follows the MVC pattern. All logic is written in Model layer.

## Implementation
### S.O.L.I.D Principles and Design Pattern
* No use of hardcoded values.
* Every class have responsibility over a single part of the functionality provided by the application.
* Classes are programmed to interface to reduce coupling among modules.
* Code structure is based on functionality instead of layer, so that one package is responsible for providing a specific functionality.
* Creational design pattern is used to instantiate classes. To instantiate classes, abstract factories are used, whose implementation supplied concrete factories for using business logic.
* Singleton pattern is used to create one object of classes which interact with database.

### Test Driver Development (TDD)
* For developing high quality and robust system that never fails, TDD approach is followed. 
* Mock objects are used for providing functionality of database access and JUnit class is used for testing.
* All business logic is covered by the test cases.
* Test code adheres to the same clean code standards as production code.

### Logging and Exception Handling
* Logging of all the important events are done, which gets saved to file.
* Specific format of logging is setup to record date, time, file and data.

### Clean Code
* Consistent indentation
* Language (standards) idioms
* Preferred polymorphism to conditions
* No negative conditionals
* No comment noise
* Resource clean up after usage



