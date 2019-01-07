package view;

import controller.Controller;
import model.LevelsEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton toMenu;
    private Controller controller;

    private int width;
    private int height;

    private static final int windowWidth = 60;
    private static final int windowHeight = 70;
    private static final int offset = 50;

    public MainFrame(LevelsEnum levelsEnum, Controller controller) {
        this.controller = controller;
        controller.setLevelsEnum(levelsEnum);
        this.levelsEnum = levelsEnum;
        width = windowWidth * levelsEnum.getWindowsHorizontal() + offset * (levelsEnum.getWindowsHorizontal() - 1) + 160;
        height = windowHeight * levelsEnum.getWindowsVertical() + offset * (levelsEnum.getWindowsVertical() - 1) + 160;

        setContentPane(new JLabel(new ImageIcon("resources/background.png")));
        initComponents();

        setTitle("FireFighting");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        action();
    }

    private void initComponents() {
        toMenu =  new JButton();
        toMenu.setIcon(new ImageIcon("resources/back.png"));
        toMenu.setBounds(0, 0, 30, 30);
        toMenu.setBackground(Color.white);
        toMenu.setFocusable(false);
        add(toMenu);

        int max = width - 140;
        horizontalSlider = new JSlider(JSlider.HORIZONTAL, max, 0);
        horizontalSlider.setEnabled(false);
        horizontalSlider.setBounds(50, 30, max, 30);
        horizontalSlider.setOpaque(false);

        max = height - 140;
        verticalSlider = new JSlider(JSlider.VERTICAL, 0, max, 0);
        verticalSlider.setEnabled(false);
        verticalSlider.setBounds(30, 50, 20, max);
        verticalSlider.setOpaque(false);

        add(horizontalSlider);
        add(verticalSlider);

        moveHorizontalSlider();

        timer = new Timer(levelsEnum.getTimeInSeconds());
        int x = width/2 - 40;
        timer.setBounds(x , 10, 100, 30);

        add(timer);
        house = new House(levelsEnum, controller.getHose());
        house.setBounds(40, 40,
                windowWidth * levelsEnum.getWindowsHorizontal() + offset * (levelsEnum.getWindowsHorizontal()),
                windowHeight * levelsEnum.getWindowsVertical() + offset * (levelsEnum.getWindowsVertical()));
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
                        restart();
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
        return new Point(horizontalSlider.getValue(), verticalSlider.getValue());
    }

    private void restart() {
        stop = false;
        moveHorizontalSlider();
    }

    public void pauseEverything() {
        stop = true;
        stopVer = true;
        timer.stop();
    }

    public void stopEverything() {
        pauseEverything();
        new ResultFrame(false, controller, 0);
    }

    private void action() {
        toMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartFrame(controller);
                dispose();
            }
        });
    }

    public Controller getController() {
        return controller;
    }

    public int countTimerScore() {
        return timer.getTimeLeft() * 3;
    }
}