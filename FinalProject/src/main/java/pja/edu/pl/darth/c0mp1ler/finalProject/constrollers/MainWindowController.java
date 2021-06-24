package pja.edu.pl.darth.c0mp1ler.finalProject.constrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import pja.edu.pl.darth.c0mp1ler.finalProject.view.MainWindowView;

import javax.annotation.PostConstruct;
import javax.swing.*;

/**
 * MainWindowController controls what is the content of the MainWindowView
 * @see MainWindowView
 */
@Controller
@RequiredArgsConstructor
public class MainWindowController {

    private final MainWindowView mainWindowView;
    private final ConstructionListController constructionListController;

    /**
     * Sets the main frame to visible, showing it.
     */
    public void showUI() {
        mainWindowView.setVisible(true);
    }

    @PostConstruct
    private void initMenuListeners(){
        System.out.println("creating event listeners");
        mainWindowView.getShowConstructionInfoMenu().addActionListener(
                e -> {
                    constructionListController.showUI(this);
                }
        );
    }

    /**
     * shows the jpanel, provided as a parameter as a content pane of the displayed frame
     * @param content content to be displayed
     */
    public void showContent(JPanel content){
        mainWindowView.getContentPane().removeAll();
        mainWindowView.getContentPane().add(content);
        mainWindowView.setVisible(true);
        mainWindowView.revalidate();

        mainWindowView.setSize(1027,767);
        mainWindowView.setSize(1028,768);
    }


}
