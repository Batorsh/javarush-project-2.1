package ru.javarush.monkeyisland;

import ru.javarush.monkeyisland.constants.Constants;

import java.util.concurrent.Phaser;

public class Main {
    public static void main(String[] args) {
        Operator operator = new Operator(30,2,2);
        Thread thread = new Thread(operator);
        thread.start();
    }
}
