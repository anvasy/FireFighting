
import controller.Controller;
import view.MainFrame;
import view.StartFrame;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();
        new StartFrame(controller);
    }
}
