package ru.javarush.monkey_island;

import ru.javarush.monkey_island.constants.Constants;
import ru.javarush.monkey_island.items.GameItem;

import java.util.HashMap;
import java.util.List;

public class GameField implements Runnable{

    HashMap<Integer, List<GameItem>> listOfItems = new HashMap<>();

    HashMap<Integer, Integer> MAX_ITEMS_ON_FIELD = Constants.MAX_ITEMS_ON_FIELD;


    @Override
    public void run() {

    }
}
