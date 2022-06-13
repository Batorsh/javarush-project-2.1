package ru.javarush.monkey_island.items;

public class Hog extends Herbivore {
    int WEIGHT;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    double MAX_FOOD;
    final int TYPE = 12;

    public int getTYPE() {
        return TYPE;
    }
}
