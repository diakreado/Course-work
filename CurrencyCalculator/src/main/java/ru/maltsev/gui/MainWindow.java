package ru.maltsev.gui;

import javax.swing.*;

public class MainWindow {

    /* Вынесены в поля, чтобы из Engine был доступ к ним */
    JTextField textField1;
    JTextField textField2;


    MainWindow() {

        /* Создаём фрейм, устанавливаем его размер */
        JFrame frame = new JFrame("Валютный калькулятор");
        frame.setSize(400,175);
        frame.setResizable(false); // Запрещаем изменять размер фрейма
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Вместе с фреймом закрывается и программа


        JPanel panel = new JPanel(); // Создаём панель
        panel.setLayout(null); // Сделано, чтобы можно было самому задавать местоположение объектов


        /* Создаём 2 поля ввода текста, помещаем на панель и устанавливаем их местоположение */
        textField1 = new JTextField();
        panel.add(textField1);
        textField1.setBounds(25, 55, 95, 23);

        textField2 = new JTextField();
        panel.add(textField2);
        textField2.setBounds(235, 55, 95, 23);


         /* Создаём 2 метки для указания валют, помещаем ...*/
        JLabel labelUSD = new JLabel("usd");
        panel.add(labelUSD);
        labelUSD.setBounds(335,55,95,23);

        JLabel labelRUB = new JLabel("руб");
        panel.add(labelRUB);
        labelRUB.setBounds(125,55,95,23);


        /* Создаём  движок и делаем его слушателем сигналов*/
        Engine engine = new Engine(this);

        textField1.addActionListener(engine);
        textField2.addActionListener(engine);


        frame.add(panel); // Устанавливаем панель на фрейм
        frame.setVisible(true); // Делаем фрейм видимым
    }

    public static void main(String[] args) {

        MainWindow start = new MainWindow();

    }
}
