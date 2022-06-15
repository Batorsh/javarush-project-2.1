package ru.javarush.monkey_island.items;


public abstract class GameItem {

    static int TYPE;
    static int WEIGHT;

    static int MAX_HEALTH_POINT;

    int healthPoint;
    int healthPointsPerDay = 0;

    public int getHealthPointsPerDay() {
        return healthPointsPerDay;
    }

    public int getMaxHealthPoint() {
        return MAX_HEALTH_POINT;
    }
    public void setHealthPoint(int healthPoint) {
        this.healthPoint += healthPoint;
        if (this.healthPoint > getMaxHealthPoint()){
            this.healthPoint = getMaxHealthPoint();
        }
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    int MAX_AMOUNT_ON_FIELD;
    int SPEED;

    public int getTYPE() {
        return TYPE;
    }

    public int getWEIGHT() {
        return WEIGHT;
    }


}
