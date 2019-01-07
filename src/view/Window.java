package view;

import model.WindowModel;

public class Window extends Sprite {

    private WindowModel windowModel;

    public Window(int x, int y, WindowModel windowModel) {
        super(x, y);

        this.windowModel = windowModel;
        initWindow();
    }

    public void setFire(boolean fire) {
        windowModel.setOnFire(fire);
    }

    private void initWindow() {

        if(windowModel.getIsOnFire())
            loadImage("resources/window_on_fire.png");
        else
            loadImage("resources/window.png");

        getImageDimensions();
    }

    public WindowModel getWindowModel() {
        return windowModel;
    }

}



