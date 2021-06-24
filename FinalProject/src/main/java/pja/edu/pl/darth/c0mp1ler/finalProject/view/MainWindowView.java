package pja.edu.pl.darth.c0mp1ler.finalProject.view;

import lombok.Data;
import org.springframework.stereotype.Controller;

import javax.swing.*;

/**
 * Class representing configuration for the main frame of application
 */
@Data
@Controller
public class MainWindowView extends JFrame {
    private JMenuItem showConstructionInfoMenu;

    /**
     * constructor
     */
    public MainWindowView(){
        setTitle("Fantasy world management application");
        setSize(1028,768);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createMenuBar();
    }

    private void createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Map Components");
        menuBar.add(menu);
        showConstructionInfoMenu = new JMenuItem("Show construction info");
        menu.add(showConstructionInfoMenu);
        this.setJMenuBar(menuBar);
    }
}
