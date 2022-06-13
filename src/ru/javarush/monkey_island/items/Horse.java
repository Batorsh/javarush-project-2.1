package ru.javarush.monkey_island.items;

public class Horse extends Herbivore {
    int WEIGHT;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    double MAX_FOOD;
    final int TYPE = 6;

    public int getTYPE() {
        return TYPE;
    }
}
