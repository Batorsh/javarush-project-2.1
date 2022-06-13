package ru.javarush.monkey_island.items;


public abstract class GameItem {
    int WEIGHT;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    double MAX_FOOD;
    int TYPE;

    public int getTYPE() {
        return TYPE;
    }

    public double getMAX_FOOD() {
        return MAX_FOOD;
    }
}
