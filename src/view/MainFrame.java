package view;

import controller.TimerListener;
import model.LevelsEnum;

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
    private LevelsEnum levelsEnum;

    private static final int windowWidth = 60;
    private static final int windowHeight = 70;
    private static final int offset = 50;

    public MainFrame(LevelsEnum levelsEnum) {
        this.levelsEnum = levelsEnum;

        initUI();

        setTitle("FireFighting");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        int width = windowWidth * levelsEnum.getWindowsHorizontal() + offset * (levelsEnum.getWindowsHorizontal() - 1) + 50;
        int height = windowHeight * levelsEnum.getWindowsVertical() + offset * (levelsEnum.getWindowsVertical() - 1) + 50;
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
    }

    private void initUI() {
        horizontalSlider = new JSlider(JSlider.HORIZONTAL, 360, 0);
        horizontalSlider.setEnabled(false);
        horizontalSlider.setBounds(30, 20, 360, 30);

        verticalSlider = new JSlider(JSlider.VERTICAL, 0, 360, 0);
        verticalSlider.setEnabled(false);
        verticalSlider.setBounds(10, 30, 20, MainFrame.this.getHeight() - 50);

        add(horizontalSlider);
        add(verticalSlider);

        moveHorizontalSlider();

        TimerListener timerListener = new TimerListener(MainFrame.this);
        timer = new Timer(levelsEnum.getTimeInSeconds(), timerListener);
        add(timer);
        house = new House(levelsEnum);
        add(house);

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {

                int key = e.getKeyChar();

                if (key == KeyEvent.VK_SPACE) {
                    if(!stop) {
                        stop = true;
                        stopVer = false;
                        moveVerticalSlider();
                    } else {
                        stopVer = true;
                        house.putOut(countMarks());
                        if(!house.checkVictory())
                            restart();
                        else{
                            stopEverything();
                            new ResultFrame(true);
                        }
                    }
                }
            }
        });

    }

    private void moveVerticalSlider() {
        verticalSlider.setInverted(true);
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
        stop = false;
        moveHorizontalSlider();
    }

    public void stopEverything() {
        stop = true;
        stopVer = true;
        timer.stop();
    }

}