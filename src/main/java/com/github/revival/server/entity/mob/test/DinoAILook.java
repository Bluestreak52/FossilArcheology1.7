package com.github.revival.server.entity.mob.test;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class DinoAILook extends EntityAIBase {
    private static final String __OBFID = "CL_00001607";
    public int necklength;
    /**
     * The entity that is looking idle.
     */
    private EntityLiving idleEntity;
    /**
     * X offset to look at
     */
    private double lookX;
    /**
     * Z offset to look at
     */
    private double lookZ;
    /**
     * A decrementing tick that stops the entity from being idle once it reaches 0.
     */
    private int idleTime;

    public DinoAILook(EntityLiving p_i1647_1_, int necklength) {
        this.idleEntity = p_i1647_1_;
        this.necklength = necklength;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        return this.idleEntity.getRNG().nextFloat() < 0.02F;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting() {
        return this.idleTime >= 0;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        double d0 = (Math.PI * 2D) * this.idleEntity.getRNG().nextDouble();
        this.lookX = Math.cos(d0 / necklength);
        this.lookZ = Math.sin(d0 / necklength);
        this.idleTime = 20 + this.idleEntity.getRNG().nextInt(20);
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        --this.idleTime;
        this.idleEntity.getLookHelper().setLookPosition(this.idleEntity.posX + this.lookX, this.idleEntity.posY + (double) this.idleEntity.getEyeHeight(), this.idleEntity.posZ + this.lookZ, 10.0F, (float) this.idleEntity.getVerticalFaceSpeed());
    }
}