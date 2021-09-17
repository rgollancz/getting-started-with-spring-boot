# Lab Exercise 1

_NB this exercise was written to be used with Visual Studio Code. You may have to adapt it for use with other IDEs_

**Open VS Code & add extensions**
  - Click on the ‘Extensions’ option in the left-hand menu
  - Search for ‘java’
  - Select the following extensions and the click the ‘install’ button for each:
    - Extension Pack for Java
    - Spring Initializr Java Support
 
**Generate your project**
  - From the ‘Java Overview’ screen’s ‘Start’ menu choose ‘Create a Spring Boot Project’
  - Select ‘Gradle project’ and enter the following options:
    - 2.5.4
    - Java
  - Hit enter to accept the default group ID
  - Hit enter to accept the default name
  - JAR
  - Java 11
  - Search for and add the following dependencies:
    - Spring Web
    - Spring Data JPA
    - H2 Database SQL
  - Hit ‘enter’ and choose ‘Desktop’ to save your project
  - Click ‘Generate into this folder’
  - Click ‘open’ to start using your generated project
  - Wait until you see a pop-up saying “The workspace contains Java projects, would you like to import them?” and click ‘Yes’
  - Wait for the project to finish importing - you can watch it’s progress by clicking on the spinning sync icon in the bottom bar
 
**Create your first endpoint**
  - Open your project's source code directory at src > main > java / com / example / demo
  - Add a new java file for your Controller e.g. HelloController.java
  - Create your HelloController class and add an endpoint for your web application at "/"
  - Return the string "Hello, World!" from your index method

```
Example:
@RestController
public class HelloController {

   @GetMapping("/")
   public String index() {
     return "Hello, World!";
    }
}
```

**Start your application and try it out**
  - Make sure your Hellocontroller.java file is saved
  - Open the terminal in VS Code and run `./gradlew bootRun`
  - Wait for your Spring application to start
  - Open a new terminal window and hit your new endpoint using: `curl localhost:8080`
  - You should see the string “Hello, World!” returned from your endpoint

