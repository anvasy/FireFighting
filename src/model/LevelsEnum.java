package model;

public enum LevelsEnum {
    FIRSTTIMER(2,2,1,0,60), BEGINNER(4,4,6,1,60), AMATEUR(4,5,8,2,60), EXPERT(4,7,12,4,60);

    private int windowsHorisontal;
    private int windowsVertical;
    private int windowsOnFire;
    private int people;
    private int timeInSeconds;

    LevelsEnum(int windowsHorisontal, int windowsVertical,
               int windowsOnFire, int people, int timeInSeconds)  {
        this.windowsHorisontal = windowsHorisontal;
        this.windowsVertical = windowsVertical;
        this.windowsOnFire = windowsOnFire;
        this.people = people;
        this.timeInSeconds = timeInSeconds;
    }

    public int getPeople() {
        return people;
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public int getWindowsHorisontal() {
        return windowsHorisontal;
    }

    public int getWindowsOnFire() {
        return windowsOnFire;
    }

    public int getWindowsVertical() {
        return windowsVertical;
    }
}
