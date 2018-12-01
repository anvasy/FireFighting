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
    private static final int windowWidth = 65;
    private static final int windowHeight = 75;

    public House(LevelsEnum levelsEnum) {
        this.levelsEnum = levelsEnum;
        setDoubleBuffered(true);
        setLayout(null);
        setVisible(true);

        buildHouse();
        WindowModel windowModel =  new WindowModel(false, false);
        Window window = new Window(50, 50, windowModel);
        add(window);
    }


    private void buildHouse() {

       windows = new ArrayList<>();
       List<WindowModel> windowModels = new ArrayList<>();
       for(int el = 0; el < levelsEnum.getWindowsVertical() * levelsEnum.getWindowsHorizontal(); el++) {
           WindowModel windowModel = new WindowModel(false, false);
           windowModels.add(windowModel);
       }

        Set<Integer> burning = setOnFire();
        for (Integer num : burning) {
            System.out.println(num);
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
                waterCircle = new WaterCircle(point.x, point.y, FirehoseEnum.STANDART);
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

        Circle circle = new Circle(10, waterCircle.getX(), waterCircle.getY());

        for (Window window : windows) {

            Rectangle win = window.getBounds();

            if (circle.intersects(win) && window.getWindowModel().getIsOnFire()) {
                window.loadImage("resources/window_human.png");
                window.setFire(false);
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

        return (counter > 0);
    }

}
