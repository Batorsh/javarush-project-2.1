package ru.javarush.monkey_island.items;

public abstract class Herbivore extends GameItem{
    final static int TYPE = 12;
    int WEIGHT;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    double MAX_FOOD;

    public int getTYPE() {
        return 0;
    }
    public int getWEIGHT() {
        return WEIGHT;
    }
    public double getMAX_FOOD() {
        return MAX_FOOD;
    }
}
