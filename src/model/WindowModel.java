package model;

public class WindowModel {
    private boolean isOnFire;
    private boolean isHuman;

    public WindowModel(boolean isOnFire, boolean isHuman) {
        this.isOnFire = isOnFire;
        this.isHuman = isHuman;
    }

    public boolean getIsOnFire() {
        return isOnFire;
    }

    public boolean getIsHuman() {
        return isHuman;
    }
}