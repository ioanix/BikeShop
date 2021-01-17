package ro.ubb.lab6.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.lab6.client.ui.Console;


public class ClientApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.lab6.client.config");

        Console console = context.getBean(Console.class);
        console.runConsole();

        System.out.println("bye ");
    }
}
