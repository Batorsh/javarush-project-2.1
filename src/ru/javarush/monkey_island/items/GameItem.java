package ru.javarush.monkey_island.items;


public abstract class GameItem {

    static int TYPE;
    static int WEIGHT;
    static int MAX_FOOD;


    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    double currentFood;


    public int getTYPE() {
        return TYPE;
    }

    public int getWEIGHT() {
        return WEIGHT;
    }

    public double getMAX_FOOD() {
        return MAX_FOOD;
    }

    public void setCurrentFood(double currentFood) {
        this.currentFood = this.currentFood + currentFood;
        this.currentFood = Math.min(this.currentFood, MAX_FOOD);
    }


}
