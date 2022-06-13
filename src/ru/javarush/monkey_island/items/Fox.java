package ru.javarush.monkey_island.items;

public class Fox extends Predator{
    final static int TYPE = 3;
    final static int WEIGHT = 8_000;
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
