package model;

public enum FirehoseEnum {
    STANDART(20, 0), IMPROVED(30, 200), ELITE(30, 200);

    private final int radius;
    private final int cost;

    FirehoseEnum(int radius, int cost) {
        this.radius = radius;
        this.cost = cost;
    }
    public int getRadius() { return radius; }
    public int getCost() { return radius; }

}
