package model;

public enum LevelsEnum {
    FIRSTTIMER(2, 2, 1, 300, 20), BEGINNER(4, 4, 6, 20, 20), AMATEUR(4, 5, 8, 40, 30), EXPERT(4, 7, 12, 50, 30);

    private int windowsHorizontal;
    private int windowsVertical;
    private int windowsOnFire;
    private int salary;
    private int timeInSeconds;

    LevelsEnum(int windowsHorizontal, int windowsVertical,
               int windowsOnFire, int people, int timeInSeconds) {
        this.windowsHorizontal = windowsHorizontal;
        this.windowsVertical = windowsVertical;
        this.windowsOnFire = windowsOnFire;
        this.salary = people;
        this.timeInSeconds = timeInSeconds;
    }

    public int getSalary() {
        return salary;
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
