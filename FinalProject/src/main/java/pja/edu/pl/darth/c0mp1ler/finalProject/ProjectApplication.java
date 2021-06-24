package pja.edu.pl.darth.c0mp1ler.finalProject;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import pja.edu.pl.darth.c0mp1ler.finalProject.constrollers.MainWindowController;

import javax.swing.*;

/**
 * Main class that starts the application
 */
@SpringBootApplication
public class ProjectApplication {

    /**
     * Runs the application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(ProjectApplication.class).headless(false)
                .run(args);
        SwingUtilities.invokeLater(()->{
            ctx.getBean(MainWindowController.class).showUI();
        });
    }

}
