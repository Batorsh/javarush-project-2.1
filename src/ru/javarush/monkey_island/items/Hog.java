package ru.javarush.monkey_island.items;

public class Hog extends Herbivore {
    final static int TYPE = 12;
    final static int WEIGHT = 400_000;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    double MAX_FOOD;


    public int getTYPE() {
        return TYPE;
    }
    public int getWEIGHT() {
        return WEIGHT;
    }
    public double getMAX_FOOD() {
        return MAX_FOOD;
    }
}
