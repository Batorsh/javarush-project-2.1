package ru.javarush.monkey_island.items;

public class Goat extends Herbivore {
    int WEIGHT;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    double MAX_FOOD;
    final int TYPE = 10;

    public int getTYPE() {
        return TYPE;
    }

    public double getMAX_FOOD() {
        return MAX_FOOD;
    }
}
