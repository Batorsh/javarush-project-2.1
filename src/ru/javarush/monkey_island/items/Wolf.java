package ru.javarush.monkey_island.items;

public class Wolf extends Predator{
    final static int TYPE = 1;
    final static int WEIGHT = 50_000;
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
