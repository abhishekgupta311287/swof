# Support Wheel of Fate
This project serves two purpose
- Expose API to deal with the list of engineers and to generate a shift schedule
- Create a very simple UI/presentation layer

#### Assumptions
- Schedule needs to be generated from present working day. 
- If present day is a non working day, then it generates schedule from the next upcoming working day.
- Schedule is generated for two weeks for 10 engineers.
- Schedule is generated with 2 shifts per day.
- Schedule is generated with at most 1 half day shift and at least 1 full day support per engineer in two weeks. 
- Workings days are considered from Monday to Friday.

##   UI/Presentation Layer
- https://abhishekgupta311287.github.io/swof/
or
- http://swof.us-east-2.elasticbeanstalk.com/

##   API

- http://swof.us-east-2.elasticbeanstalk.com/api/schedule
    - Returns a day wise shift schedule to be consumed by the front end client.

- http://swof.us-east-2.elasticbeanstalk.com/api/shifts/{ShiftsPerPeriod}/{ShiftsPerEngineerPerPeriod}
    - Generates a shift with the given shifts per period and shifts per engineer per period.

- http://swof.us-east-2.elasticbeanstalk.com/api/engineers
    - Returns the list of predefined engineers

- http://swof.us-east-2.elasticbeanstalk.com/api/engineer/{id}
    - Returns an engineer with the given id

- http://swof.us-east-2.elasticbeanstalk.com/api/add/engineer/{name}
    - Adds an engineer, with the given name, to the list

- http://swof.us-east-2.elasticbeanstalk.com/api/delete/engineer/{id}
    - Deletes an engineer, with the given id, from the list

## Architecture
The approach followed provides maximum flexibility in terms of new rules additions with minimal code addition/changes

- ### EngineerController
    - Exposes apis to deal with engineer list

- ### ScheduleController
    - Exposes schedule generation api

- ### ViewController
    - Exposes the presentation layer

- ### IRule
    - Determines and applies the rule to validate the generated shift

- ### IRuleEvaluator
    - Evaluates the shift against gives set of rules

- ### IScheduleGenerator
    - This generates the schedule as per the defined set of rules

- ### IEngineerPool
   -  This provides functionality to deal with the list of engineers

- ### IEngineerFactory
   - Creates a list of the engineer depending on number of shifts per engineer per period

### Springboot
  -  To ease the Java-based applications Development, Unit Test and Integration Test Process.
  -  To reduce Development, Unit Test and Integration Test time by providing some defaults.
  -  To increase Productivity.

### Gradle
  - Gradle’s build scripts are more readable, expressive and declarative.
  - Gradle’s build scripts are written in simple Groovy, no XML. That means it use it’s own DSL based on Groovy script.
  - It’s highly scalable for complex multi-module projects.
  - Unlike Maven’s pom.xml, No need to write boilerplate XML Code
  - It provides plugins for most of the IDEs like Eclipse, IntelliJ IDEA, Spring STS Suite etc.
  - It is very easy to maintain even for multi-module complex projects.


##  Project Structure
### package - com.swof

- ### SwofApplication
    - main entry point for spring boot applications

- ### controller
    - contains all the controllers that exposes apis

- ### interfaces
    - contains all the interfaces

- ### impl
    - contains the implementation of interfaces

- ### model
    - contains the model classes

- ### utils
    - contains the utility and constant classes

### Predefined Constants
- SHIFT_DAYS = 10
- SHIFTS_PER_ENGINEER_PER_PERIOD = 2
- SHIFT_PER_PERIOD = 20

### Web Server
- AWS Elasticbeanstalk for hosting the server



