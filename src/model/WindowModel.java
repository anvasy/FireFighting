package model;

public class WindowModel {
    private boolean isOnFire;

    public WindowModel(boolean isOnFire) {
        this.isOnFire = isOnFire;
    }

    public boolean getIsOnFire() {
        return isOnFire;
    }

    public void setOnFire(boolean onFire) {
        isOnFire = onFire;
    }
}
