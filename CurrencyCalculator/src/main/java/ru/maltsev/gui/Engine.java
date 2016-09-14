package ru.maltsev.gui;

import ru.maltsev.core.CoreOfCalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Engine implements ActionListener {

    MainWindow parent;

    Engine(MainWindow parent){this.parent = parent;}

    /*  Метод который нужно объязательно перегрузить при наследование от ActionListener */
    public void actionPerformed(ActionEvent e){

        JTextField textField = (JTextField)e.getSource();

        String infoFromTextField = textField.getText();


        double displayValue = 0;
        if(!"".equals(infoFromTextField)) displayValue = Double.parseDouble(infoFromTextField);    // TODO  добавить понятность


        if (infoFromTextField.equals(parent.textField1.getText())) {
            parent.textField2.setText(String.format("%.2f", CoreOfCalculator.transferRubTOUsd(displayValue)));
        }
        else {
            parent.textField1.setText(String.format("%.2f", CoreOfCalculator.transferUsdToRub(displayValue)));
        }
    }
}
