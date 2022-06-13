package ru.javarush.monkey_island.items;

public class Buffalo extends Herbivore {
    static int TYPE = 13;
    static int WEIGHT = 700_000;
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
