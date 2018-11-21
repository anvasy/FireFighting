package controller;

import view.MainFrame;
import view.Timer;

public class TimerListener {

    private MainFrame mainFrame;
    private Timer timer;

    public TimerListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void timeOut() {
        mainFrame.stopEverything();
    }
}
