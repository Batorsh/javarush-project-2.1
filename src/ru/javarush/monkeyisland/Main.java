package ru.javarush.monkeyisland;


public class Main {
    public static void main(String[] args) {
        Operator operator = new Operator(30,10,10);
        Thread thread = new Thread(operator);
        thread.start();
    }
}
