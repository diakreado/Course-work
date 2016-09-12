package ru.maltsev.gui;

import javax.swing.*;

public class MainWindow {

    JLabel label;


    MainWindow() {

        /* Создаём фрейм, устанавливаем его размер */
        JFrame frame = new JFrame("Валютный калькулятор");
        frame.setSize(400,175);
        frame.setResizable(false); // Запрещаем изменять размер фрейма
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Вместе с фреймом закрывается и программа


        JPanel panel = new JPanel(); // Создаём панель
        panel.setLayout(null); // Сделано, чтобы можно было самому задавать местоположение объектов


        /* Создаём поле ввода текста, устанавливаем его местоположение и помещаем на панель */
        JTextField textField = new JTextField();
        panel.add(textField);
        textField.setBounds(25, 55, 95, 23);

         /* Создаём поле вывода текста...*/
        label = new JLabel();
        panel.add(label);
        label.setBounds(275,55,95,23);

        Engine engine = new Engine(this);

        textField.addActionListener(engine);


        frame.add(panel); // Устанавливаем панель на фрейм
        frame.setVisible(true); // Делаем фрейм видимым
    }

    public static void main(String[] args) {

        MainWindow start = new MainWindow();

    }
}
