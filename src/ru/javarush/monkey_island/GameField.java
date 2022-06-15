package ru.javarush.monkey_island;

import ru.javarush.monkey_island.constants.Constants;
import ru.javarush.monkey_island.items.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GameField implements Runnable {

    Constants constants;
    HashMap<Integer, Queue<GameItem>> listOfItems = new HashMap<>();

    GameField(Constants constants) {
        this.constants = constants;

        //Filling field by random creatures
        for (int i = 1; i < constants.getAmountOfTypes(); i++) {
            listOfItems.put(i, new LinkedList<>());
        }
        for (int i = 1; i < constants.getAmountOfTypes(); i++) {
            int solidPart = constants.getMaxItemsOnField(i) / 4;
            int randomOfAmountOfTypes = ThreadLocalRandom.current().nextInt(constants.getMaxItemsOnField(i) - solidPart);
            for (int j = 0; j < solidPart + randomOfAmountOfTypes; j++) {
                listOfItems.get(i).add(createNewItem(i));
            }
            System.out.println("TYPE " + i + " : " + listOfItems.get(i).size() + " : max on field " + constants.getMaxItemsOnField(i));
        }
    }

    @Override
    public void run() {
        for (int day = 1; day < 100; day++) {
            System.out.println("Day " + day);
            //Eating
            for (int i = 1; i < constants.getAmountOfTypes() - 1; i++) {//до 16, потому что трава все равно никого не ест
                for (GameItem gameItem : listOfItems.get(i)) {

                    tryToEat(gameItem);
                }
            }
            //Reproducing
            for (int i = 1; i < constants.getAmountOfTypes(); i++) {//
                Queue<GameItem> newCreatedItems = new LinkedList<>();
                if (listOfItems.get(i).size() < constants.getMaxItemsOnField(i)) {
                    if (i != 16) {
                        for (GameItem gameItem : listOfItems.get(i)) {

                            int amountOfNewItems = tryToReproduce(gameItem);
                            //System.out.println(amountOfNewItems);
                            if (amountOfNewItems > 0) {
                                if (amountOfNewItems + listOfItems.get(i).size() + newCreatedItems.size() > constants.getMaxItemsOnField(i)) {
                                    amountOfNewItems = constants.getMaxItemsOnField(i) - listOfItems.get(i).size() - newCreatedItems.size();
                                    for (int j = 0; j < amountOfNewItems; j++) {
                                        newCreatedItems.add(createNewItem(i));
                                    }
                                    break;
                                } else {
                                    for (int j = 0; j < amountOfNewItems; j++) {
                                        newCreatedItems.add(createNewItem(i));
                                    }
                                }
                            }
                        }
                    } else {
                        for (int j = 0; j < 100; j++) {
                            newCreatedItems.add(createNewItem(i));
                        }
                    }
                }
                listOfItems.get(i).addAll(newCreatedItems);
                System.out.println("TYPE " + i + " was created = " + newCreatedItems.size());
                System.out.println("TYPE " + i + " : " + listOfItems.get(i).size() + " : max on field " + constants.getMaxItemsOnField(i));
            }
            //ReducingHP and Abandon Island
            for (int i = 1; i < constants.getAmountOfTypes() - 2; i++) {//до 15, потому что трава и гусеница не умирают
                for (GameItem gameItem : listOfItems.get(i)) {
                    if(reduceHPbyDay(gameItem) < 0) {
                        System.out.println(gameItem.getClass().getSimpleName() + " " + gameItem.hashCode() + " abandoned");
                        listOfItems.remove(gameItem);
                    }
                }
            }
        }
    }

    public void tryToEat(GameItem gameItem) {
        List<Integer> listOfAvailableFood = new ArrayList<>();
        int typeOfItem = gameItem.getTYPE();

        for (int i = 1; i < constants.getAmountOfTypes(); i++) {
            if (constants.chanceToEat(typeOfItem, i) > 0 && listOfItems.get(i).size() > 0) {
                listOfAvailableFood.add(i);
            }
        }
        if (listOfAvailableFood.size() > 0) {
            int randomTypeForAttack = ThreadLocalRandom.current().nextInt(listOfAvailableFood.size());
            int pointsOfAttack = ThreadLocalRandom.current().nextInt(101);
            if (pointsOfAttack > 100 - constants.chanceToEat(typeOfItem, listOfAvailableFood.get(randomTypeForAttack))) {
                GameItem killedItem = listOfItems.get(listOfAvailableFood.get(randomTypeForAttack)).poll();
                System.out.print(pointsOfAttack + " " + gameItem.getClass().getSimpleName() + " " + gameItem.hashCode() + " eats " + killedItem.getClass().getSimpleName());
                gameItem.setHealthPoint(killedItem.getWEIGHT());
                System.out.println(" and gets " + killedItem.getWEIGHT() + " health points");
            }
        }
    }

    public int tryToReproduce(GameItem gameItem) {

        int typeOfItem = gameItem.getTYPE();
        if (listOfItems.get(typeOfItem).size() > 1) {
            int randomTypeForReproduce = ThreadLocalRandom.current().nextInt(101);
            if (randomTypeForReproduce > 100 - constants.chanceToReproduce(typeOfItem)) {
                int offspring = 1 + ThreadLocalRandom.current().nextInt(constants.getMaxOffspring(typeOfItem));
                return offspring;
            }
        }
        return 0;
    }

    public int reduceHPbyDay(GameItem gameItem){
        gameItem.setHealthPoint(gameItem.getHealthPoint() - gameItem.getHealthPointsPerDay());
        return gameItem.getHealthPoint();
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
