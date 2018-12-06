package view;

import controller.Controller;
import model.LevelsEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame {

    private JLabel coins;
    private JButton start;
    private JButton highscores;
    private JButton clear;
    private JButton shop;
    private JComboBox<LevelsEnum> chooseLevels;
    private Controller controller;

    public StartFrame(Controller controller) {
        this.controller = controller;
        setTitle("Start");
        setSize(400,512);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setContentPane(new JLabel(new ImageIcon("resources/startBackground.png")));
        setLayout(null);
        initComponents();
        action();
        revalidate();
        repaint();
    }

    private void initComponents() {
        Font font = new Font("Serif", Font.PLAIN, 16);

        JLabel levels = new JLabel("SELECT DIFFICULTY");
        JLabel copyright = new JLabel("Copyright © 2018 An.Vasy");
        copyright.setFont(font);
        copyright.setForeground(Color.GRAY);
        coins = new JLabel(String.valueOf(controller.getMoney()));
        coins.setFont(font);
        ImageIcon icon = new ImageIcon("resources/coin.png");
        JLabel coin = new JLabel();
        coin.setIcon(icon);
        levels.setFont(font);
        chooseLevels = new JComboBox(LevelsEnum.values());
        start = new JButton("START");
        highscores = new JButton("HIGHSCORES");
        shop = new JButton("SHOP");
        clear = new JButton("CLEAR RESULTS");

        levels.setFont(font);
        start.setFont(font);
        highscores.setFont(font);
        shop.setFont(font);
        clear.setFont(font);

        levels.setBounds(40, 70, 150, 30);
        coins.setBounds(40, 10, 30, 30);
        coin.setBounds(10, 10, 30, 30);
        chooseLevels.setBounds(20, 100, 200, 30);
        start.setBounds(20, 150,200, 30);
        shop.setBounds(20, 190,200, 30);
        highscores.setBounds(20, 230,200, 30);
        clear.setBounds(20, 270,200, 30);
        copyright.setBounds(220, 450, 300, 30);


        add(coin);
        add(coins);
        add(start);
        add(highscores);
        add(shop);
        add(clear);
        add(chooseLevels);
        add(levels);
        add(copyright);
    }

    private void action() {
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.readFile();
                new MainFrame((LevelsEnum)chooseLevels.getSelectedItem(), controller);
                setVisible(false);
                dispose();
            }
        });
        highscores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HighScoreFrame(controller);
            }
        });
        shop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShopFrame(controller);
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clearAll();
                coins.setText("0");
                revalidate();
                repaint();
            }
        });
    }
}
