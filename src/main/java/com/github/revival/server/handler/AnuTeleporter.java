package com.github.revival.server.handler;


import com.github.revival.Revival;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class AnuTeleporter extends Teleporter {
    private final WorldServer worldServerInstance;
    private final Random random;
    private final LongHashMap destinationCoordinateCache = new LongHashMap();
    /**
     * A list of valid keys for the destinationCoordainteCache. These are based on the X & Z of the players initial
     * location.
     */
    private final List destinationCoordinateKeys = new ArrayList();

    public AnuTeleporter(WorldServer worldserver) {
        super(worldserver);
        this.worldServerInstance = worldserver;
        this.random = new Random(worldserver.getSeed());
    }

    /**
     * Place an entity in a nearby portal, creating one if necessary.
     */
    @Override
    public void placeInPortal(Entity p_77185_1_, double p_77185_2_, double p_77185_4_, double p_77185_6_, float p_77185_8_) {

        p_77185_1_.setLocationAndAngles((double) -74, (double) 63, (double) -115, p_77185_1_.rotationYaw, 0.0F);

        int i = MathHelper.floor_double(p_77185_1_.posX);
        int j = MathHelper.floor_double(p_77185_1_.posY) - 1;
        int k = MathHelper.floor_double(p_77185_1_.posZ);
        byte b0 = 1;
        byte b1 = 0;
        if (worldServerInstance.provider.dimensionId == Revival.CONFIG.dimensionIDTreasure) {
            if (p_77185_1_ instanceof EntityPlayer) {
                ((EntityPlayer) p_77185_1_).triggerAchievement(FossilAchievementHandler.wtf);
            }
        }
        if (worldServerInstance.provider.dimensionId == Revival.CONFIG.dimensionIDDarknessLair) {

            for (int l = -2; l <= 2; ++l) {
                for (int i1 = -2; i1 <= 2; ++i1) {
                    for (int j1 = -1; j1 < 3; ++j1) {
                        int k1 = i + i1 * b0 + l * b1;
                        int l1 = j + j1;
                        int i2 = k + i1 * b1 - l * b0;
                        boolean flag = j1 < 0;
                        this.worldServerInstance.setBlock(k1, 62, i2, Blocks.obsidian);
                    }
                }
            }
        }
        p_77185_1_.motionX = p_77185_1_.motionY = p_77185_1_.motionZ = 0.0D;
    }


    @Override
    public boolean makePortal(Entity p_85188_1_) {
        byte b0 = 16;
        double d0 = -1.0D;
        int i = MathHelper.floor_double(p_85188_1_.posX);
        int j = MathHelper.floor_double(p_85188_1_.posY);
        int k = MathHelper.floor_double(p_85188_1_.posZ);
        int l = i;
        int i1 = j;
        int j1 = k;
        int k1 = 0;
        int l1 = this.random.nextInt(4);
        int i2;
        double d1;
        int k2;
        double d2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        int l4;
        int i5;
        double d3;
        double d4;

        for (i2 = i - b0; i2 <= i + b0; ++i2) {
            d1 = (double) i2 + 0.5D - p_85188_1_.posX;

            for (k2 = k - b0; k2 <= k + b0; ++k2) {
                d2 = (double) k2 + 0.5D - p_85188_1_.posZ;
                label274:

                for (i3 = this.worldServerInstance.getActualHeight() - 1; i3 >= 0; --i3) {
                    if (this.worldServerInstance.isAirBlock(i2, i3, k2)) {
                        while (i3 > 0 && this.worldServerInstance.isAirBlock(i2, i3 - 1, k2)) {
                            --i3;
                        }

                        for (j3 = l1; j3 < l1 + 4; ++j3) {
                            k3 = j3 % 2;
                            l3 = 1 - k3;

                            if (j3 % 4 >= 2) {
                                k3 = -k3;
                                l3 = -l3;
                            }

                            for (i4 = 0; i4 < 3; ++i4) {
                                for (j4 = 0; j4 < 4; ++j4) {
                                    for (k4 = -1; k4 < 4; ++k4) {
                                        l4 = i2 + (j4 - 1) * k3 + i4 * l3;
                                        i5 = i3 + k4;
                                        int j5 = k2 + (j4 - 1) * l3 - i4 * k3;

                                        if (k4 < 0 && !this.worldServerInstance.getBlock(l4, i5, j5).getMaterial().isSolid() || k4 >= 0 && !this.worldServerInstance.isAirBlock(l4, i5, j5)) {
                                            continue label274;
                                        }
                                    }
                                }
                            }

                            d3 = (double) i3 + 0.5D - p_85188_1_.posY;
                            d4 = d1 * d1 + d3 * d3 + d2 * d2;

                            if (d0 < 0.0D || d4 < d0) {
                                d0 = d4;
                                l = i2;
                                i1 = i3;
                                j1 = k2;
                                k1 = j3 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D) {
            for (i2 = i - b0; i2 <= i + b0; ++i2) {
                d1 = (double) i2 + 0.5D - p_85188_1_.posX;

                for (k2 = k - b0; k2 <= k + b0; ++k2) {
                    d2 = (double) k2 + 0.5D - p_85188_1_.posZ;
                    label222:

                    for (i3 = this.worldServerInstance.getActualHeight() - 1; i3 >= 0; --i3) {
                        if (this.worldServerInstance.isAirBlock(i2, i3, k2)) {
                            while (i3 > 0 && this.worldServerInstance.isAirBlock(i2, i3 - 1, k2)) {
                                --i3;
                            }

                            for (j3 = l1; j3 < l1 + 2; ++j3) {
                                k3 = j3 % 2;
                                l3 = 1 - k3;

                                for (i4 = 0; i4 < 4; ++i4) {
                                    for (j4 = -1; j4 < 4; ++j4) {
                                        k4 = i2 + (i4 - 1) * k3;
                                        l4 = i3 + j4;
                                        i5 = k2 + (i4 - 1) * l3;

                                        if (j4 < 0 && !this.worldServerInstance.getBlock(k4, l4, i5).getMaterial().isSolid() || j4 >= 0 && !this.worldServerInstance.isAirBlock(k4, l4, i5)) {
                                            continue label222;
                                        }
                                    }
                                }

                                d3 = (double) i3 + 0.5D - p_85188_1_.posY;
                                d4 = d1 * d1 + d3 * d3 + d2 * d2;

                                if (d0 < 0.0D || d4 < d0) {
                                    d0 = d4;
                                    l = i2;
                                    i1 = i3;
                                    j1 = k2;
                                    k1 = j3 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int k5 = l;
        int j2 = i1;
        k2 = j1;
        int l5 = k1 % 2;
        int l2 = 1 - l5;

        if (k1 % 4 >= 2) {
            l5 = -l5;
            l2 = -l2;
        }

        boolean flag;

        if (d0 < 0.0D) {
            if (i1 < 70) {
                i1 = 70;
            }

            if (i1 > this.worldServerInstance.getActualHeight() - 10) {
                i1 = this.worldServerInstance.getActualHeight() - 10;
            }

            j2 = i1;

            for (i3 = -1; i3 <= 1; ++i3) {
                for (j3 = 1; j3 < 3; ++j3) {
                    for (k3 = -1; k3 < 3; ++k3) {
                        l3 = k5 + (j3 - 1) * l5 + i3 * l2;
                        i4 = j2 + k3;
                        j4 = k2 + (j3 - 1) * l2 - i3 * l5;
                        flag = k3 < 0;
                        this.worldServerInstance.setBlock(l3, i4, j4, flag ? Blocks.obsidian : Blocks.air);
                    }
                }
            }
        }

        for (i3 = 0; i3 < 4; ++i3) {
            for (j3 = 0; j3 < 4; ++j3) {
                for (k3 = -1; k3 < 4; ++k3) {
                    l3 = k5 + (j3 - 1) * l5;
                    i4 = j2 + k3;
                    j4 = k2 + (j3 - 1) * l2;
                    flag = j3 == 0 || j3 == 3 || k3 == -1 || k3 == 3;
                    this.worldServerInstance.setBlock(l3, i4, j4, (Block) (flag ? Blocks.obsidian : Blocks.portal), 0, 2);
                }
            }

            for (j3 = 0; j3 < 4; ++j3) {
                for (k3 = -1; k3 < 4; ++k3) {
                    l3 = k5 + (j3 - 1) * l5;
                    i4 = j2 + k3;
                    j4 = k2 + (j3 - 1) * l2;
                    this.worldServerInstance.notifyBlocksOfNeighborChange(l3, i4, j4, this.worldServerInstance.getBlock(l3, i4, j4));
                }
            }
        }

        return true;
    }

    /**
     * called periodically to remove out-of-date portal locations from the cache list. Argument par1 is a
     * WorldServer.getTotalWorldTime() value.
     */
    @Override
    public void removeStalePortalLocations(long p_85189_1_) {
        if (p_85189_1_ % 100L == 0L) {
            Iterator iterator = this.destinationCoordinateKeys.iterator();
            long j = p_85189_1_ - 600L;

            while (iterator.hasNext()) {
                Long olong = (Long) iterator.next();
                AnuTeleporter.PortalPosition portalposition = (AnuTeleporter.PortalPosition) this.destinationCoordinateCache.getValueByKey(olong.longValue());

                if (portalposition == null || portalposition.lastUpdateTime < j) {
                    iterator.remove();
                    this.destinationCoordinateCache.remove(olong.longValue());
                }
            }
        }
    }

    public class PortalPosition extends ChunkCoordinates {
        private static final String __OBFID = "CL_00000154";
        /**
         * The worldtime at which this PortalPosition was last verified
         */
        public long lastUpdateTime;

        public PortalPosition(int p_i1962_2_, int p_i1962_3_, int p_i1962_4_, long p_i1962_5_) {
            super(p_i1962_2_, p_i1962_3_, p_i1962_4_);
            this.lastUpdateTime = p_i1962_5_;
        }
    }
}