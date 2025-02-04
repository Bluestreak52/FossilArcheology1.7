package com.github.revival.server.entity.mob.test;

import com.github.revival.server.enums.EnumPrehistoricAI.Following;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DinoAIFollowWild extends EntityAIBase {
    private static final String __OBFID = "CL_00001616";
    /**
     * The entity using this AI that is tempted by the player.
     */
    private EntityNewPrehistoric temptedEntity;
    private double field_75282_b;
    /**
     * X position of player tempting this mob
     */
    private double targetX;
    /**
     * Y position of player tempting this mob
     */
    private double targetY;
    /**
     * Z position of player tempting this mob
     */
    private double targetZ;
    private double field_75278_f;
    private double field_75279_g;
    /**
     * The player that is tempting the entity that is using this AI.
     */
    private EntityPlayer temptingPlayer;
    /**
     * A counter that is decremented each time the shouldExecute method is called. The shouldExecute method will always
     * return false if delayTemptCounter is greater than 0.
     */
    private int delayTemptCounter;
    /**
     * True if this EntityAITempt task is running
     */
    private boolean isRunning;
    private Item field_151484_k;
    /**
     * Whether the entity using this AI will be scared by the tempter's sudden movement.
     */
    private boolean field_75286_m;

    public DinoAIFollowWild(EntityNewPrehistoric mob, double i, Item food) {
        this.temptedEntity = mob;
        this.field_75282_b = i;
        this.field_151484_k = food;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        if (temptedEntity.aiFollowType() == Following.NONE) {
            return false;
        }

        if (temptedEntity.isSitting()) {
            return false;
        }

        if (this.delayTemptCounter > 0) {
            --this.delayTemptCounter;
            return false;
        } else {
            this.temptingPlayer = this.temptedEntity.worldObj.getClosestPlayerToEntity(this.temptedEntity, 10.0D);

            if (this.temptingPlayer == null) {
                return false;
            } else {
                ItemStack itemstack = this.temptingPlayer.getCurrentEquippedItem();
                return itemstack != null && itemstack.getItem() == this.field_151484_k;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting() {
        if (this.temptedEntity.aiFollowType() == Following.SKITTISH) {
            if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 36.0D) {
                if (this.temptingPlayer.getDistanceSq(this.targetX, this.targetY, this.targetZ) > 0.010000000000000002D) {
                    return false;
                }

                if (Math.abs((double) this.temptingPlayer.rotationPitch - this.field_75278_f) > 5.0D || Math.abs((double) this.temptingPlayer.rotationYaw - this.field_75279_g) > 5.0D) {
                    return false;
                }
            } else {
                this.targetX = this.temptingPlayer.posX;
                this.targetY = this.temptingPlayer.posY;
                this.targetZ = this.temptingPlayer.posZ;
            }

            this.field_75278_f = (double) this.temptingPlayer.rotationPitch;
            this.field_75279_g = (double) this.temptingPlayer.rotationYaw;
        }

        return this.shouldExecute();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        this.targetX = this.temptingPlayer.posX;
        this.targetY = this.temptingPlayer.posY;
        this.targetZ = this.temptingPlayer.posZ;
        this.isRunning = true;
        this.field_75286_m = this.temptedEntity.getNavigator().getAvoidsWater();
        this.temptedEntity.getNavigator().setAvoidsWater(false);
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {
        this.temptingPlayer = null;
        this.temptedEntity.getNavigator().clearPathEntity();
        this.delayTemptCounter = 100;
        this.isRunning = false;
        this.temptedEntity.getNavigator().setAvoidsWater(this.field_75286_m);
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        if (temptedEntity.aiFollowType() == Following.AGRESSIVE && temptedEntity.isTamed()) {
            if (temptedEntity.getOwner() != temptingPlayer) {
                temptedEntity.attackEntityAsMob(temptingPlayer);
            }
        }

        this.temptedEntity.getLookHelper().setLookPositionWithEntity(this.temptingPlayer, 30.0F, (float) this.temptedEntity.getVerticalFaceSpeed());

        if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 6.25D) {
            this.temptedEntity.getNavigator().clearPathEntity();
        } else {
            this.temptedEntity.getNavigator().tryMoveToEntityLiving(this.temptingPlayer, this.field_75282_b);
        }
    }

    /**
     * @see #isRunning
     */
    public boolean isRunning() {
        return this.isRunning;
    }
}