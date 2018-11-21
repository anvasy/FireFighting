package view;

import model.LevelsEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame {

    private JButton start;
    private JComboBox<LevelsEnum> chooseLevels;

    public StartFrame() {
        setTitle("Welcome");
        setSize(200, 200);
        setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        action();
        setResizable(false);
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(null);

        JLabel levels = new JLabel("SELECT DIFFICULTY");
        chooseLevels = new JComboBox(LevelsEnum.values());
        start = new JButton("START");

        levels.setBounds(40, 20, 150, 30);
        chooseLevels.setBounds(50, 60, 100, 30);
        start.setBounds(50, 100, 100, 30);

        panel.add(start);
        panel.add(chooseLevels);
        panel.add(levels);
        add(panel);
    }

    private void action() {
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // new MainFrame((LevelsEnum) chooseLevels.getSelectedItem());
            }
        });
    }
}
