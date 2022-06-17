package ru.javarush.monkey_island;

import ru.javarush.monkey_island.constants.Constants;
import ru.javarush.monkey_island.items.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GameField implements Runnable {

    Constants constants;
    HashMap<Integer, Queue<GameItem>> listOfItems = new HashMap<>();

    int x;
    int y;

    GameField(Constants constants, int x, int y) {
        this.constants = constants;
        this.x = x;
        this.y = y;

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
            System.out.println("Field " + getX() + " " + getY() + " TYPE " + i + " : " + listOfItems.get(i).size() + " : max on field " + constants.getMaxItemsOnField(i));
        }
    }

    @Override
    public void run() {
        for (int day = 1; day < 30; day++) {
            //Output statistics
            synchronized (constants) {
                System.out.print("Field " + getX() + " " + getY() + " Day " + day + ": ");
                for (int i = 1; i < constants.getAmountOfTypes(); i++) {
                    System.out.print(getTypeByNumber(i) + " " + listOfItems.get(i).size() + "; ");
                }
                System.out.println();
            }
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
                //System.out.println("Field " + getX() + " " + getY() + " " + getTypeByNumber(i) + " was created = " + newCreatedItems.size());
                //System.out.println("Field " + getX() + " " + getY() + " " + getTypeByNumber(i) + " : " + listOfItems.get(i).size() + " : max on field " + constants.getMaxItemsOnField(i));
            }
            //ReducingHP and Abandon Island
            for (int i = 1; i < constants.getAmountOfTypes() - 2; i++) {//до 15, потому что трава и гусеница не умирают
                for (GameItem gameItem : listOfItems.get(i)) {
                    if (reduceHPbyDay(gameItem) < 0) {
                        //System.out.println("Field " + getX() + " " + getY() + " " + gameItem.getClass().getSimpleName() + " " + gameItem.hashCode() + " abandoned");
                        listOfItems.remove(gameItem);
                    }
                }
            }
            //Moving another field
            for (int i = 1; i < constants.getAmountOfTypes() - 2; i++) {//до 14, потому что трава и гусеница не могут переходить
                for (GameItem gameItem : listOfItems.get(i)) {
                    tryToMoveToAnotherField(gameItem);
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
                //System.out.print("Field " + getX() + " " + getY() + " " + pointsOfAttack + " " + gameItem.getClass().getSimpleName() + " " + gameItem.hashCode() + " eats " + killedItem.getClass().getSimpleName());
                gameItem.setHealthPoint(killedItem.getWEIGHT());
                //System.out.println(" and gets " + killedItem.getWEIGHT() + " health points");
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

    public int reduceHPbyDay(GameItem gameItem) {
        gameItem.setHealthPoint(gameItem.getHealthPoint() - gameItem.getHealthPointsPerDay());
        return gameItem.getHealthPoint();
    }

    void tryToMoveToAnotherField(GameItem gameItem) {
        int typeOfItem = gameItem.getTYPE();
        int randomTypeForReproduce = ThreadLocalRandom.current().nextInt(101);
        if (randomTypeForReproduce > 100 - constants.getChanceToMove(typeOfItem)) {
            List<Integer> availableDirections = getAvailableDirection();
            if (availableDirections.size() > 0) {
                //System.out.println("Field " + getX() + " " + getY() + " " + gameItem.getClass().getSimpleName() + " has available directions");
            }
        }
    }

    List<Integer> getAvailableDirection() {
        List<Integer> availableDirections = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            if (checkAvailable(i)) {
                availableDirections.add(i);
            }
        }
        return availableDirections;
    }

    boolean checkAvailable(int direction) {
        int alpha;
        if (direction == 1 || direction == 3) {
            alpha = getX();
            if (direction == 1 && alpha == 0) {
                return false;
            }
            if (direction == 3 && alpha == constants.getISLAND_LENGTH()) {
                return false;
            }
            return true;

        }
        if (direction == 2 || direction == 4) {
            alpha = getX();
            if (direction == 2 && alpha == 0) {
                return false;
            }
            if (direction == 4 && alpha == constants.getISLAND_WIDTH()) {
                return false;
            }
            return true;
        }
        return false;
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

    String getTypeByNumber(int i) {
        switch (i) {
            case 1:
                return "Wolf";
            case 2:
                return "Boa";
            case 3:
                return "Fox";
            case 4:
                return "Bear";
            case 5:
                return "Eagle";
            case 6:
                return "Horse";
            case 7:
                return "Deer";
            case 8:
                return "Rabbit";
            case 9:
                return "Mouse";
            case 10:
                return "Goat";
            case 11:
                return "Sheep";
            case 12:
                return "Hog";
            case 13:
                return "Buffalo";
            case 14:
                return "Duck";
            case 15:
                return "Caterpillar";
            default:
                return "Plant";
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
