package ru.javarush.monkeyisland;

import ru.javarush.monkeyisland.items.GameItem;

public class TransferGameItem {


    private GameItem gameItem;
    private int y;
    private int x;

    public TransferGameItem(GameItem gameItem, int y, int x) {
        this.gameItem = gameItem;
        this.y = y;
        this.x = x;
    }
    public GameItem getGameItem() {
        return gameItem;
    }
    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }
}
