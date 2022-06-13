package ru.javarush.monkey_island.items;

public abstract class Predator extends GameItem{
    int WEIGHT;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    double MAX_FOOD;

    public int getWEIGHT() {
        return 0;
    }


    public int getTYPE() {
        return 0;
    }
}
