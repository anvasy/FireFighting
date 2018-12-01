package model;

public enum LevelsEnum {
    FIRSTTIMER(2,2,1,0,20), BEGINNER(4,4,6,1,60), AMATEUR(4,5,8,2,60), EXPERT(4,7,12,4,60);

    private int windowsHorizontal;
    private int windowsVertical;
    private int windowsOnFire;
    private int people;
    private int timeInSeconds;

    LevelsEnum(int windowsHorizontal, int windowsVertical,
               int windowsOnFire, int people, int timeInSeconds)  {
        this.windowsHorizontal = windowsHorizontal;
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

    public int getWindowsHorizontal() {
        return windowsHorizontal;
    }

    public int getWindowsOnFire() {
        return windowsOnFire;
    }

    public int getWindowsVertical() {
        return windowsVertical;
    }
}
