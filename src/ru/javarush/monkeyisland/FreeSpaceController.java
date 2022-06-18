package ru.javarush.monkeyisland;

import java.util.ArrayList;
import java.util.List;


public class FreeSpaceController {
    private int ISLAND_LENGTH;
    private int ISLAND_WIDTH;

    int numbersOfItems;
    private List<Integer>[][] amountFreePlacesForCreaturesOnField;

    public FreeSpaceController(int islandWidth, int islandLength, int numbersOfItems) {
        System.out.println("Constructor operatora");
        this.ISLAND_WIDTH = islandWidth;
        this.ISLAND_LENGTH = islandLength;
        this.numbersOfItems = numbersOfItems;
        List<Integer>[][] initAmountFreePlacesForCreaturesOnField = new ArrayList[islandWidth][islandLength];
        for (int i = 0; i < islandWidth; i++) {
            for (int j = 0; j < islandLength; j++) {
                initAmountFreePlacesForCreaturesOnField[i][j] = new ArrayList<>();
            }
        }
        amountFreePlacesForCreaturesOnField = initAmountFreePlacesForCreaturesOnField;
    }
    public synchronized void setFreeSpaces(List<Integer> freeSpaces, int y, int x) {
        amountFreePlacesForCreaturesOnField[y][x] = new ArrayList<>(freeSpaces);
    }
    public synchronized List<Integer> getInfoAboutFreeSpaces(int y, int x) {
        return new ArrayList<>(amountFreePlacesForCreaturesOnField[y][x]);
    }

}
