package model;

import java.util.ArrayList;
import java.util.List;

public class UserSettings {
    private int money;
    private FirehoseEnum firehouse;
    private List<FirehoseEnum> boughtHose;

    public UserSettings() {
        money = 0;
        firehouse = FirehoseEnum.STANDART;
        boughtHose = new ArrayList<>();
    }

    public void withdrawMoney(int price) {
        money = money - price;
    }

    public void addMoney(int earn) {
        money = money + earn;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setFirehouse(FirehoseEnum firehouse) {
        this.firehouse = firehouse;
    }

    public int getMoney() {
        return money;
    }

    public FirehoseEnum getFirehouse() {
        return firehouse;
    }

    public List<FirehoseEnum> getBoughtHose() {
        return boughtHose;
    }

    public void setBoughtHose(List<FirehoseEnum> boughtHose) {
        this.boughtHose = boughtHose;
    }
}
