package ru.javarush.monkey_island;

import ru.javarush.monkey_island.constants.Constants;
import ru.javarush.monkey_island.items.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;


public class Main {
    public static void main(String[] args) {
        Bear bear = new Bear();
        Duck duck = new Duck();
        System.out.println("Duck type = " + duck.getTYPE());
        System.out.println("Bear type = " + bear.getTYPE());
        System.out.println(Constants.canEat(bear, duck));
        System.out.println(Constants.chanceToEat(bear.getTYPE(), duck.getTYPE()));
        System.out.println(Constants.chanceToEat(duck.getTYPE(), bear.getTYPE()));
        Rabbit rabbit = new Rabbit();
        System.out.println("Rabbit type " + rabbit.getTYPE());
        GameField gameField = new GameField();
        //ExecutorService service = Executors.newFixedThreadPool(10);
        //service.submit(gameField);
        Thread thread = new Thread(gameField);
        thread.start();
    }


}
