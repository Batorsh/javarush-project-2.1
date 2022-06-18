package ru.javarush.monkeyisland;

import ru.javarush.monkeyisland.constants.Constants;

import java.util.concurrent.Phaser;

public class Main {
    public static void main(String[] args) {
        int length = 2;
        int width = 2;
        int numbersOfTypesOfItems = 16;
        Constants constants = new Constants(length, width);
        FreeSpaceController freeSpaceController = new FreeSpaceController(length, width, numbersOfTypesOfItems);
        Exchanger exchanger = new Exchanger(length, width, numbersOfTypesOfItems);
        Phaser phaser = new Phaser();
        GameField[][] gameFields = new GameField[length][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                gameFields[i][j] = new GameField(constants, freeSpaceController, exchanger, i, j, phaser);
                //ExecutorService service = Executors.newFixedThreadPool(10);
                //service.submit(gameField);
                Thread thread = new Thread(gameFields[i][j]);
                thread.start();
            }
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print("Field " + i + " " + j + ": ");
                for (int type = 0; type < constants.getAmountOfTypes() - 2; type++) {
                    int freeSpaces = freeSpaceController.getInfoAboutFreeSpaces(i, j).get(type);
                    System.out.print(constants.getNameOfTypeByNumber(type) + " " + freeSpaces + "; ");
                }
                System.out.println();
            }
        }
    }
}
