package ru.javarush.monkeyisland;

import ru.javarush.monkeyisland.items.GameItem;

public class TransferGameItem {
    GameItem gameItem;
    int y;
    int x;

    public TransferGameItem(GameItem gameItem, int y, int x) {
        this.gameItem = gameItem;
        this.y = y;
        this.x = x;
    }
}
