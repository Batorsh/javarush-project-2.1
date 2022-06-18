package ru.javarush.monkeyisland.items;

public class Duck extends Herbivore {
    static final int TYPE = 13;
    static final int WEIGHT = 1000;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    static int MAX_HEALTH_POINT = 150;

    int healthPoint;
    int healthPointsPerDay = 30;

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
