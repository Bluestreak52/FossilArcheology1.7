package com.github.revival.server.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class IcedMeatItem extends ItemSword {
    public IcedMeatItem(ToolMaterial var2) {
        super(var2);
        this.setMaxDamage(500);
        this.setMaxStackSize(64);
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack var1, EntityLiving var2, EntityLiving var3) {
        var1.damageItem(1000, var3);
        return true;
    }

    public boolean onBlockDestroyed(ItemStack var1, int var2, int var3, int var4, int var5, EntityLiving var6) {
        var1.damageItem(1000, var6);
        return true;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("fossil:Iced_Meat");
    }
}
