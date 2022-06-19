package ru.javarush.monkeyisland;

import org.w3c.dom.ls.LSOutput;
import ru.javarush.monkeyisland.constants.Constants;
import ru.javarush.monkeyisland.items.*;


import java.util.*;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;

public class GameField implements Runnable {
    Constants constants;
    FreeSpaceController freeSpaceController;
    Exchanger exchanger;
    Phaser phaser;
    HashMap<Integer, List<GameItem>> listOfItems = new HashMap<>();

    int days;

    private int x;
    private int y;

    GameField(Constants constants, FreeSpaceController freeSpaceController, Exchanger exchanger, int x, int y, int days, Phaser phaser) {
        this.freeSpaceController = freeSpaceController;
        this.exchanger = exchanger;
        this.constants = constants;
        this.days = days;
        this.x = x;
        this.y = y;
        this.phaser = phaser;
        this.phaser.register();
        //Filling field by random creatures
        for (int i = 0; i < constants.getAmountOfTypes(); i++) {
            listOfItems.put(i, new ArrayList<>());
        }
        for (int i = 0; i < constants.getAmountOfTypes(); i++) {
            int solidPart = constants.getMaxItemsOnField(i) / 4;
            int randomOfAmountOfTypes = ThreadLocalRandom.current().nextInt(constants.getMaxItemsOnField(i) - solidPart);
            for (int j = 0; j < solidPart + randomOfAmountOfTypes; j++) {
                listOfItems.get(i).add(createNewItem(i));
            }
            //System.out.println("Field " + getX() + " " + getY() + " TYPE " + i + " : " + listOfItems.get(i).size() + " : max on field " + constants.getMaxItemsOnField(i));
        }
    }

    @Override
    public void run() {
        for (int day = 1; day < days; day++) {
            //Transferring Items from another fields
            //System.out.println("Field №" + getY() + " " + getX() + " выполняет фазу трансфера фигур из других полей " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            //Output statistics
            synchronized (constants) {
                System.out.print("Day " + day + " Field " + getX() + " " + getY() + ": ");
                for (int i = 0; i < constants.getAmountOfTypes(); i++) {
                    System.out.print(constants.getNameOfTypeByNumber(i) + " " + listOfItems.get(i).size() + "; ");
                }
                System.out.println();
            }
            //System.out.println("Field №" + getY() + " " + getX() + " выполняет фазу вывод статистики " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            //Eating
            for (int i = 0; i < constants.getAmountOfTypes() - 1; i++) {//до 14, потому что трава все равно никого не ест
                for (int j = 0; j < listOfItems.get(i).size(); j++) {
                    tryToEat(listOfItems.get(i).get(j));
                }
            }
            //System.out.println("Field №" + getY() + " " + getX() + " выполняет фазу поедания " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            //Reproducing
            for (int i = 0; i < constants.getAmountOfTypes(); i++) {//
                List<GameItem> newCreatedItems = new LinkedList<>();
                if (listOfItems.get(i).size() < constants.getMaxItemsOnField(i)) {
                    if (i != 15) {//Plant reproduce another way
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
            //System.out.println("Field №" + getY() + " " + getX() + " выполняет фазу репродукции " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            //ReducingHP and Abandon Island
            for (int i = 0; i < constants.getAmountOfTypes() - 2; i++) {//до 13, потому что трава и гусеница не умирают
                for (int j = 0; j < listOfItems.get(i).size(); j++) {
                    if (reduceHPbyDay(listOfItems.get(i).get(j)) < 0) {
                        //System.out.println("Field " + getX() + " " + getY() + " " + gameItem.getClass().getSimpleName() + " " + gameItem.hashCode() + " abandoned");
                        listOfItems.remove(listOfItems.get(i).get(j));
                    }
                }
            }
            //System.out.println("Field №" + getY() + " " + getX() + " выполняет фазу уменьшения HP и удаление c HP < 0" + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            //Sending information about free spaces for creatures

            List<Integer> freeSpacesOnField = new ArrayList<>();//можно переделать на массив
            for (int i = 0; i < constants.getAmountOfTypes() - 2; i++) {//до 13, потому что трава и гусеница не могут переходить

                freeSpacesOnField.add(i, constants.getMaxItemsOnField(i) - listOfItems.get(i).size());

            }
            //System.out.println(freeSpacesOnField + " " + getY() + " " + getX());
            freeSpaceController.setFreeSpaces(freeSpacesOnField, getY(), getX());

            //System.out.println("Field №" + getY() + " " + getX() + " выполняет фазу записи свободных мест " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            //Moving another field
            for (int i = 0; i < constants.getAmountOfTypes() - 2; i++) {//до 13, потому что трава и гусеница не могут переходить
                List<GameItem> listForRemove = new ArrayList<>();
                for (int j = 0; j < listOfItems.get(i).size(); j++) {
                    GameItem gameItem = listOfItems.get(i).get(j);
                    int randomTypeForReproduce = ThreadLocalRandom.current().nextInt(101);
                    if (randomTypeForReproduce > 100 - constants.getChanceToMove(gameItem.getType())) {
                        List<Coordinates> availableCoordinates = getAvailableFieldsToMove(gameItem);
                        if (availableCoordinates.size() > 0) {
                            int randomField = ThreadLocalRandom.current().nextInt(availableCoordinates.size());
                            int newY = availableCoordinates.get(randomField).getY();
                            int newX = availableCoordinates.get(randomField).getX();
                            exchanger.addTransferItem(new TransferGameItem(gameItem, newY, newX));
                            listOfItems.get(i).remove(gameItem);
                        }
                    }

                }

            }
            //System.out.println("Field №" + getY() + " " + getX() + " выполняет фазу трансфера фигур " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            /*if (day == 29) {
                for (TransferGameItem transferGameItem : exchanger.getQueueOfTransferredItems()) {
                    System.out.println(transferGameItem.getGameItem().getClass().getSimpleName() + " new field is: y = " + transferGameItem.getY() +
                            ", x = " + transferGameItem.getX());
                }
                System.out.println("Exchanger " + exchanger.getQueueOfTransferredItems());
            }*/
        }

    }

    public void tryToEat(GameItem gameItem) {
        List<Integer> listOfAvailableFood = new ArrayList<>();
        int typeOfItem = gameItem.getType();

        for (int i = 0; i < constants.getAmountOfTypes(); i++) {

            if (constants.chanceToEat(typeOfItem, i) > 0 && listOfItems.get(i).size() > 0) {
                listOfAvailableFood.add(i);
            }
        }
        if (listOfAvailableFood.size() > 0) {
            int randomTypeForAttack = ThreadLocalRandom.current().nextInt(listOfAvailableFood.size());
            int pointsOfAttack = ThreadLocalRandom.current().nextInt(101);
            if (pointsOfAttack > 100 - constants.chanceToEat(typeOfItem, listOfAvailableFood.get(randomTypeForAttack))) {
                List<GameItem> killedList = listOfItems.get(listOfAvailableFood.get(randomTypeForAttack));
                GameItem killedItem = killedList.remove(killedList.size() - 1);
                //System.out.print("Field " + getX() + " " + getY() + " " + pointsOfAttack + " " + gameItem.getClass().getSimpleName() + " " + gameItem.hashCode() + " eats " + killedItem.getClass().getSimpleName());
                gameItem.setHealthPoint(killedItem.getWeight());
                //killedList.remove(killedItem);
                //System.out.println(" and gets " + killedItem.getWEIGHT() + " health points");
            }
        }
    }

    public int tryToReproduce(GameItem gameItem) {

        int typeOfItem = gameItem.getType();
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

    class Coordinates {
        int x;
        int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public void addGameItemToListOfItems(GameItem gameItem) {
        listOfItems.get(gameItem.getType()).add(gameItem);
    }

    List<Coordinates> getAvailableFieldsToMove(GameItem gameItem) {
        int steps = constants.getMaxSpeed(gameItem.getType());
        List<Coordinates> availableFields = new ArrayList<>();
        for (int i = -steps; i < steps + 1; i++) {
            int limit = steps - Math.abs(i);
            for (int j = -limit; j < limit + 1; j++) {
                if (!(i == 0 && j == 0)) {
                    if (getY() + i >= 0 && getY() + i < constants.getIslandWidth() &&
                            getX() + j >= 0 && getX() + j < constants.getIslandLength()) {
                        List<Integer> mapOfFreeSpaces = freeSpaceController
                                .getFreeSpaces(getY() + i, getX() + j);
                        /*for (int t = 0; t < mapOfFreeSpaces.size(); t++) {
                            System.out.print(mapOfFreeSpaces.get(t));
                        }*/

                        if (mapOfFreeSpaces.get(gameItem.getType()) > 0) {
                            availableFields.add(new Coordinates(getY() + i, getX() + j));
                        }

                    }
                }
            }
        }
        return availableFields;
    }

    GameItem createNewItem(int i) {
        switch (i) {
            case 0:
                return new Wolf();
            case 1:
                return new Boa();
            case 2:
                return new Fox();
            case 3:
                return new Bear();
            case 4:
                return new Eagle();
            case 5:
                return new Horse();
            case 6:
                return new Deer();
            case 7:
                return new Rabbit();
            case 8:
                return new Mouse();
            case 9:
                return new Goat();
            case 10:
                return new Sheep();
            case 11:
                return new Hog();
            case 12:
                return new Buffalo();
            case 13:
                return new Duck();
            case 14:
                return new Caterpillar();
            default:
                return new Plant();
        }
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
