
import view.MainFrame;
import view.StartFrame;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new MainFrame();
        });
        //new StartFrame();

    }
}
