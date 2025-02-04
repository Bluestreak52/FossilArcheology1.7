package com.github.revival.server.enums;

import java.util.Random;

public enum EnumShipTypes {
    Science(0),
    Battleship(1),
    Cargo(2),
    MetalTrader(3);
    private final int MetaData;

    EnumShipTypes(int var3) {
        this.MetaData = var3;
    }

    public static EnumShipTypes GetRandom(Random var0) {
        int var1 = var0.nextInt(values().length);
        return values()[var1];
    }

    public int getMetaData() {
        return this.MetaData;
    }
}
