package ru.javarush.monkey_island.items;

public class Plant extends GameItem{

    final static int TYPE = 16;
    final static int WEIGHT = 1_000;
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
