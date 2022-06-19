package ru.javarush.monkeyisland;

import ru.javarush.monkeyisland.constants.Constants;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.Phaser;

public class Operator implements Runnable {
    int islandWidth;
    int islandLength;
    int numbersOfTypes = 16;
    Exchanger exchanger;
    Phaser phaser;
    Constants constants;
    FreeSpaceController freeSpaceController;
    int days;

    public Operator(int days, int islandWidth, int islandLength) {
        this.islandWidth = islandWidth;
        this.islandLength = islandLength;
        exchanger = new Exchanger(islandWidth, islandLength, numbersOfTypes);
        phaser = new Phaser();
        constants = new Constants(islandWidth, islandLength);
        freeSpaceController = new FreeSpaceController(islandWidth, islandLength, numbersOfTypes);
        this.days = days;
        this.phaser.register();
    }


    @Override
    public void run() {
        GameField[][] gameFields = new GameField[islandWidth][islandLength];
        for (int i = 0; i < islandWidth; i++) {
            for (int j = 0; j < islandLength; j++) {
                gameFields[i][j] = new GameField(constants, freeSpaceController, exchanger, i, j, days, phaser);
                //ExecutorService service = Executors.newFixedThreadPool(10);
                //service.submit(gameField);
                Thread thread = new Thread(gameFields[i][j]);
                thread.start();
            }
        }

        for (int day = 0; day < days; day++) {
            Queue<TransferGameItem> queue = exchanger.getQueueOfTransferredItems();
            Iterator iterator = queue.iterator();
            System.out.print("Exchanger Queue size = " + queue.size() + " -> ");
            while (iterator.hasNext()) {
                TransferGameItem transferGameItem = (TransferGameItem) iterator.next();
                if (gameFields[transferGameItem.getY()][transferGameItem.getX()].listOfItems.get(transferGameItem.getGameItem().getType()).size()
                        < constants.getMaxItemsOnField(transferGameItem.getGameItem().getType())) {
                    gameFields[transferGameItem.getY()][transferGameItem.getX()].addGameItemToListOfItems(transferGameItem.getGameItem());
                }
                //System.out.println(transferGameItem.getGameItem().getClass().getSimpleName() + " transferred to field" + transferGameItem.getY() +
                //       transferGameItem.getX());
                iterator.remove();
            }
            System.out.println("Exchanger Queue size = " + queue.size());


            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndAwaitAdvance();
        }
    }
}
