package ru.javarush.monkeyisland.constants;

import ru.javarush.monkeyisland.items.GameItem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Constants {

    int ISLAND_LENGTH = 100;
    int ISLAND_WIDTH = 20;

    public Constants(int ISLAND_LENGTH, int ISLAND_WIDTH) {
        this.ISLAND_LENGTH = ISLAND_LENGTH;
        this.ISLAND_WIDTH = ISLAND_WIDTH;
    }

    public int getIslandLength() {
        return ISLAND_LENGTH;
    }

    public int getIslandWidth() {
        return ISLAND_WIDTH;
    }

    int amountOfTypes = 16;

    public int getAmountOfTypes() {
        return amountOfTypes;
    }

    public int[] maxItemsOnField = new int[16];

    {
        maxItemsOnField[0] = 30;
        maxItemsOnField[1] = 30;
        maxItemsOnField[2] = 30;
        maxItemsOnField[3] = 5;
        maxItemsOnField[4] = 20;
        maxItemsOnField[5] = 20;
        maxItemsOnField[6] = 20;
        maxItemsOnField[7] = 150;
        maxItemsOnField[8] = 500;
        maxItemsOnField[9] = 140;
        maxItemsOnField[10] = 140;
        maxItemsOnField[11] = 50;
        maxItemsOnField[12] = 10;
        maxItemsOnField[13] = 200;
        maxItemsOnField[14] = 1000;
        maxItemsOnField[15] = 200;
    }

    public int getMaxItemsOnField(int typeOfItem1) {
        return maxItemsOnField[typeOfItem1];
    }

    HashMap<Integer, Set<Integer>> MapOfCanEat = new HashMap<>();//Можно поменять на массив

    {
        MapOfCanEat.put(0, new HashSet<>(List.of(6, 7, 8, 9, 10, 11, 12, 13, 14)));
        MapOfCanEat.put(1, new HashSet<>(List.of(3, 8, 9, 14)));
        MapOfCanEat.put(2, new HashSet<>(List.of(8, 9, 14, 15)));
        MapOfCanEat.put(3, new HashSet<>(List.of(2, 6, 7, 8, 9, 10, 11, 12, 13, 14)));
        MapOfCanEat.put(4, new HashSet<>(List.of(3, 8, 9, 14)));
        MapOfCanEat.put(5, new HashSet<>(List.of(16)));
        MapOfCanEat.put(6, new HashSet<>(List.of(16)));
        MapOfCanEat.put(7, new HashSet<>(List.of(16)));
        MapOfCanEat.put(8, new HashSet<>(List.of(15, 16)));
        MapOfCanEat.put(9, new HashSet<>(List.of(16)));
        MapOfCanEat.put(10, new HashSet<>(List.of(16)));
        MapOfCanEat.put(11, new HashSet<>(List.of(9, 15, 16)));
        MapOfCanEat.put(12, new HashSet<>(List.of(16)));
        MapOfCanEat.put(13, new HashSet<>(List.of(15, 16)));
        MapOfCanEat.put(14, new HashSet<>(List.of(16)));
        MapOfCanEat.put(15, new HashSet<>(List.of(0)));
    }
    public boolean canEat(GameItem gameItem1, GameItem gameItem2) {

        return MapOfCanEat.get(gameItem1.getType()).contains(gameItem2.getType());
    }

    int[][] chanceToEat = new int[16][16];

    {
        //Wolf
        chanceToEat[0][5] = 10;
        chanceToEat[0][6] = 15;
        chanceToEat[0][7] = 60;
        chanceToEat[0][8] = 80;
        chanceToEat[0][9] = 60;
        chanceToEat[0][10] = 70;
        chanceToEat[0][11] = 15;
        chanceToEat[0][12] = 10;
        chanceToEat[0][13] = 40;
        //Boa
        chanceToEat[1][2] = 15;
        chanceToEat[1][7] = 20;
        chanceToEat[1][8] = 40;
        chanceToEat[1][13] = 10;
        //Fox
        chanceToEat[2][7] = 70;
        chanceToEat[2][8] = 90;
        chanceToEat[2][13] = 60;
        chanceToEat[2][14] = 40;
        //Bear
        chanceToEat[3][1] = 80;
        chanceToEat[3][5] = 40;
        chanceToEat[3][6] = 80;
        chanceToEat[3][7] = 80;
        chanceToEat[3][8] = 90;
        chanceToEat[3][9] = 70;
        chanceToEat[3][10] = 70;
        chanceToEat[3][11] = 50;
        chanceToEat[3][12] = 20;
        chanceToEat[3][13] = 10;

        chanceToEat[4][2] = 10;
        chanceToEat[4][7] = 90;
        chanceToEat[4][8] = 90;
        chanceToEat[4][13] = 80;

        chanceToEat[5][15] = 100;
        chanceToEat[6][15] = 100;
        chanceToEat[7][15] = 100;
        chanceToEat[8][14] = 90;
        chanceToEat[8][15] = 100;
        chanceToEat[9][15] = 100;
        chanceToEat[10][15] = 100;
        chanceToEat[11][8] = 50;
        chanceToEat[11][14] = 90;
        chanceToEat[12][15] = 100;
        chanceToEat[13][14] = 90;
        chanceToEat[13][15] = 100;
        chanceToEat[14][15] = 100;
    }

    public int chanceToEat(int typeOfItem1, int typeOfItem2) {
        return chanceToEat[typeOfItem1][typeOfItem2];
    }
    int[] chanceToReproduce = new int[16];
    {
        chanceToReproduce[0] = 5;
        chanceToReproduce[1] = 6;
        chanceToReproduce[2] = 7;
        chanceToReproduce[3] = 3;
        chanceToReproduce[4] = 5;
        chanceToReproduce[5] = 10;
        chanceToReproduce[6] = 11;
        chanceToReproduce[7] = 30;
        chanceToReproduce[8] = 30;
        chanceToReproduce[9] = 14;
        chanceToReproduce[10] = 15;
        chanceToReproduce[11] = 16;
        chanceToReproduce[12] = 5;
        chanceToReproduce[13] = 18;
        chanceToReproduce[14] = 90;
        chanceToReproduce[15] = 70;
    }
    public int chanceToReproduce(int typeOfItem1) {
        return chanceToReproduce[typeOfItem1];
    }
    int[] maxOffspring = new int[16];
    {
        maxOffspring[0] = 2;
        maxOffspring[1] = 2;
        maxOffspring[2] = 2;
        maxOffspring[3] = 2;
        maxOffspring[4] = 2;
        maxOffspring[5] = 2;
        maxOffspring[6] = 2;
        maxOffspring[7] = 5;
        maxOffspring[8] = 7;
        maxOffspring[9] = 2;
        maxOffspring[10] = 2;
        maxOffspring[11] = 2;
        maxOffspring[12] = 2;
        maxOffspring[13] = 5;
        maxOffspring[14] = 10;
        maxOffspring[15] = 10;
    }
    public int getMaxOffspring(int typeOfItem1) {
        return maxOffspring[typeOfItem1];
    }
    int[] maxSpeed = new int[16];
    {
        maxSpeed[0] = 3;
        maxSpeed[1] = 1;
        maxSpeed[2] = 2;
        maxSpeed[3] = 2;
        maxSpeed[4] = 3;
        maxSpeed[5] = 4;
        maxSpeed[6] = 4;
        maxSpeed[7] = 2;
        maxSpeed[8] = 1;
        maxSpeed[9] = 3;
        maxSpeed[10] = 3;
        maxSpeed[11] = 2;
        maxSpeed[12] = 3;
        maxSpeed[13] = 4;
        maxSpeed[14] = 0;
        maxSpeed[15] = 0;
    }
    public int getMaxSpeed(int typeOfItem1) {
        return maxSpeed[typeOfItem1];
    }
    int[] chanceToMove = new int[16];
    {
        chanceToMove[0] = 30;
        chanceToMove[1] = 10;
        chanceToMove[2] = 20;
        chanceToMove[3] = 20;
        chanceToMove[4] = 30;
        chanceToMove[5] = 40;
        chanceToMove[6] = 40;
        chanceToMove[7] = 20;
        chanceToMove[8] = 10;
        chanceToMove[9] = 30;
        chanceToMove[10] = 30;
        chanceToMove[11] = 20;
        chanceToMove[12] = 30;
        chanceToMove[13] = 40;
        chanceToMove[14] = 0;
        chanceToMove[15] = 0;
    }
    public int getChanceToMove(int typeOfItem1) {
        return chanceToMove[typeOfItem1];
    }
    public String getNameOfTypeByNumber(int i) {
        switch (i) {
            case 0:
                return "Wolf";
            case 1:
                return "Boa";
            case 2:
                return "Fox";
            case 3:
                return "Bear";
            case 4:
                return "Eagle";
            case 5:
                return "Horse";
            case 6:
                return "Deer";
            case 7:
                return "Rabbit";
            case 8:
                return "Mouse";
            case 9:
                return "Goat";
            case 10:
                return "Sheep";
            case 11:
                return "Hog";
            case 12:
                return "Buffalo";
            case 13:
                return "Duck";
            case 14:
                return "Caterpillar";
            default:
                return "Plant";
        }
    }
}
