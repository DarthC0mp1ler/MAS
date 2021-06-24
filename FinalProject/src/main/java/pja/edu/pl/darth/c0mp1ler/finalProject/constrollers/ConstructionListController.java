package pja.edu.pl.darth.c0mp1ler.finalProject.constrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Construction;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Kingdom;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.RegionImpl;
import pja.edu.pl.darth.c0mp1ler.finalProject.services.ConstructionService;
import pja.edu.pl.darth.c0mp1ler.finalProject.services.KingdomService;
import pja.edu.pl.darth.c0mp1ler.finalProject.services.RegionService;
import pja.edu.pl.darth.c0mp1ler.finalProject.view.ConstructionListView;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.List;

/**
 * ConstructionListController is used to provide ConstructionListView with
 * the required information in order to show constructions on the GUI
 *
 * @see  ConstructionListView
 */
@Controller
@RequiredArgsConstructor
public class ConstructionListController {

    private final ConstructionListView constructionListView;

    private final ConstructionInfoController constructionInfoController;
    private final ConstructionService constructionService;
    private final KingdomService kingdomService;
    private final RegionService regionService;

    private MainWindowController mainWindowController;

    /**
     * populates the ConstructionList panel with the information
     * and passes the reference to it to the MainWindowController
     * @param mainWindowController reference to MainWindowController that displays all the panels on main frame
     *
     * @see MainWindowController
     */
    public void showUI(MainWindowController mainWindowController){
        if(this.mainWindowController == null) {
            this.mainWindowController = mainWindowController;
        }
        showAllConstructions();
        showKingdomFilter();
        showRegionFilter();
        this.mainWindowController.showContent(constructionListView.getPanel());
    }

    @PostConstruct
    private void initListeners(){
        constructionListView.getConstructionList().addListSelectionListener(e-> {
            constructionInfoController.showUI(constructionListView.getConstructionList().getSelectedValue(),mainWindowController);
        });
        constructionListView.getKingdomsFilter().addItemListener( e-> {
            showRegionFilter();
            showAllConstructions();
        });
        constructionListView.getRegionsFilter().addItemListener( e-> {
            showAllConstructions();
        });
    }

    private void showKingdomFilter(){
        List<Kingdom> kingdoms = kingdomService.getAllKingdoms();
        constructionListView.getKingdomsFilter().removeAllItems();
        for (Kingdom k: kingdoms) {
            constructionListView.getKingdomsFilter().addItem(k);
        }
        constructionListView.getKingdomsFilter().setSelectedItem(null);
    }

    private void showRegionFilter(){
        List<RegionImpl> regions = regionService.getRegionsByKingdom((Kingdom) constructionListView.getKingdomsFilter().getSelectedItem());
        constructionListView.getRegionsFilter().removeAllItems();
        for (RegionImpl r: regions){
            constructionListView.getRegionsFilter().addItem(r);
        }
        constructionListView.getRegionsFilter().setSelectedItem(null);
    }

    private void showAllConstructions(){
        List<Construction> constructions = constructionService.getAllConstructions(
                (Kingdom) constructionListView.getKingdomsFilter().getSelectedItem(),
                (RegionImpl) constructionListView.getRegionsFilter().getSelectedItem()
        );
        DefaultListModel<Construction> model = constructionListView.getConstructionListModel();
        model.removeAllElements();
        constructions.forEach(model :: addElement);
    }

}
