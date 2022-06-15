package ru.javarush.monkey_island.items;

public class Boa extends Predator {
    static int TYPE = 2;
    static int WEIGHT = 15000;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    static int MAX_HEALTH_POINT;

    int healthPoint;

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

    @Override
    public int getTYPE() {
        return TYPE;
    }

    @Override
    public int getWEIGHT() {
        return WEIGHT;
    }


}
