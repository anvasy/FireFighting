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
        if(firehose.equals(FirehoseEnum.STANDART))
            loadImage("resources/water.png");
        if(firehose.equals(FirehoseEnum.IMPROVED))
            loadImage("resources/i_water.png");
        if(firehose.equals(FirehoseEnum.ELITE))
            loadImage("resources/e_water.png");
        getImageDimensions();
    }
}
