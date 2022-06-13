package ru.javarush.monkey_island.items;

public class Caterpillar extends Herbivore{
    static final int TYPE = 15;
    static final int WEIGHT = 10;
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
