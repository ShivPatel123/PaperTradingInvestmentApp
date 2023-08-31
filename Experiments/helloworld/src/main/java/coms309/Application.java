package coms309;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PetClinic Spring Boot Application.
 * 
 * @author Vivek Bengre
 */

@SpringBootApplication
public class Application {

    public static void starPyramid(int rows){
        for(int i = 1; i < rows + 1; ++i){
            for(int j = 0; j < i; ++j){
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
        System.out.println("Hello World");
        double dollas = 34;
        double quarters = dollas /.25;
        dollas -= quarters * 4;
        double dimes = dollas / .10;
        dollas -= dimes * 10;
        double nickels = dollas / .05;
        dollas -= nickels * 20;
        double pennies = dollas / .01;
        System.out.println("You have " + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels, and " + pennies + " pennies.");
        starPyramid(4);
    }

}
