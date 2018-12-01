package view;

import javax.swing.*;

public class ResultFrame extends JFrame {
    public ResultFrame(boolean victory) {
        setTitle("Result");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(60, 60);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        JLabel label = new JLabel();
        if(victory)
            label.setText("You won.");
        else
            label.setText("You lost.");
    }
}
