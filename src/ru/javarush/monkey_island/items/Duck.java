package ru.javarush.monkey_island.items;

public class Duck extends Herbivore {
    static final int TYPE = 14;
    static final int WEIGHT = 1000;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    double MAX_FOOD;

    @Override
    public int getTYPE() {
        return TYPE;
    }
    @Override
    public int getWEIGHT() {
        return WEIGHT;
    }
    public double getMAX_FOOD() {
        return MAX_FOOD;
    }
}
