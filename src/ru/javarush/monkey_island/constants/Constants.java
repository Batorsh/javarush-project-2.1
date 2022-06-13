package ru.javarush.monkey_island.constants;

import ru.javarush.monkey_island.items.GameItem;

import java.util.*;

public class Constants {

    static int ISLAND_LENGTH = 100;
    static int ISLAND_WIDTH = 20;

    static public HashMap<Integer, Integer> MAX_ITEMS_ON_FIELD = new HashMap<>();

    static {
        MAX_ITEMS_ON_FIELD.put(1, 30);
        MAX_ITEMS_ON_FIELD.put(2, 30);
        MAX_ITEMS_ON_FIELD.put(3, 30);
        MAX_ITEMS_ON_FIELD.put(4, 5);
        MAX_ITEMS_ON_FIELD.put(5, 20);
        MAX_ITEMS_ON_FIELD.put(6, 20);
        MAX_ITEMS_ON_FIELD.put(7, 20);
        MAX_ITEMS_ON_FIELD.put(8, 150);
        MAX_ITEMS_ON_FIELD.put(9, 500);
        MAX_ITEMS_ON_FIELD.put(10, 140);
        MAX_ITEMS_ON_FIELD.put(11, 140);
        MAX_ITEMS_ON_FIELD.put(12, 50);
        MAX_ITEMS_ON_FIELD.put(13, 10);
        MAX_ITEMS_ON_FIELD.put(14, 200);
        MAX_ITEMS_ON_FIELD.put(15, 1000);
        MAX_ITEMS_ON_FIELD.put(16, 200);
    }

    static HashMap<Integer, Set<Integer>> MapOfCanEat = new HashMap<>();

    static {
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

    static int[][] chanceToEat = new int[17][17];

    static {
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

    static public boolean canEat(GameItem gameItem1, GameItem gameItem2) {

        return MapOfCanEat.get(gameItem1.getTYPE()).contains(gameItem2.getTYPE());
    }

    static public int chanceToEat(int typeOfItem1, int typeOfItem2) {
        return chanceToEat[typeOfItem1][typeOfItem2];
    }
}
