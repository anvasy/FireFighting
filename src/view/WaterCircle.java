package view;

import model.FirehoseEnum;

public class WaterCircle extends Sprite {

    private FirehoseEnum firehose;

    public WaterCircle(int x, int y, FirehoseEnum firehose) {
        super(x, y);

        this.firehose = firehose;
        initCircle();
    }

    private void initCircle() {
        loadImage("resources/water.png");
        getImageDimensions();
    }

}
