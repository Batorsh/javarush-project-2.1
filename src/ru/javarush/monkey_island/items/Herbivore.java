package ru.javarush.monkey_island.items;

public abstract class Herbivore extends GameItem{
    final static int TYPE = 12;
    int WEIGHT;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    static int MAX_HEALTH_POINT;

    int healthPoint;
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
        return 0;
    }
    public int getWEIGHT() {
        return WEIGHT;
    }

}
