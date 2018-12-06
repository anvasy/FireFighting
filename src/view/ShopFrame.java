package view;

import controller.Controller;
import model.FirehoseEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopFrame extends JFrame {

    private JButton chooseStan;
    private JButton chooseImpr;
    private JButton chooseEl;
    private JButton toMenu;
    private JButton buyImpr;
    private JButton buyElite;
    private Controller controller;

    public ShopFrame(Controller controller) {
        this.controller = controller;

        setTitle("Shop");
        setSize(550,450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setContentPane(new JLabel(new ImageIcon("resources/scoreBackground.png")));
        setLayout(null);
        initComponents();
        action();
        revalidate();
        repaint();
    }

    private void initComponents() {
        Font font = new Font("Serif", Font.PLAIN, 16);
        JLabel label = new JLabel("BUY FIREHOSE");

        JLabel standard = new JLabel("STANDART:");
        JLabel chars = new JLabel("Radius: " + FirehoseEnum.STANDART.getRadius() + " Cost: " + FirehoseEnum.STANDART.getCost());
        chooseStan = new JButton("CHOOSE");
        JButton buy = new JButton("BUY");
        buy.setEnabled(false);

        JLabel improved = new JLabel("IMPROVED:");
        JLabel chars1 = new JLabel("Radius: " + FirehoseEnum.IMPROVED.getRadius() + " Cost: " + FirehoseEnum.IMPROVED.getCost());
        chooseImpr = new JButton("CHOOSE");
        buyImpr = new JButton("BUY");
        if(!controller.hasFirehose(FirehoseEnum.IMPROVED))
            chooseImpr.setEnabled(false);
        if(controller.hasFirehose(FirehoseEnum.IMPROVED))
            buyImpr.setEnabled(false);


        JLabel elite = new JLabel("ELITE:");
        JLabel chars3 = new JLabel("Radius: " + FirehoseEnum.ELITE.getRadius() + " Cost: " + FirehoseEnum.ELITE.getCost());
        chooseEl = new JButton("CHOOSE");
        buyElite = new JButton("BUY");
        if(!controller.hasFirehose(FirehoseEnum.ELITE))
            chooseEl.setEnabled(false);
        if(controller.hasFirehose(FirehoseEnum.ELITE))
            buyElite.setEnabled(false);

        toMenu = new JButton("BACK TO MAIN MENU");

        label.setBounds(30, 20, 490, 30);
        standard.setBounds(30, 50, 490, 30);
        chars.setBounds(30, 70, 490, 30);
        chooseStan.setBounds(30, 100, 200, 30);
        buy.setBounds(240, 100, 200, 30);
        improved.setBounds(30, 130, 490, 30);
        chars1.setBounds(30, 150, 490, 30);
        chooseImpr.setBounds(30, 180, 200, 30);
        buyImpr.setBounds(240, 180, 200, 30);
        elite.setBounds(30, 210, 490, 30);
        chars3.setBounds(30, 230, 490, 30);
        chooseEl.setBounds(30, 260, 200, 30);
        buyElite.setBounds(240, 260, 200, 30);
        toMenu.setBounds(30, 380, 490, 30);

        label.setFont(font);
        standard.setFont(font);
        chars.setFont(font);
        chooseStan.setFont(font);
        buy.setFont(font);
        improved.setFont(font);
        chars1.setFont(font);
        chooseImpr.setFont(font);
        buyImpr.setFont(font);
        elite.setFont(font);
        chars3.setFont(font);
        chooseEl.setFont(font);
        buyElite.setFont(font);
        toMenu.setFont(font);

        add(label);
        add(standard);
        add(chars);
        add(chooseStan);
        add(buy);
        add(improved);
        add(chars1);
        add(chooseImpr);
        add(elite);
        add(chars3);
        add(chooseEl);
        add(buyElite);
        add(buyImpr);
        add(toMenu);
    }

    private void action() {
        chooseStan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setFirehose(FirehoseEnum.STANDART);
            }
        });
        chooseEl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setFirehose(FirehoseEnum.ELITE);
            }
        });
        chooseImpr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setFirehose(FirehoseEnum.IMPROVED);
            }
        });
        buyElite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!controller.buyHose(FirehoseEnum.ELITE))
                    JOptionPane.showMessageDialog(null, "Недостаточно средств. Продолжайте играть, чтоб заработать больше.", "Внимание", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        buyImpr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!controller.buyHose(FirehoseEnum.IMPROVED))
                    JOptionPane.showMessageDialog(null, "Недостаточно средств. Продолжайте играть, чтоб заработать больше.", "Внимание", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        toMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
