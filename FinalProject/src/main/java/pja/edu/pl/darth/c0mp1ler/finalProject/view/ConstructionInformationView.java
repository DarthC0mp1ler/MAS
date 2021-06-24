package pja.edu.pl.darth.c0mp1ler.finalProject.view;

import lombok.Data;
import org.springframework.stereotype.Controller;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Building;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Construction;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Class representing configuration for the construction information
 */
@Controller
@Data
public class ConstructionInformationView {
    private JTextPane constructionInfo;
    private JButton addBuilding;
    private JButton removeBuilding;
    private JButton removeConstruction;
    private DefaultListModel<Building> buildingListModel;
    private JList<Building> buildingList;
    private JPanel panel;

    private void createUIComponents() {
       buildingListModel = new DefaultListModel<>();
        buildingList = new JList<>(buildingListModel);
        buildingList.setCellRenderer(new BuildingListCellRenderer());

        removeConstruction = new JButton(" REMOVE CONSTRUCTION");

    }

    private static class BuildingListCellRenderer extends JLabel implements ListCellRenderer<Building>{

        public BuildingListCellRenderer(){
            setOpaque(true);
            Border blackline = BorderFactory.createLineBorder(Color.black);
            setBorder(blackline);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Building> list, Building value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.getName() + " " + value.getBuildingType());
            if(isSelected){
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            }
            else{
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            return this;
        }
    }
}
