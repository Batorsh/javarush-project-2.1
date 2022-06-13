package ru.javarush.monkey_island.items;

public class Fox extends Predator{
    int WEIGHT;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    double MAX_FOOD;
    final int TYPE = 3;

    public int getTYPE() {
        return TYPE;
    }

    public double getMAX_FOOD() {
        return MAX_FOOD;
    }
}
