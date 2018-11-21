package view;

import controller.TimerListener;
import model.WindowModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MainFrame extends JFrame {

    private JSlider horizontalSlider;
    private JSlider verticalSlider;
    private boolean stop;
    private boolean stopVer;
    private Timer timer;
    private House house;

    public MainFrame() {
        initUI();

        setTitle("FireFighting");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
    }

    private void initUI() {

        horizontalSlider = new JSlider(JSlider.HORIZONTAL, 400, 0);
        horizontalSlider.setEnabled(false);
        horizontalSlider.setBounds(30, 20, 400, 30);

        verticalSlider = new JSlider(JSlider.VERTICAL, 0, 400, 0);
        verticalSlider.setEnabled(false);
        verticalSlider.setBounds(10, 30, 20, 400);

        add(horizontalSlider);
        add(verticalSlider);

        moveHorizontalSlider();

        WindowModel windowModel =  new WindowModel(false, false);
        Window window = new Window(60, 60, windowModel);
        add(window);

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {

                int key = e.getKeyChar();

                if (key == KeyEvent.VK_SPACE) {
                    if(!stop) {
                        stop = true;
                        moveVerticalSlider();
                    } else {
                        stopVer = true;
                        countMarks();
                    }
                }
            }
        });

        TimerListener timerListener = new TimerListener(MainFrame.this);
        timer = new Timer(15, timerListener);
        add(timer);
    }

    private void moveVerticalSlider() {
        Thread goSlider = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!stopVer) {
                    try {
                        Thread.sleep(10);
                    } catch(InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    if(verticalSlider.getValue() >= verticalSlider.getMaximum())
                        verticalSlider.setValue(0);

                    verticalSlider.setValue(verticalSlider.getValue() + 2);
                }
            }
        });
        goSlider.start();
    }

    private void moveHorizontalSlider() {
        Thread goSlider = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!stop) {
                    try {
                        Thread.sleep(10);
                    } catch(InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    if(horizontalSlider.getValue() >= horizontalSlider.getMaximum())
                        horizontalSlider.setValue(0);

                    horizontalSlider.setValue(horizontalSlider.getValue() + 2);
                }
            }
        });
        goSlider.start();
    }

    private Point countMarks() {
        System.out.println(horizontalSlider.getValue());
        System.out.println(verticalSlider.getValue());
        return new Point(horizontalSlider.getValue(), verticalSlider.getValue());
    }

    private void restart() {

    }

    public void stopEverything() {
        stop = true;
        stopVer = true;
        timer.stop();
    }

}