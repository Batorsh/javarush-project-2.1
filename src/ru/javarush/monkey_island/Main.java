package ru.javarush.monkey_island;

import ru.javarush.monkey_island.constants.Constants;
import ru.javarush.monkey_island.items.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;


public class Main {
    public static void main(String[] args) {
        int length = 5;
        int width = 5;
        Constants constants = new Constants(length - 1, width - 1);
        GameField[][] gameFields = new GameField[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                gameFields[i][j] = new GameField(constants, i, j);
                //ExecutorService service = Executors.newFixedThreadPool(10);
                //service.submit(gameField);
                Thread thread = new Thread(gameFields[i][j]);
                thread.start();
            }
        }
    }


}
