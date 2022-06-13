package ru.javarush.monkey_island.constants;

import ru.javarush.monkey_island.items.GameItem;

import java.util.*;

public class Constants {

    static int ISLAND_LENGTH = 100;
    static int ISLAND_WIDTH = 20;

    static public HashMap<Integer, Integer> MAX_ITEMS_ON_FIELD = new HashMap<>();

    {
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

    static int[][] chanceToEat = new int[ISLAND_WIDTH][ISLAND_LENGTH];

    static {

    }

    static public boolean canEat(GameItem gameItem1, GameItem gameItem2) {

        return MapOfCanEat.get(gameItem1.getTYPE()).contains(gameItem2.getTYPE());
    }

    static public int chanceToEat(GameItem gameItem1, GameItem gameItem2) {
        return chanceToEat[gameItem1.getTYPE()][gameItem2.getTYPE()];
    }
}
