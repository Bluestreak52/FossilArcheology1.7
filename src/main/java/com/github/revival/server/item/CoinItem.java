package com.github.revival.server.item;

import com.github.revival.server.enums.EnumCoinType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CoinItem extends Item {
    public static final int BASEICONLOC = 45;
    private EnumCoinType delfWorldType = null;

    protected CoinItem(EnumCoinType var2) {
        super();
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.maxStackSize = 64;
        this.delfWorldType = var2;
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    //public int getIconFromDamage(int var1)
    //{
    //    return 45 + var1;
    //}
    public String getItemNameIS(ItemStack var1) {
        return "coins";
    }

    public int getTargetDimension() {
        return this.delfWorldType.targetDimension;
    }
}
