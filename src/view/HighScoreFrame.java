package view;

import controller.Controller;
import model.LevelsEnum;
import model.ScoreResult;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HighScoreFrame extends JFrame {

    private JButton clear;
    private JButton ok;
    private ScoreTable scoreTable;
    private Controller controller;

    public HighScoreFrame(Controller controller) {
        this.controller = controller;
        setTitle("Highscores");
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

        JLabel label = new JLabel("YOUR HIGHSCORES: ");
        List<ScoreResult> resultList = controller.getScores();

        scoreTable = new ScoreTable(resultList);
        ((DefaultTableCellRenderer)scoreTable.getDefaultRenderer(Object.class)).setOpaque(false);

        clear = new JButton("CLEAR ALL");
        ok = new JButton("OK");
        clear.setFont(font);
        ok.setFont(font);
        label.setFont(font);
        scoreTable.setFont(font);

        label.setBounds(20, 20, 200, 30);
        scoreTable.setBounds(20, 50, 400, 300);
        clear.setBounds(20, 350, 200, 30);
        ok.setBounds(230, 350, 200, 30);

        add(label);
        add(scoreTable);
        add(ok);
        add(clear);
    }

    private void action() {
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scoreTable.removeData();
                controller.clearScores();
                revalidate();
                repaint();
            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
