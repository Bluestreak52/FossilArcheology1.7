package com.github.revival.server.handler;

import com.github.revival.Revival;
import com.github.revival.server.block.FABlockRegistry;
import com.github.revival.server.enchantment.FAEnchantmentRegistry;
import com.github.revival.server.item.FAItemRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

public class FossilToolEvent {

    private int paleontologyBonus;
    private int archeologyBonus;

    private boolean hasPaleontologyBonus = false;
    private boolean hasArcheologyBonus = false;

    @SubscribeEvent
    public void onHarvestBlocks(BlockEvent.HarvestDropsEvent event) {
        Block block = event.block;

        EntityPlayer player = event.harvester;
        if (player != null) {
            paleontologyBonus = EnchantmentHelper.getEnchantmentLevel(FAEnchantmentRegistry.INSTANCE.paleontology.effectId, event.harvester.inventory.getCurrentItem());
            archeologyBonus = EnchantmentHelper.getEnchantmentLevel(FAEnchantmentRegistry.INSTANCE.archeology.effectId, event.harvester.inventory.getCurrentItem());

            if (event.harvester.inventory.getCurrentItem() == null) {
                return;
            } else {
                if (paleontologyBonus > 0) {
                    hasPaleontologyBonus = true;
                }

                if (archeologyBonus > 0) {
                    hasArcheologyBonus = true;
                }
            }

            if (hasPaleontologyBonus) {

                if (block == FABlockRegistry.INSTANCE.blockFossil) {
                    switch (paleontologyBonus) {
                        case 1:
                            if (player.worldObj.rand.nextFloat() < 0.70) {
                                event.drops.remove(0);
                                event.drops.add(new ItemStack(FAItemRegistry.INSTANCE.biofossil, 1));
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (player.worldObj.rand.nextFloat() < 0.80) {
                                event.drops.remove(0);
                                event.drops.add(new ItemStack(FAItemRegistry.INSTANCE.biofossil, 1));
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if (player.worldObj.rand.nextFloat() < 0.90) {
                                event.drops.remove(0);
                                event.drops.add(new ItemStack(FAItemRegistry.INSTANCE.biofossil, 1));
                                break;
                            } else {
                                break;
                            }
                        default:
                            break;
                    }


                }

                if (block == FABlockRegistry.INSTANCE.blockPermafrost) {
                    switch (paleontologyBonus) {
                        case 1:
                            if (player.worldObj.rand.nextFloat() < 0.50) {
                                event.drops.remove(0);
                                event.drops.add(new ItemStack(FAItemRegistry.INSTANCE.icedMeat, 1));
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (player.worldObj.rand.nextFloat() < 0.70) {
                                event.drops.remove(0);
                                event.drops.add(new ItemStack(FAItemRegistry.INSTANCE.icedMeat, 1));
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if (player.worldObj.rand.nextFloat() < 0.90) {
                                event.drops.remove(0);
                                event.drops.add(new ItemStack(FAItemRegistry.INSTANCE.icedMeat, 1));
                                break;
                            } else {
                                break;
                            }
                        default:
                            break;
                    }
                }
            }

            //Handle archeology bonus
            if (hasArcheologyBonus) {

                float rand = player.worldObj.rand.nextInt(1000);

                if (block == FABlockRegistry.INSTANCE.blockFossil) {
                    switch (archeologyBonus) {
                        case 1:
                            if (rand < 500) {
                                event.drops.remove(0);
                                event.drops.add(new ItemStack(FAItemRegistry.INSTANCE.relic, 1));
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (rand < 700) {
                                event.drops.remove(0);
                                event.drops.add(new ItemStack(FAItemRegistry.INSTANCE.relic, 1));
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if (rand < 900) {
                                event.drops.remove(0);
                                event.drops.add(new ItemStack(FAItemRegistry.INSTANCE.relic, 1));
                                break;
                            } else if (rand < 904) {
                                event.drops.remove(0);
                                event.drops.add(new ItemStack(FAItemRegistry.INSTANCE.gem, 1));
                                break;
                            } else {
                                break;
                            }
                        default:
                            break;
                    }
                }
            }
        }
    }
}
