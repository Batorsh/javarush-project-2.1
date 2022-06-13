package ru.javarush.monkey_island.items;

public class Herbivore extends GameItem{
    int WEIGHT;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    double MAX_FOOD;

    public double getMAX_FOOD() {
        return MAX_FOOD;
    }
}
