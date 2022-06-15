package ru.javarush.monkey_island.items;

public class Plant extends GameItem{

    final static int TYPE = 16;
    final static int WEIGHT = 1_000;

    static int MAX_HEALTH_POINT = 1;

    int healthPoint = 1;
    int healthPointsPerDay = 0;

    public int getHealthPointsPerDay() {
        return healthPointsPerDay;
    }
    public void setHealthPoint(int healthPoint) {
        this.healthPoint += healthPoint;
        if (this.healthPoint > getMaxHealthPoint()) {
            this.healthPoint = getMaxHealthPoint();
        }
    }
    public int getHealthPoint() {
        return healthPoint;
    }
    public int getMaxHealthPoint() {
        return MAX_HEALTH_POINT;
    }
    public int getTYPE() {
        return TYPE;
    }
    public int getWEIGHT() {
        return WEIGHT;
    }

}
