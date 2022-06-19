package ru.javarush.monkeyisland;


public class Main {
    public static void main(String[] args) {
        Operator operator = new Operator(40,20,100);
        Thread thread = new Thread(operator);
        thread.start();
    }
}
