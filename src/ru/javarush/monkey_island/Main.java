package ru.javarush.monkey_island;

import ru.javarush.monkey_island.constants.Constants;
import ru.javarush.monkey_island.items.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;


public class Main {
    public static void main(String[] args) {
        Constants constants = new Constants();
        GameField gameField = new GameField(constants);
        //ExecutorService service = Executors.newFixedThreadPool(10);
        //service.submit(gameField);
        Thread thread = new Thread(gameField);
        thread.start();
    }


}
