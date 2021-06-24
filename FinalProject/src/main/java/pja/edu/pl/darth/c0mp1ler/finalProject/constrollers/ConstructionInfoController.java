package pja.edu.pl.darth.c0mp1ler.finalProject.constrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Building;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Construction;
import pja.edu.pl.darth.c0mp1ler.finalProject.services.*;
import pja.edu.pl.darth.c0mp1ler.finalProject.view.ConstructionInformationView;

import javax.swing.*;
import java.util.List;

/**
 * ConstructionInfoController is used to provide ConstructionInformationView components
 * with the required information in order to show it on the GUI.
 * @see ConstructionInformationView
 */
@Controller
@RequiredArgsConstructor
public class ConstructionInfoController {

    private final ConstructionInformationView constructionInformationView;

    private final RulerService rulerService;
    private final GovernorService governorService;
    private final BuildingService buildingService;

    private MainWindowController mainWindowController;

    /**
     * ShowUI is a method for ConstructionInfoController to get the required construction and a
     * reference to MainWindowController in order to invoke the display of this view
     * @param construction the selected construction to show information
     * @param mainWindowController reference to MainWindowController that displays all the panels on main frame
     *
     * @see MainWindowController
     */
    public void showUI(Construction construction, MainWindowController mainWindowController){
        if(this.mainWindowController == null) {
            this.mainWindowController = mainWindowController;
        }if(construction != null) {
            ShowInfo(construction);
            ShowListOfBuildings(construction);
            this.mainWindowController.showContent(constructionInformationView.getPanel());
        }
    }

    private void ShowListOfBuildings(Construction construction){
        List<Building> buildings = buildingService.getBuildingsInConstruction(construction);;
        DefaultListModel<Building> model = constructionInformationView.getBuildingListModel();
        model.removeAllElements();
        buildings.forEach(model :: addElement);
    }

    private void ShowInfo(Construction construction){
        String res = construction.getName() + " of " +
                construction.getRegion().getName() + " of " +
                construction.getRegion().getKingdom().getName() + "\n" +
                "Ruled by " + rulerService.getRulerByKingdom(construction.getRegion().getKingdom()).get(0).getName() + "\n" +
                "Governed by " + governorService.getGovernorByRegion(construction.getRegion()).get(0).getName() + "\n" +
                construction.displayInfo();

        constructionInformationView.getConstructionInfo().setText(res);
    }

}
