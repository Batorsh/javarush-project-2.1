package ru.javarush.monkey_island.items;

public class Deer extends Herbivore {
    static final int TYPE = 7;
    static final int WEIGHT = 300_000;
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
