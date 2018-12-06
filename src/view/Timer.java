package view;

import controller.TimerListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer extends JPanel {

    private javax.swing.Timer timer;
    private JLabel showTime;
    private TimerListener timerListener;
    private TimeCounter tc;

    public Timer(int max, TimerListener timerController) {
        setOpaque(false);

        showTime = new JLabel("00:00");
        showTime.setFont(new Font("Serif", Font.PLAIN, 20));
        tc = new TimeCounter(max);
        timer = new javax.swing.Timer(1000, tc);
        timer.start();

        this.timerListener = timerController;

        add(showTime);
    }

    public int getTimeLeft() {
        return tc.counter;
    }

    public void stop() {
        timer.stop();
    }

    public class TimeCounter implements ActionListener {
        private int counter;

        public TimeCounter(int counter) {
            this.counter = counter;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            counter--;

            if(counter >= 10) {
                showTime.setText("00:" + counter);
            }else if(counter >= 1) {
                showTime.setText("00:0" + counter);
            } else if(counter < 1){
                timer.stop();
                showTime.setText("00:00");
                timerListener.timeOut();
            }
        }
    }
}
