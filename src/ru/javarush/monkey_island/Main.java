package ru.javarush.monkey_island;

import ru.javarush.monkey_island.constants.Constants;
import ru.javarush.monkey_island.items.*;


public class Main {
    public static void main(String[] args) {
        Bear bear = new Bear();
        Duck duck = new Duck();
        System.out.println(duck.getTYPE());
        System.out.println(bear.getTYPE());
        System.out.println(Constants.canEat(bear, duck));
        System.out.println(Constants.chanceToEat(bear, duck));
    }


}
