package com.github.revival.server.entity.ai;

import com.github.revival.server.entity.mob.EntityDinosaur;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.Vec3;

import java.util.List;

public class DinoAIAvoidEntityWhenYoung extends EntityAIAvoidEntity {
    EntityDinosaur dinoEntity;

    /**
     * The entity we are attached to
     */
    private EntityCreature theEntity;
    private double farSpeed;
    private double nearSpeed;
    private Entity closestLivingEntity;
    private float distanceFromEntity;

    /**
     * The PathEntity of our entity
     */
    private PathEntity entityPathEntity;

    /**
     * The PathNavigate of our entity
     */
    private PathNavigate entityPathNavigate;

    /**
     * The class of the entity we should avoid
     */
    private Class targetEntityClass;

    public DinoAIAvoidEntityWhenYoung(EntityCreature par1EntityCreature, Class par2Class, float par3, double par4, double par6) {
        super(par1EntityCreature, par2Class, par3, par4, par6);
        this.theEntity = (EntityDinosaur) par1EntityCreature;
        this.targetEntityClass = par2Class;
        this.distanceFromEntity = par3;
        this.farSpeed = par4;
        this.nearSpeed = par6;
        this.entityPathNavigate = par1EntityCreature.getNavigator();
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        if (this.targetEntityClass == EntityPlayer.class) {
            this.closestLivingEntity = this.theEntity.worldObj.getClosestPlayerToEntity(this.theEntity, (double) this.distanceFromEntity);

            if (this.closestLivingEntity == null) {
                return false;
            }
        } else {
            List list = this.theEntity.worldObj.selectEntitiesWithinAABB(this.targetEntityClass, this.theEntity.boundingBox.expand((double) this.distanceFromEntity, 3.0D, (double) this.distanceFromEntity), this.field_98218_a);

            if (list.isEmpty()) {
                return false;
            }

            this.closestLivingEntity = (Entity) list.get(0);
        }

        Vec3 vec3 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.theEntity, 16, 7, Vec3.createVectorHelper(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));

        if (vec3 == null) {
            return false;
        } else if (this.closestLivingEntity.getDistanceSq(vec3.xCoord, vec3.yCoord, vec3.zCoord) < this.closestLivingEntity.getDistanceSqToEntity(this.theEntity)) {
            return false;
        } else {
            this.entityPathEntity = this.entityPathNavigate.getPathToXYZ(vec3.xCoord, vec3.yCoord, vec3.zCoord);
            return this.entityPathEntity != null && this.entityPathEntity.isDestinationSame(vec3);
        }
    }


    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting() {
        return !this.entityPathNavigate.noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        this.entityPathNavigate.setPath(this.entityPathEntity, this.farSpeed);
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {
        this.closestLivingEntity = null;
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        if (this.theEntity.getDistanceSqToEntity(this.closestLivingEntity) < 49.0D) {
            this.theEntity.getNavigator().setSpeed(this.nearSpeed);
        } else {
            this.theEntity.getNavigator().setSpeed(this.farSpeed);
        }
    }
}
