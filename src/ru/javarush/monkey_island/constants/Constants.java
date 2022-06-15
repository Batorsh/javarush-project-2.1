package ru.javarush.monkey_island.constants;

import ru.javarush.monkey_island.items.GameItem;

import java.util.*;

public class Constants {

    int ISLAND_LENGTH = 100;
    int ISLAND_WIDTH = 20;

    public int[] maxItemsOnField = new int[17];
    {
        maxItemsOnField[1] = 30;
        maxItemsOnField[2] = 30;
        maxItemsOnField[3] = 30;
        maxItemsOnField[4] = 5;
        maxItemsOnField[5] = 20;
        maxItemsOnField[6] = 20;
        maxItemsOnField[7] = 20;
        maxItemsOnField[8] = 150;
        maxItemsOnField[9] = 500;
        maxItemsOnField[10] = 140;
        maxItemsOnField[11] = 140;
        maxItemsOnField[12] = 50;
        maxItemsOnField[13] = 10;
        maxItemsOnField[14] = 200;
        maxItemsOnField[15] = 1000;
        maxItemsOnField[16] = 200;
    }
    public int getMaxItemsOnField(int typeOfItem1) {
        return maxOffspring[typeOfItem1];
    }

    HashMap<Integer, Set<Integer>> MapOfCanEat = new HashMap<>();

    {
        MapOfCanEat.put(1, new HashSet<>(List.of(6, 7, 8, 9, 10, 11, 12, 13, 14)));
        MapOfCanEat.put(2, new HashSet<>(List.of(3, 8, 9, 14)));
        MapOfCanEat.put(3, new HashSet<>(List.of(8, 9, 14, 15)));
        MapOfCanEat.put(4, new HashSet<>(List.of(2, 6, 7, 8, 9, 10, 11, 12, 13, 14)));
        MapOfCanEat.put(5, new HashSet<>(List.of(3, 8, 9, 14)));
        MapOfCanEat.put(6, new HashSet<>(List.of(16)));
        MapOfCanEat.put(7, new HashSet<>(List.of(16)));
        MapOfCanEat.put(8, new HashSet<>(List.of(16)));
        MapOfCanEat.put(9, new HashSet<>(List.of(15, 16)));
        MapOfCanEat.put(10, new HashSet<>(List.of(16)));
        MapOfCanEat.put(11, new HashSet<>(List.of(16)));
        MapOfCanEat.put(12, new HashSet<>(List.of(9, 15, 16)));
        MapOfCanEat.put(13, new HashSet<>(List.of(16)));
        MapOfCanEat.put(14, new HashSet<>(List.of(15, 16)));
        MapOfCanEat.put(15, new HashSet<>(List.of(16)));
        MapOfCanEat.put(16, new HashSet<>(List.of(0)));
    }

    int[][] chanceToEat = new int[17][17];

    {
        //Wolf
        chanceToEat[1][6] = 10;
        chanceToEat[1][7] = 15;
        chanceToEat[1][8] = 60;
        chanceToEat[1][9] = 80;
        chanceToEat[1][10] = 60;
        chanceToEat[1][11] = 70;
        chanceToEat[1][12] = 15;
        chanceToEat[1][13] = 10;
        chanceToEat[1][14] = 40;
        //Boa
        chanceToEat[2][3] = 15;
        chanceToEat[2][8] = 20;
        chanceToEat[2][9] = 40;
        chanceToEat[2][14] = 10;
        //Fox
        chanceToEat[3][8] = 70;
        chanceToEat[3][9] = 90;
        chanceToEat[3][14] = 60;
        chanceToEat[3][15] = 40;
        //Bear
        chanceToEat[4][2] = 80;
        chanceToEat[4][6] = 40;
        chanceToEat[4][7] = 80;
        chanceToEat[4][8] = 80;
        chanceToEat[4][9] = 90;
        chanceToEat[4][10] = 70;
        chanceToEat[4][11] = 70;
        chanceToEat[4][12] = 50;
        chanceToEat[4][13] = 20;
        chanceToEat[4][14] = 10;

        chanceToEat[5][3] = 10;
        chanceToEat[5][8] = 90;
        chanceToEat[5][9] = 90;
        chanceToEat[5][14] = 80;

        chanceToEat[6][16] = 100;
        chanceToEat[7][16] = 100;
        chanceToEat[8][16] = 100;
        chanceToEat[9][15] = 90;
        chanceToEat[9][16] = 100;
        chanceToEat[10][16] = 100;
        chanceToEat[11][16] = 100;
        chanceToEat[12][9] = 50;
        chanceToEat[12][15] = 90;
        chanceToEat[13][16] = 100;
        chanceToEat[14][15] = 90;
        chanceToEat[15][16] = 100;

    }

    public boolean canEat(GameItem gameItem1, GameItem gameItem2) {

        return MapOfCanEat.get(gameItem1.getTYPE()).contains(gameItem2.getTYPE());
    }

    public int chanceToEat(int typeOfItem1, int typeOfItem2) {
        return chanceToEat[typeOfItem1][typeOfItem2];
    }

    int[] chanceToReproduce = new int[17];
    {
        chanceToReproduce[1] = 5;
        chanceToReproduce[2] = 6;
        chanceToReproduce[3] = 7;
        chanceToReproduce[4] = 8;
        chanceToReproduce[5] = 9;
        chanceToReproduce[6] = 10;
        chanceToReproduce[7] = 11;
        chanceToReproduce[8] = 30;
        chanceToReproduce[9] = 30;
        chanceToReproduce[10] = 14;
        chanceToReproduce[11] = 15;
        chanceToReproduce[12] = 16;
        chanceToReproduce[13] = 17;
        chanceToReproduce[14] = 18;
        chanceToReproduce[15] = 50;
        chanceToReproduce[16] = 50;
    }
    public int chanceToReproduce(int typeOfItem1) {
        return chanceToReproduce[typeOfItem1];
    }

    int[] maxOffspring = new int[17];
    {
        maxOffspring[1] = 2;
        maxOffspring[2] = 2;
        maxOffspring[3] = 2;
        maxOffspring[4] = 2;
        maxOffspring[5] = 2;
        maxOffspring[6] = 2;
        maxOffspring[7] = 2;
        maxOffspring[8] = 5;
        maxOffspring[9] = 7;
        maxOffspring[10] = 2;
        maxOffspring[11] = 2;
        maxOffspring[12] = 2;
        maxOffspring[13] = 2;
        maxOffspring[14] = 5;
        maxOffspring[15] = 5;
        maxOffspring[16] = 3;
    }

    public int getMaxOffspring(int typeOfItem1) {
        return maxOffspring[typeOfItem1];
    }
}
