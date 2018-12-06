package view;

import controller.Controller;
import model.ScoreResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ResultFrame extends JFrame {

    private JButton toMenu;
    private JButton submit;
    private JTextField name;
    private Controller controller;
    private int score;

    public ResultFrame(boolean victory, Controller controller, int score) {
        this.controller = controller;
        this.score = score;
        setTitle("Result");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        setContentPane(new JLabel(new ImageIcon("resources/background.png")));
        submit = new JButton();

        if(victory)
            initVictory();
        else
            initFailure();

        revalidate();
        repaint();

        action();
    }

    private void initVictory() {
        JLabel label = new JLabel("You won!");
        JLabel scoreLabel = new JLabel("Your score " + score);
        toMenu = new JButton("BACK TO MAIN MENU");
        submit = new JButton("OK");
        name = new JTextField("Enter your name", 20);

        label.setFont(new Font("Serif", Font.PLAIN, 22));
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        toMenu.setFont(new Font("Serif", Font.PLAIN, 16));
        submit.setFont(new Font("Serif", Font.PLAIN, 16));
        name.setFont(new Font("Serif", Font.PLAIN, 16));

        label.setBounds(100, 10, 100, 30);
        scoreLabel.setBounds(100, 30, 100, 30);
        toMenu.setBounds(10, 100, 260, 30);
        name.setBounds(10, 60, 130, 30);
        submit.setBounds(150, 60, 120, 30);

        add(label);
        add(scoreLabel);
        add(toMenu);
        add(name);
        add(submit);
    }

    private void initFailure() {
        JLabel label = new JLabel("You lost.");
        JLabel second = new JLabel("Try another time or choose ");
        JLabel third = new JLabel("different difficulty level.");
        toMenu = new JButton("BACK TO MAIN MENU");

        label.setFont(new Font("Serif", Font.PLAIN, 20));
        second.setFont(new Font("Serif", Font.PLAIN, 13));
        third.setFont(new Font("Serif", Font.PLAIN, 13));
        toMenu.setFont(new Font("Serif", Font.PLAIN, 16));

        second.setBounds(60, 50, 200, 40);
        third.setBounds(70, 60, 200, 40);
        label.setBounds(100, 30, 200, 30);
        toMenu.setBounds(10, 100, 260, 30);

        add(second);
        add(third);
        add(label);
        add(toMenu);
    }

    private void action() {
        toMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartFrame(controller);
                dispose();
            }
        });
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScoreResult scoreResult = new ScoreResult();
                scoreResult.setName(name.getText());
                scoreResult.setDate(new Date());
                scoreResult.setScore(String.valueOf(score));
                scoreResult.setLevelsEnum(controller.getLevelsEnum());
                controller.updateScores(scoreResult);
                controller.updateMoney();
                new StartFrame(controller);
                dispose();
            }
        });
    }
}
