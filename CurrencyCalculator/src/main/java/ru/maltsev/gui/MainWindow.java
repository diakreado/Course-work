package ru.maltsev.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mikle on 11.09.2016.
 */
public class MainWindow {

    MainWindow() {

        /* Создаём фрейм, устанавливаем его размер */
        JFrame jf = new JFrame("Валютный калькулятор");
        jf.setSize(400,200);
        jf.setResizable(false); // запрещаем изменять размер фрейма
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Вместе с фреймом закрывается и программа

        JPanel panel = new JPanel(); // Создаём панель

        panel.setLayout(new FlowLayout()); // К панели добавляем менеджер компановки элементов

        /* Создаём кнопку Okay и устанавливаем её размер */
        JButton buttonOk = new JButton("Okay");
        buttonOk.setPreferredSize(new Dimension(100,50)); // Preferred означает, что не следует изменять размер

        panel.add(buttonOk); //  Помещаем кнопку на панель

        jf.add(panel); // Устанавливаем панель на фрейм
        jf.setVisible(true); // Делаем фрейм видимым
    }

    public static void main(String[] args) {

        MainWindow start = new MainWindow();

    }
}
