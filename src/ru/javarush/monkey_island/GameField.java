package ru.javarush.monkey_island;

import ru.javarush.monkey_island.constants.Constants;
import ru.javarush.monkey_island.items.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameField implements Runnable {

    HashMap<Integer, List<GameItem>> listOfItems = new HashMap<>();

    {
        for (int i = 1; i < 17; i++) {
            listOfItems.put(i, new ArrayList<>());
        }

        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 3 + ThreadLocalRandom.current().nextInt(Constants.MAX_ITEMS_ON_FIELD.get(i) / 3); j++) {
                listOfItems.get(i).add(createNewItem(i));
            }
        }
    }

    @Override
    public void run() {
        for (int i = 1; i < 17; i++) {
            for (GameItem gameItem : listOfItems.get(i)) {
                    tryToEat(gameItem);
            }
            System.out.println(listOfItems.get(i));
        }

    }

    public void tryToEat(GameItem gameItem){
        int typeOfItem = gameItem.getTYPE();
        double needFood = gameItem.getMAX_FOOD();
        for (int i = 1; i < 17; i++){

        }


    }

    GameItem createNewItem(int i) {
        switch (i) {
            case 1:
                return new Wolf();
            case 2:
                return new Boa();
            case 3:
                return new Fox();
            case 4:
                return new Bear();
            case 5:
                return new Eagle();
            case 6:
                return new Horse();
            case 7:
                return new Deer();
            case 8:
                return new Rabbit();
            case 9:
                return new Mouse();
            case 10:
                return new Goat();
            case 11:
                return new Sheep();
            case 12:
                return new Hog();
            case 13:
                return new Buffalo();
            case 14:
                return new Duck();
            case 15:
                return new Caterpillar();
            default:
                return new Plant();
        }

    }
}
