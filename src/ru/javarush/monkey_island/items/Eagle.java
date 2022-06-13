package ru.javarush.monkey_island.items;

public class Eagle extends Predator{
    int WEIGHT;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    double MAX_FOOD;
    final int TYPE = 5;

    public int getTYPE() {
        return TYPE;
    }
}
