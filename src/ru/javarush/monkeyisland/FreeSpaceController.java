package ru.javarush.monkeyisland;

import java.util.ArrayList;
import java.util.List;


public class FreeSpaceController {
    private int ISLAND_LENGTH;
    private int ISLAND_WIDTH;

    int numbersOfItems;
    public List<Integer>[][] freeSpaces;

    public FreeSpaceController(int islandWidth, int islandLength, int numbersOfItems) {
        //System.out.println("Constructor FreeSpaceControllera");
        this.ISLAND_WIDTH = islandWidth;
        this.ISLAND_LENGTH = islandLength;

        //System.out.println("Island width and length = " + ISLAND_WIDTH + " " + ISLAND_LENGTH);
        this.numbersOfItems = numbersOfItems;
        this.freeSpaces = new ArrayList[islandWidth][islandLength];


    }
    public synchronized void setFreeSpaces(List<Integer> inFreeSpaces, int y, int x) {
        //System.out.println("FreeSpaces: " + inFreeSpaces + " " + y  + " " + x);
        freeSpaces[y][x] = inFreeSpaces;
    }

    public synchronized List<Integer> getFreeSpaces(int y, int x) {
        return freeSpaces[y][x];
    }


}
class ListWrapper{
    List<Integer> listOfFreeSpaces = new ArrayList<>();

    public void setListOfFreeSpaces(List<Integer> listOfFreeSpaces) {
        this.listOfFreeSpaces = listOfFreeSpaces;
    }

    public List<Integer> getListOfFreeSpaces() {
        return listOfFreeSpaces;
    }
}