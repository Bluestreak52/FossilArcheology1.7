package com.github.revival.server.item;

import com.github.revival.Revival;
import com.github.revival.server.creativetab.FATabRegistry;
import com.github.revival.server.enums.EnumDinoBones;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class DinosaurBoneItem extends Item {

    public IIcon[] icons = new IIcon[EnumDinoBones.values().length];
    String itemType;

    public DinosaurBoneItem(String _itemType) {
        super();
        this.itemType = _itemType;
        setMaxDamage(0);

        this.setCreativeTab(FATabRegistry.INSTANCE.tabFItems);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return getUnlocalizedName() + "." + EnumDinoBones.values()[itemstack.getItemDamage()].name();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return icons[damage];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister icon) {
        for (int i = 0; i < icons.length; i++) {
            icons[i] = icon.registerIcon(Revival.MODID + ":" + "dinosaur_bones/" + this.itemType + "/" + EnumDinoBones.values()[i] + "_" + this.itemType);
        }
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < icons.length; i++) {
            ItemStack itemstack = new ItemStack(item, 1, i);
            list.add(itemstack);
        }
    }

}
