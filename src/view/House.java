package view;

import model.FirehoseEnum;
import model.WindowModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class House extends JPanel {

    public House() {
        setBackground(Color.GRAY);
        setDoubleBuffered(true);
        setLayout(null);
        setVisible(true);

        setPreferredSize(new Dimension(200, 200));

        buildHouse();
        WindowModel windowModel =  new WindowModel(false, false);
        Window window = new Window(50, 50, windowModel);
        add(window);
    }


    private void buildHouse() {

       List<Window> windows = new ArrayList();

        int x = 50;
        int y = 50;
        int height = 50;
        for(int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                WindowModel windowModel = new WindowModel(false, false);
                windows.add(new Window(x, y, windowModel));
            }
            y+= height + 10;
            x = 50;
        }
    }

    public void putOut(Point point) {
        WaterCircle waterCircle = new WaterCircle(point.x, point.y, FirehoseEnum.STANDART);
    }

}
