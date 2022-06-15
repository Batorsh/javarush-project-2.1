package ru.javarush.monkey_island;

import ru.javarush.monkey_island.constants.Constants;
import ru.javarush.monkey_island.items.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GameField implements Runnable {

    Constants constants;
    HashMap<Integer, Queue<GameItem>> listOfItems = new HashMap<>();

    GameField(Constants constants){
        this.constants = constants;
    }

    {
        for (int i = 1; i < 17; i++) {
            listOfItems.put(i, new LinkedList<>());
        }

        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 3 + ThreadLocalRandom.current().nextInt(constants.MAX_ITEMS_ON_FIELD.get(i) / 2); j++) {
                listOfItems.get(i).add(createNewItem(i));
            }
        }
    }

    @Override
    public void run() {
        for (int day = 1; day < 100; day++) {
            for (int i = 1; i < 17; i++) {//до 16, потому что трава все равно никого не ест
                for (GameItem gameItem : listOfItems.get(i)) {
                    tryToEat(gameItem);
                }
            }
            for (int i = 1; i < 17; i++) {
                Queue<GameItem> newCreatedItems = new LinkedList<>();
                for (GameItem gameItem : listOfItems.get(i)) {
                    int amountOfNewItems = tryToReproduce(gameItem);
                    if (amountOfNewItems > 0) {
                        for (int j = 0; j < amountOfNewItems; j++) {
                            newCreatedItems.add(createNewItem(i));
                        }
                    }
                }
                listOfItems.get(i).addAll(newCreatedItems);
                System.out.println("Type " + i + " was created = " + newCreatedItems.size());
                System.out.println("TYPE " + i + " : " + listOfItems.get(i).size());
            }
        }
    }

    public void tryToEat(GameItem gameItem) {
        List<Integer> listOfAvailableFood = new ArrayList<>();
        int typeOfItem = gameItem.getTYPE();
        double needFood = gameItem.getMAX_FOOD();
        for (int i = 1; i < 17; i++) {
            if (constants.chanceToEat(typeOfItem, i) > 0 && listOfItems.get(i).size() > 0) {
                listOfAvailableFood.add(i);
            }
        }

        if (listOfAvailableFood.size() > 0) {
            int randomTypeForAttack = ThreadLocalRandom.current().nextInt(listOfAvailableFood.size());
            int pointsOfAttack = ThreadLocalRandom.current().nextInt(101);
            if (pointsOfAttack > 100 - constants.chanceToEat(typeOfItem, listOfAvailableFood.get(randomTypeForAttack))) {
                System.out.println(pointsOfAttack + " " + gameItem.getClass().getSimpleName() + " Ест " +listOfItems.get(listOfAvailableFood.get(randomTypeForAttack)).poll().getClass().getSimpleName());
            }
        }
    }

    public int tryToReproduce(GameItem gameItem) {
        int typeOfItem = gameItem.getTYPE();
        if (listOfItems.get(typeOfItem).size() > 1) {
            int randomTypeForReproduce = ThreadLocalRandom.current().nextInt(101);
            if (randomTypeForReproduce > 100 - constants.chanceToReproduce(typeOfItem)){
                return 1;
            }
        }
        return 0;
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
