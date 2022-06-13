package ru.javarush.monkey_island.items;

public class Wolf extends Predator{
    int WEIGHT;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    double MAX_FOOD;
    final int TYPE = 1;

    public int getTYPE() {
        return TYPE;
    }
}
