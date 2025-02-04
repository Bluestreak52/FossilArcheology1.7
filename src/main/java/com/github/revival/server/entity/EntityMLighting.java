package com.github.revival.server.entity;

import com.github.revival.server.entity.mob.EntityFriendlyPigZombie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class EntityMLighting extends EntityLightningBolt {
    /**
     * A random long that is used to change the vertex of the lightning rendered in RenderLightningBolt
     */
    public long boltVertex;
    private int lightningState = 2;
    private int boltLivingTime;

    public EntityMLighting(World var1, double var2, double var4, double var6) {
        super(var1, var2, var4, var6);
        this.boltLivingTime = this.rand.nextInt(3) + 1;
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.lightningState == 2) {
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "ambient.weather.thunder", 10000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
        }

        --this.lightningState;

        if (this.lightningState < 0) {
            if (this.boltLivingTime == 0) {
                this.setDead();
            } else if (this.lightningState < -this.rand.nextInt(10)) {
                --this.boltLivingTime;
                this.lightningState = 1;
                this.boltVertex = this.rand.nextLong();

                if (this.worldObj.doChunksNearChunkExist(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 10)) {
                    int var1 = MathHelper.floor_double(this.posX);
                    int var2 = MathHelper.floor_double(this.posY);
                    int var3 = MathHelper.floor_double(this.posZ);

                    if (this.worldObj.getBlock(var1, var2, var3) == Blocks.air && Blocks.fire.canPlaceBlockAt(this.worldObj, var1, var2, var3)) {
                        this.worldObj.setBlock(var1, var2, var3, Blocks.fire);
                    }
                }
            }
        }

        if (this.lightningState >= 0) {
            double var6 = 3.0D;
            List var7 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBox(this.posX - var6, this.posY - var6, this.posZ - var6, this.posX + var6, this.posY + 6.0D + var6, this.posZ + var6));

            for (int var4 = 0; var4 < var7.size(); ++var4) {
                Entity var5 = (Entity) var7.get(var4);

                if (!(var5 instanceof EntityPlayer) && !(var5 instanceof EntityFriendlyPigZombie) && !(var5 instanceof EntityPig)) {
                    var5.onStruckByLightning(new EntityLightningBolt(this.worldObj, this.posX, this.posY, this.posZ));
                }
            }

            this.worldObj.lastLightningBolt = 2;
        }
    }
}
