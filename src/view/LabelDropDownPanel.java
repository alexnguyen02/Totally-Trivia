package src.view;

import javax.swing.*;

public class LabelDropDownPanel extends JPanel {

    LabelDropDownPanel(JLabel label, JComboBox dropdownField){
        this.add(label);
        this.add(dropdownField);
    }
}
