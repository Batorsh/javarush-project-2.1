package ru.javarush.monkeyisland;

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
    HashMap<Integer, List<GameItem>> mapOfItems = new HashMap<>();

    int days;

    private int x;
    private int y;

    GameField(Constants constants, FreeSpaceController freeSpaceController, Exchanger exchanger, int y, int x, int days, Phaser phaser) {
        this.freeSpaceController = freeSpaceController;
        this.exchanger = exchanger;
        this.constants = constants;
        this.days = days;
        this.x = x;
        this.y = y;
        this.phaser = phaser;
        this.phaser.register();

        for (int i = 0; i < constants.getAmountOfTypes(); i++) {
            mapOfItems.put(i, new ArrayList<>());
        }
        /**
         * В этом цикле заполняется Мапа игровых элементов случайным образом
         * каждый элемент помещается в лист, соответствующий своему типу.
         * четверть от максимально возможного числа элементов на поле заполняется всегда
         */
        for (int i = 0; i < constants.getAmountOfTypes(); i++) {
            int solidPart = constants.getMaxItemsOnField(i) / 4;
            int randomOfAmountOfTypes = ThreadLocalRandom.current().nextInt(constants.getMaxItemsOnField(i) - solidPart);
            for (int j = 0; j < solidPart + randomOfAmountOfTypes; j++) {
                mapOfItems.get(i).add(createNewItem(i));
            }
        }
    }

    @Override
    public void run() {
        for (int day = 1; day < days; day++) {
            /**
             * Ждем пока operator распределит переходящих животных
             */
            phaser.arriveAndAwaitAdvance();
            /**
             * Выводим статистику игрового поля поля (День, Номер поля, Игровые элементы)
             */
            printStatistics(day);
            phaser.arriveAndAwaitAdvance();
            /**
             * В цикле пробегаем по всем животным
             * Животное пробует съесть другой игровой элемент
             */
            eat();
            phaser.arriveAndAwaitAdvance();
            /**
             * В цикле пробегаем по всем животным
             * Каждое животное пробует размножиться
             */
            reproduce();
            phaser.arriveAndAwaitAdvance();
            /**
             * Отнимаем HP за день, если HP меньше 0, удалем его с поля
             */
            reduceHP();
            phaser.arriveAndAwaitAdvance();
            /**
             * Заполняем информацию о свободных местах на текущем поле
             */
            fillFreeSpaces();
            phaser.arriveAndAwaitAdvance();
            /**
             * Перебираем в цикле всех животных
             * Каждое животное пробует перейти на другое поле
             */
            moveAnotherField();
            phaser.arriveAndAwaitAdvance();
            if (day == days - 1) {
                phaser.arriveAndDeregister();
            }
        }
    }

    public void addGameItemToListOfItems(GameItem gameItem) {
        mapOfItems.get(gameItem.getType()).add(gameItem);
    }

    private void printStatistics(int day) {
        synchronized (constants) {
            System.out.print("Day " + day + " Field " + getX() + " " + getY() + ": ");
            for (int i = 0; i < constants.getAmountOfTypes(); i++) {
                System.out.print(constants.getNameOfTypeByNumber(i) + " " + mapOfItems.get(i).size() + "; ");
            }
            System.out.println();
        }
    }

    private void eat() {
        for (int i = 0; i < constants.getAmountOfTypes() - 1; i++) {//до 14, потому что трава все равно никого не ест
            for (int j = 0; j < mapOfItems.get(i).size(); j++) {
                tryToEat(mapOfItems.get(i).get(j));
            }
        }
    }

    public void tryToEat(GameItem gameItem) {
        List<Integer> listOfAvailableFood = new ArrayList<>();
        int typeOfItem = gameItem.getType();

        for (int i = 0; i < constants.getAmountOfTypes(); i++) {

            if (constants.chanceToEat(typeOfItem, i) > 0 && mapOfItems.get(i).size() > 0) {
                listOfAvailableFood.add(i);
            }
        }
        if (listOfAvailableFood.size() > 0) {
            int randomTypeForAttack = ThreadLocalRandom.current().nextInt(listOfAvailableFood.size());
            int pointsOfAttack = ThreadLocalRandom.current().nextInt(101);
            if (pointsOfAttack > 100 - constants.chanceToEat(typeOfItem, listOfAvailableFood.get(randomTypeForAttack))) {
                List<GameItem> killedList = mapOfItems.get(listOfAvailableFood.get(randomTypeForAttack));
                GameItem killedItem = killedList.remove(killedList.size() - 1);
                gameItem.setHealthPoint(killedItem.getWeight());
            }
        }
    }

    private void reproduce() {
        for (int i = 0; i < constants.getAmountOfTypes(); i++) {//
            /**
             * Если размножение произошло удачно, новые животные помещаются в newCreatedList
             */
            List<GameItem> newCreatedItems = new LinkedList<>();
            if (mapOfItems.get(i).size() < constants.getMaxItemsOnField(i)) {
                if (i != 15) {//Plant reproduce another way
                    for (GameItem gameItem : mapOfItems.get(i)) {
                        /**
                         * Получаем количество потомства в случае удачного исхода
                         */
                        int amountOfNewItems = tryToReproduce(gameItem);
                        if (amountOfNewItems > 0) {
                            if (amountOfNewItems + mapOfItems.get(i).size() + newCreatedItems.size() > constants.getMaxItemsOnField(i)) {
                                amountOfNewItems = constants.getMaxItemsOnField(i) - mapOfItems.get(i).size() - newCreatedItems.size();
                                for (int j = 0; j < amountOfNewItems; j++) {
                                    newCreatedItems.add(createNewItem(i));
                                }
                                break;
                            } else {
                                for (int j = 0; j < amountOfNewItems; j++) {//удалить
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
            mapOfItems.get(i).addAll(newCreatedItems);
        }
    }

    public int tryToReproduce(GameItem gameItem) {

        int typeOfItem = gameItem.getType();
        if (mapOfItems.get(typeOfItem).size() > 1) {
            int randomTypeForReproduce = ThreadLocalRandom.current().nextInt(101);
            if (randomTypeForReproduce > 100 - constants.chanceToReproduce(typeOfItem)) {
                int offspring = 1 + ThreadLocalRandom.current().nextInt(constants.getMaxOffspring(typeOfItem));
                return offspring;
            }
        }
        return 0;
    }

    private void reduceHP() {
        for (int i = 0; i < constants.getAmountOfTypes() - 2; i++) {//до 13, потому что трава и гусеница не умирают
            for (int j = 0; j < mapOfItems.get(i).size(); j++) {
                if (reduceHPbyDay(mapOfItems.get(i).get(j)) < 0) {
                    mapOfItems.remove(mapOfItems.get(i).get(j));
                }
            }
        }
    }

    public int reduceHPbyDay(GameItem gameItem) {
        gameItem.setHealthPoint(gameItem.getHealthPoint() - gameItem.getHealthPointsPerDay());
        return gameItem.getHealthPoint();
    }

    private void fillFreeSpaces() {
        List<Integer> freeSpacesOnField = new ArrayList<>();//можно переделать на массив
        for (int i = 0; i < constants.getAmountOfTypes() - 2; i++) {//до 13, потому что трава и гусеница не могут переходить
            freeSpacesOnField.add(i, constants.getMaxItemsOnField(i) - mapOfItems.get(i).size());
        }
        freeSpaceController.setFreeSpaces(freeSpacesOnField, getY(), getX());
    }

    private void moveAnotherField() {
        for (int i = 0; i < constants.getAmountOfTypes() - 2; i++) {//до 13, потому что трава и гусеница не могут переходить
            for (int j = 0; j < mapOfItems.get(i).size(); j++) {
                GameItem gameItem = mapOfItems.get(i).get(j);
                int randomTypeForReproduce = ThreadLocalRandom.current().nextInt(101);
                if (randomTypeForReproduce > 100 - constants.getChanceToMove(gameItem.getType())) {
                    synchronized (freeSpaceController) {
                        /**
                         * Здесь получаем список полей возможных для перехода,
                         * зависит от количества клеток, на которое может перейти животное,
                         * зависит от наличия свободных мест в поле для данного вида животных,
                         * учитываются границы острова
                         */
                        List<Coordinates> availableCoordinates = getAvailableFieldsToMove(gameItem);
                        if (availableCoordinates.size() > 0) {
                            int randomField = ThreadLocalRandom.current().nextInt(availableCoordinates.size());
                            /**
                             * Выбираем поле из возможных
                             * удаляем из Мапы текущего поля
                             * резервируем место в новом поле
                             */
                            int newY = availableCoordinates.get(randomField).getY();
                            int newX = availableCoordinates.get(randomField).getX();
                            exchanger.addTransferItem(new TransferGameItem(gameItem, newY, newX));
                            mapOfItems.get(i).remove(gameItem);
                            freeSpaceController.minusOneSpace(gameItem.getType(), newY, newX);
                        }
                    }
                }
            }
        }
    }

    List<Coordinates> getAvailableFieldsToMove(GameItem gameItem) {
        int steps = constants.getMaxSpeed(gameItem.getType());
        List<Coordinates> availableFields = new ArrayList<>();
        for (int i = -steps; i < steps + 1; i++) {
            int limit = steps - Math.abs(i);
            for (int j = -limit; j < limit + 1; j++) {
                if (!(i == 0 && j == 0)) {
                    int newY = getY() + i;
                    int newX = getX() + j;
                    if (newY >= 0 && newY < constants.getIslandWidth() &&
                            newX >= 0 && newX < constants.getIslandLength()) {
                        List<Integer> mapOfFreeSpaces = freeSpaceController
                                .getFreeSpaces(newY, newX);
                        if (mapOfFreeSpaces.get(gameItem.getType()) > 0) {
                            availableFields.add(new Coordinates(getY() + i, getX() + j));
                        }
                    }
                }
            }
        }
        return availableFields;
    }

    class Coordinates {
        int x;
        int y;

        public Coordinates(int y, int x) {
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
