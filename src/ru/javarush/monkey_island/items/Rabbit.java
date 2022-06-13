package ru.javarush.monkey_island.items;

public class Rabbit extends Herbivore {
    int WEIGHT;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    double MAX_FOOD;
    final int TYPE = 8;

    public int getTYPE() {
        return TYPE;
    }
}
