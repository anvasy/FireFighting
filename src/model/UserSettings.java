package model;

public class UserSettings {
    private String name;
    private int money;
    private FirehoseEnum firehouse;
    private int record;

    public UserSettings() {
        name = "NEW";
        money = 0;
        record = 0;
        firehouse = FirehoseEnum.STANDART;
    }

    public UserSettings(String name, int money, FirehoseEnum firehouse, int record) {
        this.name = name;
        this.money = money;
        this.firehouse = firehouse;
        this.record = record;
    }

    public void withdrawMoney(int price) {
        money = money - price;
    }

    public void addMoney(int earn) {
        money = money + earn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public void setFirehouse(FirehoseEnum firehouse) {
        this.firehouse = firehouse;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public FirehoseEnum getFirehouse() {
        return firehouse;
    }

    public int getRecord() {
        return record;
    }
}
