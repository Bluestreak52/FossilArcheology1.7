package com.github.revival.server.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.util.MathHelper;

public class WaterDinoAILeapAtTarget extends EntityAILeapAtTarget {
    /**
     * The entity that is leaping.
     */
    EntityLiving leaper;

    /**
     * The entity that the leaper is leaping towards.
     */
    EntityLivingBase leapTarget;

    /**
     * The entity's motionY after leaping.
     */
    float leapMotionY;

    public WaterDinoAILeapAtTarget(EntityLiving var1, float var2) {
        super(var1, var2);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        this.leapTarget = this.leaper.getAttackTarget();

        if (this.leapTarget == null) {
            return false;
        } else {
            double d0 = this.leaper.getDistanceSqToEntity(this.leapTarget);
            return d0 >= 4.0D && d0 <= 16.0D && (!this.leaper.onGround ? false : this.leaper.getRNG().nextInt(5) == 0);
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting() {
        return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        double var1 = this.leapTarget.posX - this.leaper.posX;
        double var3 = this.leapTarget.posZ - this.leaper.posZ;
        float var5 = MathHelper.sqrt_double(var1 * var1 + var3 * var3);
        this.leaper.motionX += var1 / (double) var5 * 0.5D * 0.800000011920929D + this.leaper.motionX * 0.20000000298023224D;
        this.leaper.motionZ += var3 / (double) var5 * 0.5D * 0.800000011920929D + this.leaper.motionZ * 0.20000000298023224D;
        this.leaper.motionY = (double) this.leapMotionY;
    }
}
