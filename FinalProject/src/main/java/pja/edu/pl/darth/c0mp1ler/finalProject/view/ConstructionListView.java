package pja.edu.pl.darth.c0mp1ler.finalProject.view;

import lombok.Data;
import org.springframework.stereotype.Controller;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Construction;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Kingdom;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.RegionImpl;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Class representing configuration for the construction list panel
 */
@Controller
@Data
public class ConstructionListView {
    private JComboBox<Kingdom> kingdomsFilter;
    private JComboBox<RegionImpl> regionsFilter;
    private JList<Construction> constructionList;
    private DefaultListModel<Construction> constructionListModel;
    private JPanel panel;

    private void createUIComponents(){
        constructionListModel = new DefaultListModel<>();
        constructionList = new JList<>(constructionListModel);
        constructionList.setCellRenderer(new ConstructionListCellRenderer());

        kingdomsFilter = new JComboBox<>();
        regionsFilter = new JComboBox<>();

    }

    private static class ConstructionListCellRenderer extends JLabel implements ListCellRenderer<Construction>{

        public ConstructionListCellRenderer(){
            setOpaque(true);
            Border blackline = BorderFactory.createLineBorder(Color.black);
            setBorder(blackline);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Construction> list, Construction value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.getName());
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
