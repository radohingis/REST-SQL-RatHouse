package sk.kosickaakademia.hingis.rat_house.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "sk.kosickaakademia.hingis.rat_house.controller")
public class App 
{
    public static void main( String[] args ) {

        SpringApplication.run(App.class, args);

    }
}
