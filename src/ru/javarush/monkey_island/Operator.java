package ru.javarush.monkey_island;

import java.lang.reflect.Field;


public class Operator {
    int ISLAND_LENGTH = 100;
    int ISLAND_WIDTH = 20;

    GameField[][] fields = new GameField[ISLAND_WIDTH][ISLAND_LENGTH];

    public void start() {
        for (int i = 0; i < ISLAND_WIDTH; i++) {
            for (int j = 0; j < ISLAND_LENGTH; j++) {
                fields[i][j] = new GameField();
            }
        }
    }


}
