package ru.maltsev.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Engine implements ActionListener {

    MainWindow parent;

    Engine(MainWindow parent){this.parent = parent;}

    public void actionPerformed(ActionEvent e){

        JTextField textField = (JTextField)e.getSource();

        parent.label.setText(textField.getText());
    }
}
