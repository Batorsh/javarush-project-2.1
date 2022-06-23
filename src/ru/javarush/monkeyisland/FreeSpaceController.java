package ru.javarush.monkeyisland;

import ru.javarush.monkeyisland.items.GameItem;

import java.util.ArrayList;
import java.util.List;


public class FreeSpaceController {
    private int ISLAND_LENGTH;
    private int ISLAND_WIDTH;

    int numbersOfItems;
    public List<Integer>[][] freeSpaces;

    public FreeSpaceController(int islandWidth, int islandLength, int numbersOfItems) {
        this.ISLAND_WIDTH = islandWidth;
        this.ISLAND_LENGTH = islandLength;

        this.numbersOfItems = numbersOfItems;
        this.freeSpaces = new ArrayList[islandWidth][islandLength];


    }
    public synchronized void setFreeSpaces(List<Integer> inFreeSpaces, int y, int x) {
        freeSpaces[y][x] = inFreeSpaces;
    }

    public synchronized List<Integer> getFreeSpaces(int y, int x) {
        return freeSpaces[y][x];
    }
    public synchronized void minusOneSpace(int type, int y, int x) {
        Integer newInteger = freeSpaces[y][x].get(type) - 1;
        freeSpaces[y][x].add(type, newInteger);
    }
}
