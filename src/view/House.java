package view;

import model.Circle;
import model.FirehoseEnum;
import model.LevelsEnum;
import model.WindowModel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class House extends JPanel {

    private List<Window> windows;
    private LevelsEnum levelsEnum;
    private boolean out;
    private WaterCircle waterCircle;
    private FirehoseEnum firehoseEnum;
    private int score = 0;

    private static final int windowWidth = 60;
    private static final int windowHeight = 70;
    private static final int windowScore = 20;

    public House(LevelsEnum levelsEnum, FirehoseEnum firehoseEnum) {
        setOpaque(false);
        this.levelsEnum = levelsEnum;
        this.firehoseEnum = firehoseEnum;
        setDoubleBuffered(true);
        setLayout(null);
        setVisible(true);
        buildHouse();
    }

    private void buildHouse() {
       windows = new ArrayList<>();
       List<WindowModel> windowModels = new ArrayList<>();
       for(int el = 0; el < levelsEnum.getWindowsVertical() * levelsEnum.getWindowsHorizontal(); el++) {
           WindowModel windowModel = new WindowModel(false);
           windowModels.add(windowModel);
       }

        Set<Integer> burning = setOnFire();
        for (Integer num : burning) {
            windowModels.get(num - 1).setOnFire(true);
        }

        int x = 50;
        int y = 50;
        int counter = 0;

        for(WindowModel windowModel : windowModels) {
            windows.add(new Window(x, y, windowModel));
            x += windowWidth + 20;
            counter++;
            if(counter >= levelsEnum.getWindowsHorizontal()) {
                counter = 0;
                x = 50;
                y += windowHeight + 20;
            }
        }
    }

    public void putOut(Point point) {
        Thread putOut = new Thread(new Runnable() {
            @Override
            public void run() {
                waterCircle = new WaterCircle(point.x, point.y, firehoseEnum);
                out = true;
                repaint();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                checkCollisions();
                repaint();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(checkVictory()) {
                    MainFrame frame = (MainFrame) getRootPane().getParent();
                    score += frame.countTimerScore();
                    frame.pauseEverything();
                    frame.dispose();
                    new ResultFrame(true, frame.getController(), score);
                }
            }
        });
        putOut.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawObjects(g);
        if(out)
            drawCircle(g);
    }

    private void drawObjects(Graphics g) {
        for (Window window : windows) {
            g.drawImage(window.getImage(), window.getX(), window.getY(),
                    this);
        }
    }

    private void drawCircle(Graphics g) {
        g.drawImage(waterCircle.getImage(), waterCircle.getX(), waterCircle.getY(), this);
    }

    private Set<Integer> setOnFire() {
        Set<Integer> burning = new HashSet<Integer>();
        Random r = new Random();
        while (burning.size() < levelsEnum.getWindowsOnFire()) {
            burning.add(r.nextInt(levelsEnum.getWindowsHorizontal() * levelsEnum.getWindowsVertical()) + 1);
        }
        return burning;
    }

    public void checkCollisions() {

        Circle circle = new Circle(firehoseEnum.getRadius(), waterCircle.getX(), waterCircle.getY());

        for (Window window : windows) {
            Rectangle win = window.getBounds();
            if (circle.intersects(win) && window.getWindowModel().getIsOnFire()) {
                window.loadImage("resources/window_human.png");
                window.setFire(false);
                score += windowScore;
            }
        }
        out = false;
    }

    public boolean checkVictory() {
        int counter = 0;
        for(Window window : windows) {
            if (window.getWindowModel().getIsOnFire())
                counter++;
        }
        return (counter == 0);
    }

}
