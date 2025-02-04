package com.github.revival.server.entity.ai;

import com.github.revival.server.entity.mob.EntityQuagga;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

public class QuaggaAITaming extends EntityAIBase {
    private EntityQuagga horseHost;
    private double field_111178_b;
    private double field_111179_c;
    private double field_111176_d;
    private double field_111177_e;

    public QuaggaAITaming(EntityQuagga par1EntityQuagga, double par2) {
        this.horseHost = par1EntityQuagga;
        this.field_111178_b = par2;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        if (!this.horseHost.isTame() && this.horseHost.riddenByEntity != null) {
            Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.horseHost, 5, 4);

            if (vec3 == null) {
                return false;
            } else {
                this.field_111179_c = vec3.xCoord;
                this.field_111176_d = vec3.yCoord;
                this.field_111177_e = vec3.zCoord;
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        this.horseHost.getNavigator().tryMoveToXYZ(this.field_111179_c, this.field_111176_d, this.field_111177_e, this.field_111178_b);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting() {
        return !this.horseHost.getNavigator().noPath() && this.horseHost.riddenByEntity != null;
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        if (this.horseHost.getRNG().nextInt(50) == 0) {
            if (this.horseHost.riddenByEntity instanceof EntityPlayer) {
                int i = this.horseHost.getTemper();
                int j = this.horseHost.getMaxTemper();

                if (j > 0 && this.horseHost.getRNG().nextInt(j) < i) {
                    this.horseHost.setTamedBy((EntityPlayer) this.horseHost.riddenByEntity);
                    this.horseHost.worldObj.setEntityState(this.horseHost, (byte) 7);
                    return;
                }

                this.horseHost.increaseTemper(5);
            }

            this.horseHost.riddenByEntity.mountEntity((Entity) null);
            this.horseHost.riddenByEntity = null;
            this.horseHost.makeHorseRearWithSound();
            this.horseHost.worldObj.setEntityState(this.horseHost, (byte) 6);
        }
    }
}
