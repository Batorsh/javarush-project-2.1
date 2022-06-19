package ru.javarush.monkeyisland;

import ru.javarush.monkeyisland.items.GameItem;

import java.util.*;

public class Exchanger {

    private int ISLAND_LENGTH;
    private int ISLAND_WIDTH;

    int numbersOfItems;
    private Queue<TransferGameItem> queueOfTransferredItems = new LinkedList<>();

    public Exchanger(int islandWidth, int islandLength,
                     int numbersOfItems) {
        System.out.println("Constructor exchangera");
        this.ISLAND_WIDTH = islandWidth;
        this.ISLAND_LENGTH = islandLength;
        this.numbersOfItems = numbersOfItems;

    }

    public synchronized void addTransferItem(TransferGameItem transferGameItem) {
        queueOfTransferredItems.add(transferGameItem);
    }

    public synchronized TransferGameItem getTransferredItems() {
        return queueOfTransferredItems.poll();
    }

    public synchronized Queue<TransferGameItem> getQueueOfTransferredItems(){
        return queueOfTransferredItems;
    }
}


