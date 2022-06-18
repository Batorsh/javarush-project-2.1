package ru.javarush.monkeyisland.items;

public class Sheep extends Herbivore {
    final static int TYPE = 10;
    final static int WEIGHT = 70_000;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    static int MAX_HEALTH_POINT = 15_000;

    int healthPoint;
    int healthPointsPerDay = 2_500;

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
