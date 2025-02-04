package com.github.revival.server.block.entity;

import com.github.revival.Revival;
import com.github.revival.server.block.BlockAnalyzer;
import com.github.revival.server.block.FABlockRegistry;
import com.github.revival.server.enums.EnumPrehistoric;
import com.github.revival.server.handler.FossilAchievementHandler;
import com.github.revival.server.handler.LocalizationStrings;
import com.github.revival.server.item.FAItemRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

public class TileEntityAnalyzer extends TileEntity implements IInventory,
        ISidedInventory {

    private static final int[] slots_top = new int[]{}; // input
    private static final int[] slots_bottom = new int[]{10, 11, 12}; // output
    private static final int[] slots_sides = new int[]{0, 1, 2, 3, 4, 5, 6,
            7, 8};//fuel
    public int analyzerBurnTime = 0;
    public int currentItemBurnTime = 100;
    public int analyzerCookTime = 0;
    private String customName;
    private ItemStack[] analyzerItemStacks;
    private int RawIndex = -1;
    private int SpaceIndex = -1;

    public TileEntityAnalyzer() {
        analyzerItemStacks = new ItemStack[13];
    }

    private static int getItemBurnTime(ItemStack var1) {
        return 100;
    }

    /**
     * Return true if item is a fuel source (getItemBurnTime() > 0).
     */
    public static boolean isItemFuel(ItemStack par0ItemStack) {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {
        return this.analyzerItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    @Override
    public ItemStack getStackInSlot(int var1) {
        return this.analyzerItemStacks[var1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number
     * (second arg) of items and returns them in a new stack.
     */
    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        if (this.analyzerItemStacks[var1] != null) {
            ItemStack var3;

            if (this.analyzerItemStacks[var1].stackSize <= var2) {
                var3 = this.analyzerItemStacks[var1];
                this.analyzerItemStacks[var1] = null;
                return var3;
            } else {
                var3 = this.analyzerItemStacks[var1].splitStack(var2);

                if (this.analyzerItemStacks[var1].stackSize == 0) {
                    this.analyzerItemStacks[var1] = null;
                }

                return var3;
            }
        } else {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be
     * crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
        this.analyzerItemStacks[var1] = var2;

        if (var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
            var2.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.customName : "tile."
                + LocalizationStrings.BLOCK_ANALYZER_IDLE_NAME + ".name";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return this.customName != null && this.customName.length() > 0;
    }

    /**
     * Sets the custom display name to use when opening a GUI linked to this
     * tile entity.
     */
    public void setGuiDisplayName(String par1Str) {
        this.customName = par1Str;
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound var1) {
        super.readFromNBT(var1);
        NBTTagList var2 = var1.getTagList("Items", 10);
        this.analyzerItemStacks = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
            NBTTagCompound var4 = (NBTTagCompound) var2.getCompoundTagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.analyzerItemStacks.length) {
                this.analyzerItemStacks[var5] = ItemStack
                        .loadItemStackFromNBT(var4);
            }
        }

        this.analyzerBurnTime = var1.getShort("BurnTime");
        this.analyzerCookTime = var1.getShort("CookTime");
        this.currentItemBurnTime = 100;

        if (var1.hasKey("CustomName")) {
            this.customName = var1.getString("CustomName");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound var1) {
        super.writeToNBT(var1);
        var1.setShort("BurnTime", (short) this.analyzerBurnTime);
        var1.setShort("CookTime", (short) this.analyzerCookTime);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.analyzerItemStacks.length; ++var3) {
            if (this.analyzerItemStacks[var3] != null) {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte) var3);
                this.analyzerItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        if (this.hasCustomInventoryName()) {
            var1.setString("CustomName", this.customName);
        }

        var1.setTag("Items", var2);
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be
     * 64, possibly will be extended. *Isn't this more of a set than a get?*
     */
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    public int getAnalyzeProgressScaled(int var1) {
        return this.analyzerCookTime * var1 / 200;
    }

    public int getBurnTimeRemainingScaled(int var1) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = 100;
        }

        return this.analyzerBurnTime * var1 / this.currentItemBurnTime;
    }

    public boolean isBurning() {
        return this.analyzerBurnTime > 0;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses,
     * e.g. the mob spawner uses this to count ticks and creates a new spawn
     * inside its implementation.
     */
    @Override
    public void updateEntity() {
        int slots;
        //System.out.println(EnumPrehistoric.getRandomMezoic());

        for (int var7 = 0; var7 < worldObj.playerEntities.size(); ++var7) {
            EntityPlayer P = (EntityPlayer) worldObj.playerEntities.get(var7);

            if (Math.pow(this.xCoord - P.posX, 2D) + Math.pow(this.yCoord - P.posY, 2D) + Math.pow(this.zCoord - P.posZ, 2D) < 40) {
                for (slots = 12; slots > 8; --slots) {
                    if (this.analyzerItemStacks[slots] != null) {
                        if (this.analyzerItemStacks[slots].getItem() != null) {
                            if (this.analyzerItemStacks[slots].getItem() == FAItemRegistry.INSTANCE.stoneboard) {
                                P.addStat(FossilAchievementHandler.tablet, 1);
                            }
                            //if (Revival.isDNA(this.analyzerItemStacks[slots].getItem())) {
                            //	P.addStat(FossilAchievementHandler.dinoDna, 1);
                            //}
                        }
                    }

                }
            }
        }
        boolean var1 = this.analyzerBurnTime > 0;
        boolean var2 = false;

        if (this.analyzerBurnTime > 0) {
            --this.analyzerBurnTime;
        }

        if (!this.worldObj.isRemote) {
            if (this.analyzerBurnTime == 0 && this.canSmelt()) {
                this.currentItemBurnTime = this.analyzerBurnTime = 100;

                if (this.analyzerBurnTime > 0) {
                    var2 = true;
                }
            }

            if (this.isBurning() && this.canSmelt()) {
                ++this.analyzerCookTime;

                if (this.analyzerCookTime == 200) {
                    this.analyzerCookTime = 0;
                    this.smeltItem();
                    var2 = true;
                }
            } else {
                this.analyzerCookTime = 0;
            }

            if (var1 != this.analyzerBurnTime > 0) {
                var2 = true;
                BlockAnalyzer.updateFurnaceBlockState(
                        this.analyzerBurnTime > 0, this.worldObj, this.xCoord,
                        this.yCoord, this.zCoord);
            }
        }

        if (var2) {
            this.markDirty();
        }
    }

    private boolean canSmelt() {
        this.SpaceIndex = -1;
        this.RawIndex = -1;
        int var1;

        for (var1 = 0; var1 < 9; ++var1) {
            if (this.analyzerItemStacks[var1] != null) {
                Item var2 = this.analyzerItemStacks[var1].getItem();

                if (EnumPrehistoric.isFoodItem(this.analyzerItemStacks[var1]
                        .getItem())
                        || (var2 == FAItemRegistry.INSTANCE.biofossil)
                        || (var2 == FAItemRegistry.INSTANCE.tarfossil)
                        || (var2 == FAItemRegistry.INSTANCE.tardrop)
                        || (var2 == FAItemRegistry.INSTANCE.failuresaurusFlesh)
                        || (var2 == FAItemRegistry.INSTANCE.relic)
                        || (var2 == Items.porkchop)
                        || (var2 == Items.beef)
                        || (var2 == Items.egg)
                        || (var2 == Items.chicken)
                        || (var2 == Item.getItemFromBlock(Blocks.wool))
                        || (var2 == FAItemRegistry.INSTANCE.icedMeat)
                        || (var2 == Items.leather)
                        || (var2 == FAItemRegistry.INSTANCE.brokenSapling)) {
                    this.RawIndex = var1;
                    break;
                }
            }
        }

        if (this.RawIndex == -1) {
            return false;
        } else {
            for (var1 = 12; var1 > 8; --var1) {
                if (this.analyzerItemStacks[var1] == null) {
                    this.SpaceIndex = var1;
                    break;
                }
            }

            return this.SpaceIndex != -1 && this.RawIndex != -1;
        }
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemstack = null;
            int rand = new Random().nextInt(100);
            int var3;

            if (this.analyzerItemStacks[this.RawIndex].getItem() == FAItemRegistry.INSTANCE.biofossil) {

                if (!Revival.enableDebugging()) {
                    if (rand > -1 && rand <= 50) {
                        itemstack = new ItemStack(Items.dye, 3, 15);
                    }

                    if (rand > 50 && rand <= 85) {
                        itemstack = new ItemStack(Blocks.sand, 3);
                    }

                    if (rand > 85) {
                        itemstack = new ItemStack(EnumPrehistoric.getRandomMezoic().DNAItem, 1);
                    }
                } else {
                    itemstack = new ItemStack(EnumPrehistoric.getRandomMezoic().DNAItem, 1);
                    System.out.println(EnumPrehistoric.getRandomMesozoicDNA().getUnlocalizedName());
                }
            }
            if (this.analyzerItemStacks[this.RawIndex].getItem() == FAItemRegistry.INSTANCE.tarfossil) {

                if (rand > -1 && rand <= 50) {
                    itemstack = new ItemStack(Items.dye, 3, 15);
                }

                if (rand > 50 && rand <= 80) {
                    itemstack = new ItemStack(FABlockRegistry.INSTANCE.volcanicRock, 1);
                }

                if (rand > 80 && rand <= 85) {
                    itemstack = new ItemStack(Blocks.obsidian, 1);
                }
                if (rand > 85) {
                    itemstack = new ItemStack(EnumPrehistoric.getRandomCenozoic().DNAItem, 1);
                }

            }
            if (this.analyzerItemStacks[this.RawIndex].getItem() == FAItemRegistry.INSTANCE.tardrop) {

                if (rand >= 0 && rand <= 40) {
                    itemstack = new ItemStack(Items.coal, new Random().nextInt(2) + 1, new Random().nextInt(1));
                }
                if (rand > 40 && rand <= 85) {
                    itemstack = new ItemStack(FAItemRegistry.INSTANCE.tarfossil, 1);
                }

                if (rand > 85) {
                    itemstack = new ItemStack(FABlockRegistry.INSTANCE.volcanicRock, 1);
                }

            }
            if (this.analyzerItemStacks[this.RawIndex].getItem() == FAItemRegistry.INSTANCE.brokenSapling) {
                if (rand > 0) {
                    itemstack = new ItemStack(Blocks.sand, 2, 0);
                }
                if (rand > 35 && rand <= 65) {
                    itemstack = new ItemStack(Items.coal, 1, 0);
                }
                if (rand > 65 && rand <= 75) {
                    itemstack = new ItemStack(FAItemRegistry.INSTANCE.palaeSaplingFossil, 1, 0);
                }
                if (rand > 75 && rand <= 85) {
                    itemstack = new ItemStack(Items.dye, 1, 2);
                }
                if (rand > 85) {
                    itemstack = new ItemStack(FAItemRegistry.INSTANCE.fossilSeed, 1, new Random().nextInt(9));
                }

            }

            if (this.analyzerItemStacks[this.RawIndex].getItem() == Item
                    .getItemFromBlock(Blocks.wool)) {
                if ((new Random()).nextInt(50) <= 30) {
                    itemstack = new ItemStack(Items.string, 4);
                } else {
                    itemstack = new ItemStack(EnumPrehistoric.Sheep.DNAItem, 1);
                }
            }

			/*
             * if (this.analyzerItemStacks[this.RawIndex].getItem() ==
			 * Revival.rawDinoMeat) { itemstack = new ItemStack(Revival.dna, 4,
			 * this.analyzerItemStacks[this.RawIndex].getItemDamage()); }
			 */

            if (EnumPrehistoric.getDNA(this.analyzerItemStacks[this.RawIndex]
                    .getItem()) != null) {
                itemstack = new ItemStack(
                        EnumPrehistoric.getDNA(this.analyzerItemStacks[this.RawIndex]
                                .getItem()), 1);
            }

            if (this.analyzerItemStacks[this.RawIndex].getItem() == Items.porkchop) {
                itemstack = new ItemStack(EnumPrehistoric.Pig.DNAItem, 2);
            }

            if (this.analyzerItemStacks[this.RawIndex].getItem() == Items.beef) {
                itemstack = new ItemStack(EnumPrehistoric.Cow.DNAItem, 2);
            }
            if (this.analyzerItemStacks[this.RawIndex].getItem() == FAItemRegistry.INSTANCE.failuresaurusFlesh) {
                int randChoice = new Random().nextInt(3);
                if (randChoice == 0) {
                    itemstack = new ItemStack(Items.rotten_flesh, 1);
                } else {

                    itemstack = new ItemStack(EnumPrehistoric.getRandom().DNAItem, 1);
                }
            }

            if (this.analyzerItemStacks[this.RawIndex].getItem() == Items.leather) {
                if (new Random().nextInt(10) > 3) {
                    itemstack = new ItemStack(EnumPrehistoric.Cow.DNAItem, 1);
                } else {
                    itemstack = new ItemStack(EnumPrehistoric.Horse.DNAItem, 1);
                }
            }

            if (this.analyzerItemStacks[this.RawIndex].getItem() == Items.egg) {
                itemstack = new ItemStack(EnumPrehistoric.Chicken.DNAItem, 1);
            }

            if (this.analyzerItemStacks[this.RawIndex].getItem() == Items.chicken) {
                itemstack = new ItemStack(EnumPrehistoric.Chicken.DNAItem, 1);
            }


            if (this.analyzerItemStacks[this.RawIndex].getItem() == FAItemRegistry.INSTANCE.icedMeat) {

                if (rand >= 15) {
                    itemstack = new ItemStack(Items.chicken, 1);
                }

                if (rand >= 15 && rand < 30) {
                    itemstack = new ItemStack(Items.chicken, 1);
                }

                if (rand >= 30 && rand < 45) {
                    itemstack = new ItemStack(Items.porkchop, 1);
                }

                if (rand >= 45 && rand < 85) {
                    itemstack = new ItemStack(EnumPrehistoric.getRandomMezoic().DNAItem);

                }

                if (itemstack == null) {
                    itemstack = new ItemStack(Items.beef);
                }
            }

            if (this.analyzerItemStacks[this.RawIndex].getItem() == FAItemRegistry.INSTANCE.relic) {
                if (rand <= 40) {
                    itemstack = new ItemStack(Blocks.gravel, 2);
                }

                if (rand > 40 && rand <= 70) {
                    itemstack = new ItemStack(FAItemRegistry.INSTANCE.stoneboard, 1);
                }

                if (rand > 70 && rand <= 88) {
                    itemstack = new ItemStack(Items.flint, 2);
                }

                if (rand > 88 && rand <= 92) {
                    itemstack = new ItemStack(FAItemRegistry.INSTANCE.potteryShards, 1);
                }

                if (rand > 92 && rand <= 96) {
                    if (new Random().nextFloat() < 0.7) {
                        itemstack = new ItemStack(FABlockRegistry.INSTANCE.figurineBlock, 1,
                                new Random().nextInt(5) + 10);
                    } else {
                        itemstack = new ItemStack(FABlockRegistry.INSTANCE.figurineBlock, 1,
                                new Random().nextInt(5) + 5);
                    }
                }

                if (rand > 96) {
                    itemstack = new ItemStack(FAItemRegistry.INSTANCE.brokenSword, 1);
                }
            }

            if (itemstack != null) {
                if (itemstack.getItem() == Items.dye
                        || itemstack.getItem() == FAItemRegistry.INSTANCE.fernSeed
                        || itemstack.getItem() == FAItemRegistry.INSTANCE.stoneboard
                        || itemstack.getItem() == Items.flint
                        || itemstack.getItem() == Item
                        .getItemFromBlock(Blocks.gravel)
                        || itemstack.getItem() == FAItemRegistry.INSTANCE.relic
                        || itemstack.getItem() == FAItemRegistry.INSTANCE.brokenSapling
                        || itemstack.getItem() == Item
                        .getItemFromBlock(Blocks.sand)
                        || itemstack.getItem() == Items.string
                        || itemstack.getItem() == Items.beef) {

                    for (var3 = 12; var3 > 8; --var3) {
                        if (this.analyzerItemStacks[var3] != null
                                && itemstack == this.analyzerItemStacks[var3]) {
                            if (this.analyzerItemStacks[var3].stackSize
                                    + itemstack.stackSize <= this.analyzerItemStacks[var3]
                                    .getMaxStackSize()) {
                                this.analyzerItemStacks[var3].stackSize += itemstack.stackSize;
                                itemstack.stackSize = 0;
                                break;
                            }

                            itemstack.stackSize -= this.analyzerItemStacks[var3]
                                    .getMaxStackSize()
                                    - this.analyzerItemStacks[var3].stackSize;
                            this.analyzerItemStacks[var3].stackSize = this.analyzerItemStacks[var3]
                                    .getMaxStackSize();
                        }
                    }
                }

                if (itemstack.stackSize != 0
                        && this.analyzerItemStacks[this.SpaceIndex] == null) {
                    this.analyzerItemStacks[this.SpaceIndex] = itemstack.copy();
                }

                --this.analyzerItemStacks[this.RawIndex].stackSize;

                if (this.analyzerItemStacks[this.RawIndex].stackSize == 0) {
                    this.analyzerItemStacks[this.RawIndex] = null;
                }
            }
        }
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes
     * with Container
     */

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord,
                this.zCoord) == this && player.getDistanceSq(
                (double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
                (double) this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {

    }

    public void closeChest() {
    }

	/*
	 * Returns true if automation is allowed to insert the given stack (ignoring
	 * stack size) into the given slot.
	 */

    public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack) {
        return par1 <= 8 && (par1 >= 8 || isItemFuel(par2ItemStack));
    }

    /*
     * When some containers are closed they call this on each slot, then drop
     * whatever it returns as an EntityItem - like when you close a workbench
     * GUI.
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {
        return null;
    }

	/*
	 * Returns true if automation is allowed to insert the given stack (ignoring
	 * stack size) into the given slot.
	 */

    @Override
    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
        return par1 != 2 && (par1 != 1 || isItemFuel(par2ItemStack));
    }

	/*
	 * Returns an array containing the indices of the slots that can be accessed
	 * by automation on the given side of this block.
	 */

    @Override
    public int[] getAccessibleSlotsFromSide(int par1) {
        return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
    }

	/*
	 * Returns true if automation can insert the given item in the given slot
	 * from the given side. Args: Slot, item, side
	 */

    @Override
    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
        return this.isItemValidForSlot(par1, par2ItemStack);
    }

	/*
	 * Returns true if automation can extract the given item in the given slot
	 * from the given side. Args: Slot, item, side
	 */

    @Override
    public boolean canExtractItem(int par1, ItemStack itemstack, int par3) {
        return par3 != 0 || par1 != 1;
    }

    @Override
    public void openInventory() {
        int slots;
        for (int var7 = 0; var7 < worldObj.playerEntities.size(); ++var7) {
            EntityPlayer P = (EntityPlayer) worldObj.playerEntities.get(var7);

            if (Math.pow(this.xCoord - P.posX, 2D) + Math.pow(this.yCoord - P.posY, 2D) + Math.pow(this.zCoord - P.posZ, 2D) < 40) {
                for (slots = 12; slots > 8; --slots) {
                    if (this.analyzerItemStacks[slots] != null) {
                        if (this.analyzerItemStacks[slots].getItem() != null) {
                            if (this.analyzerItemStacks[slots].getItem() == FAItemRegistry.INSTANCE.stoneboard) {
                                P.addStat(FossilAchievementHandler.tablet, 1);
                            }
                            //if (Revival.isDNA(this.analyzerItemStacks[slots].getItem())) {
                            //	P.addStat(FossilAchievementHandler.dinoDna, 1);
                            //}
                        }
                    }

                }
            }
        }
    }

    @Override
    public void closeInventory() {
    }
}
