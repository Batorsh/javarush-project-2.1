package ru.javarush.monkey_island.items;

import java.util.HashMap;

public class Bear extends Predator{
    int WEIGHT;
    int MAX_AMOUNT_ON_FIELD;
    int SPEED;
    double MAX_FOOD;
    final int TYPE = 4;

    public int getTYPE() {
        return TYPE;
    }
    public double getMAX_FOOD() {
        return MAX_FOOD;
    }
    static HashMap<Integer, Integer> chanceToEat = new HashMap<>();

    static {
        chanceToEat.put(2, 80);
        chanceToEat.put(6, 40);
        chanceToEat.put(7, 80);
        chanceToEat.put(8, 80);
        chanceToEat.put(9, 90);
        chanceToEat.put(10, 70);
        chanceToEat.put(11, 70);
        chanceToEat.put(12, 50);
        chanceToEat.put(13, 20);
        chanceToEat.put(14, 10);
    }




}
