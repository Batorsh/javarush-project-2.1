package ru.javarush.monkeyisland.items;

public class Buffalo extends Herbivore {
    static int TYPE = 13;
    static int WEIGHT = 700_000;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    static int MAX_HEALTH_POINT = 100_000;

    int healthPoint;
    int healthPointsPerDay = 15_000;

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
