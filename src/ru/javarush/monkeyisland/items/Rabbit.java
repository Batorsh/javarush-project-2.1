package ru.javarush.monkeyisland.items;

public class Rabbit extends Herbivore {
    final static int TYPE = 7;
    final static int WEIGHT = 2_000;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    static int MAX_HEALTH_POINT = 450;

    int healthPoint;
    int healthPointsPerDay = 80;

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

    public int getType() {
        return TYPE;
    }
    public int getWeight() {
        return WEIGHT;
    }

}
