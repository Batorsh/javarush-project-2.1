package ru.javarush.monkeyisland.items;

public class Caterpillar extends Herbivore{
    static final int TYPE = 14;
    static final int WEIGHT = 10;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
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
    @Override
    public int getType() {
        return TYPE;
    }
    @Override
    public int getWeight() {
        return WEIGHT;
    }

}
