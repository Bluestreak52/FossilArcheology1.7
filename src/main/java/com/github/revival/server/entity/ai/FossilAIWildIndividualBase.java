package com.github.revival.server.entity.ai;

import com.github.revival.server.block.entity.TileEntityFeeder;
import com.github.revival.server.entity.mobs.EntityPrehistoric;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public abstract class FossilAIWildIndividualBase extends EntityAIBase {

    private static final int taskFleeNear = 0;
    private static final int taskFleeFar = 1;
    private static final int taskAvoid = 2;
    private static final int taskWanderFast = 3;
    private static final int taskWanderSlow = 4;
    private static final int taskFollow = 5;
    private static final int taskIdle = 6;
    private static final int taskTrackAndAttack = 7;
    private static final int taskFeedFromFeeder = 8;
    private static final int taskPickupItem = 9;
    private static final int taskEatBlock = 10;
    private static final int taskFormHerd = 11;
    private static final int taskAskJoinHerd = 12;
    private static final int taskFindAndDrinkWater = 13;

    private static final int fleeInterruptTicks = 5;

    private EntityPrehistoric entity;
    private Object target;
    private int task;
    private int tickCounter;
    private float radiusDimension;
    private boolean taskDone;

    private ArrayList<EntityPrehistoric> nearbyPrehistoricEntities = new ArrayList<EntityPrehistoric>();
    private ArrayList<EntityPrehistoric> nearbyPrehistoricEntitiesOfSameSpecies = new ArrayList<EntityPrehistoric>();
    private ArrayList<EntityLiving> nearbyLivingEntities = new ArrayList<EntityLiving>();
    private ArrayList<EntityItem> nearbyItemEntities = new ArrayList<EntityItem>();
    private ArrayList<EntityPlayer> nearbyPlayers = new ArrayList<EntityPlayer>();
    private ArrayList<EntityPrehistoric> fleeingTargets = new ArrayList<EntityPrehistoric>();
    private ArrayList<TileEntityFeeder> feeders = new ArrayList<TileEntityFeeder>();
    private ArrayList<EntityLiving> possibleFood = new ArrayList<EntityLiving>();
    private ArrayList<EntityItem> possibleItemFood = new ArrayList<EntityItem>();
    private ArrayList<Vec3> foodBlocks = new ArrayList<Vec3>();
    private ArrayList<EntityPrehistoric> possibleFleeTargets = new ArrayList<EntityPrehistoric>();
    private ArrayList<EntityPlayer> possibleFleeTargetPlayers = new ArrayList<EntityPlayer>();

    private Chunk currentChunk;
    private Chunk tempChunk;

    public FossilAIWildIndividualBase(EntityPrehistoric entity) {
        this.entity = entity;
        this.setMutexBits(2);
    }

    @Override
    public boolean shouldExecute() {
        return taskDone && !entity.hasOwner() && !entity.isInHerd();
    }

    @Override
    public boolean continueExecuting() {
        return !taskDone && !entity.hasOwner() && !entity.isInHerd();
    }

    // TODO get entities from chunks not world
    @Override
    public void startExecuting() {
        taskDone = false;
        // ArrayLists to store found entities
        nearbyPrehistoricEntities.clear();
        nearbyPrehistoricEntitiesOfSameSpecies.clear();
        nearbyLivingEntities.clear();
        nearbyItemEntities.clear();
        nearbyPlayers.clear();
        fleeingTargets.clear();
        feeders.clear();
        possibleFood.clear();
        possibleItemFood.clear();
        foodBlocks.clear();
        Vec3 waterBlock = null;
        boolean checkedForWater = false;

        int chunkRadius = (int) Math.ceil(entity.getType().getMaxAwarenessRadius() / 16);

        currentChunk = entity.worldObj.getChunkFromChunkCoords(entity.chunkCoordX, entity.chunkCoordY);

        for (int i = -chunkRadius; i <= chunkRadius; i++) {
            for (int j = -chunkRadius; j <= chunkRadius; j++) {
                tempChunk = entity.worldObj.getChunkFromChunkCoords(currentChunk.xPosition + i, currentChunk.zPosition + j);
                for (int k = (int) ((entity.posY - 4) / 16); k < (int) ((entity.posY + 4) / 16) && k >= 0 && k < tempChunk.entityLists.length; k++) {
                    for (Object tempEntity : tempChunk.entityLists[k]) {
                        if (tempEntity.getClass().isAssignableFrom(EntityPrehistoric.class)) {
                            if (entity.canFindEntity((Entity) tempEntity)) {
                                nearbyPrehistoricEntities.add((EntityPrehistoric) tempEntity);
                                if (tempEntity.getClass().isAssignableFrom(entity.getClass())) {
                                    nearbyPrehistoricEntitiesOfSameSpecies.add((EntityPrehistoric) tempEntity);
                                }
                            }
                        } else if (tempEntity.getClass().isAssignableFrom(EntityPlayer.class)) {
                            if (entity.canFindEntity((Entity) tempEntity)) {
                                nearbyPlayers.add((EntityPlayer) tempEntity);
                            }
                        } else if (tempEntity.getClass().isAssignableFrom(EntityLiving.class)) {
                            if (entity.canFindEntity((Entity) tempEntity)) {
                                nearbyLivingEntities.add((EntityLiving) tempEntity);
                            }
                        } else if (tempEntity.getClass().isAssignableFrom(EntityItem.class)) {
                            if (entity.canFindEntity((Entity) tempEntity)) {
                                nearbyItemEntities.add((EntityItem) tempEntity);
                            }
                        }
                    }
                }
            }
        }

        if (!nearbyPrehistoricEntities.isEmpty()) {
            // Check nearby targets to see if we should flee
            for (EntityPrehistoric tempEntity : nearbyPrehistoricEntities) {
                if (entity.getType().shouldRunFromEntity(tempEntity)) {
                    fleeingTargets.add(tempEntity);
                }
            }

            if (!fleeingTargets.isEmpty()) {
                // Sort based on
                Collections.sort(fleeingTargets, new Comparator<EntityPrehistoric>() {
                    @Override
                    public int compare(EntityPrehistoric o1, EntityPrehistoric o2) {
                        return (int) (o1.getStrength() - o2.getStrength());
                    }
                });
                switch (entity.distanceStatus(fleeingTargets.get(0))) {
                    case 1:
                        task = taskFleeNear;
                        break;
                    case 2:
                        task = taskFleeFar;
                        break;
                }
                target = fleeingTargets.get(0);
                return;
            }
        } else if (nearbyPlayers.isEmpty()) {
            Collections.sort(nearbyPlayers, new Comparator<EntityPlayer>() {
                @Override
                public int compare(EntityPlayer arg0, EntityPlayer arg1) {
                    return (int) (entity.distanceToEntity(arg0) - entity.distanceToEntity(arg1));
                }
            });
            switch (entity.distanceStatus(nearbyPlayers.get(0))) {
                case 1:
                    task = taskFleeFar;
                    break;
                case 2:
                    task = taskFleeNear;
                    break;
            }
            target = nearbyPlayers.get(0);
        }

        if (entity.hungerLevel() > 0) {
            for (Object tempTileEntity : entity.worldObj.loadedTileEntityList) {
                if (tempTileEntity instanceof TileEntityFeeder) {
                    if (entity.canFindFeeder((TileEntityFeeder) tempTileEntity)) {
                        feeders.add((TileEntityFeeder) tempTileEntity);
                    }
                }
            }

            if (!feeders.isEmpty()) {
                Collections.sort(feeders, new Comparator<TileEntityFeeder>() {
                    @Override
                    public int compare(TileEntityFeeder o1, TileEntityFeeder o2) {
                        int totalFood1 = 0;
                        int totalFood2 = 0;
                        if (entity.getType().eatsMeat()) {
                            totalFood1 += o1.MeatCurrent;
                            totalFood2 += o2.MeatCurrent;
                        }
                        if (entity.getType().eatsVegetables()) {
                            totalFood1 += o1.VegCurrent;
                            totalFood2 += o2.VegCurrent;
                        }
                        return totalFood1 - totalFood2;
                    }
                });
                task = taskFeedFromFeeder;
                target = feeders.get(0);
                return;
            }

            for (EntityPrehistoric food : nearbyPrehistoricEntities) {
                if (entity.getType().willAttack(food)) {
                    possibleFood.add(food);
                }
            }
            for (EntityLiving food : nearbyLivingEntities) {
                if (entity.getType().willAttack(food)) {
                    possibleFood.add(food);
                }
            }

            if (!possibleFood.isEmpty()) {
                Collections.sort(possibleFood, new Comparator<EntityLiving>() {
                    @Override
                    public int compare(EntityLiving o1, EntityLiving o2) {
                        return (int) (entity.distanceToEntity(o1) - entity.distanceToEntity(o2));
                    }
                });

                task = taskTrackAndAttack;
                target = possibleFood.get(0);
                return;
            }

            for (EntityItem item : nearbyItemEntities) {
                if (entity.getType().willEat(item.getEntityItem().getItem())) {
                    possibleItemFood.add(item);
                }
            }

            if (!possibleItemFood.isEmpty()) {
                Collections.sort(possibleItemFood, new Comparator<EntityItem>() {
                    @Override
                    public int compare(EntityItem o1, EntityItem o2) {
                        return (int) (entity.distanceToEntity(o1) - entity.distanceToEntity(o2));
                    }
                });

                task = taskPickupItem;
                target = possibleItemFood.get(0);
                return;
            }

            if (entity.getType().eatsBlocks()) {
                for (int i = (int) -entity.getType().getMaxAwarenessRadius(); i <= (int) entity.getType().getMaxAwarenessRadius(); i++) {
                    int bound = (int) Math.sqrt(Math.pow(entity.getType().getMaxAwarenessRadius(), 2) - Math.pow(i, 2));
                    for (int k = -bound; k <= bound; k++) {
                        for (int j = -4; j <= 4; j++) {
                            if (entity.getType().willEat(entity.worldObj.getBlock(i, j, k))) {
                                foodBlocks.add(Vec3.createVectorHelper(i, j, k));
                            } else if (waterBlock == null && entity.worldObj.getBlock(i, j, k).getMaterial().equals(Material.water)) {
                                waterBlock = Vec3.createVectorHelper(i, j, k);
                                checkedForWater = true;
                            }
                        }
                    }
                }

                if (!foodBlocks.isEmpty()) {
                    Collections.sort(foodBlocks, new Comparator<Vec3>() {
                        @Override
                        public int compare(Vec3 o1, Vec3 o2) {
                            return (int) (entity.distanceToLocation(o1) - entity.distanceToLocation(o2));
                        }
                    });

                    task = taskEatBlock;
                    target = foodBlocks.get(0);
                    return;
                }
            }

            if (entity.hungerLevel() > 1) {
                task = taskWanderFast;
                setWanderTarget(true);
                return;
            }
        }

        if (!nearbyPrehistoricEntitiesOfSameSpecies.isEmpty() && entity.getType().isCanFormHerds()) {
            Collections.sort(nearbyPrehistoricEntitiesOfSameSpecies, new Comparator<EntityPrehistoric>() {
                @Override
                public int compare(EntityPrehistoric o1, EntityPrehistoric o2) {
                    return (int) (entity.distanceToEntity(o1) - entity.distanceToEntity(o2));
                }
            });
            if (nearbyPrehistoricEntitiesOfSameSpecies.get(0).isInHerd()) {
                task = taskAskJoinHerd;
            } else {
                task = taskFormHerd;
            }
            target = nearbyPrehistoricEntitiesOfSameSpecies.get(0);
            return;
        }

        if (!nearbyPlayers.isEmpty()) {
            Collections.sort(nearbyPlayers, new Comparator<EntityPlayer>() {
                @Override
                public int compare(EntityPlayer o1, EntityPlayer o2) {
                    return (int) (entity.distanceToEntity(o1) - entity.distanceToEntity(o2));
                }
            });
            if (entity.isAdult()) {
                if (entity.getType().attacksPlayersAsAdult()) {
                    task = taskTrackAndAttack;
                } else {
                    task = taskFollow;
                }
                target = nearbyPlayers.get(0);
                return;
            } else if (entity.isChild()) {
                task = taskAvoid;
                target = nearbyPlayers.get(0);
                return;
            }
        }

        if (!checkedForWater) {
            for (int i = (int) -entity.getType().getMaxAwarenessRadius(); i <= (int) entity.getType().getMaxAwarenessRadius(); i++) {
                int bound = (int) Math.sqrt(Math.pow(entity.getType().getMaxAwarenessRadius(), 2) - Math.pow(i, 2));
                for (int k = -bound; k <= bound; k++) {
                    for (int j = -4; j <= 4; j++) {
                        if (waterBlock == null && entity.worldObj.getBlock(i, j, k).getMaterial().equals(Material.water)) {
                            waterBlock = Vec3.createVectorHelper(i, j, k);
                            checkedForWater = true;
                            break;
                        }
                    }
                    if (checkedForWater) {
                        break;
                    }
                }
                if (checkedForWater) {
                    break;
                }
            }
        }

        if (waterBlock == null) {
            if ((new Random()).nextDouble() >= 0.5) {
                task = taskWanderSlow;
                setWanderTarget(false);
            } else {
                task = taskIdle;
            }
        } else {
            int i = 1;
            while (!entity.worldObj.getBlock((int) waterBlock.xCoord, (int) waterBlock.yCoord + i, (int) waterBlock.zCoord).getMaterial().equals(Material.air)) {
                i++;
            }
            i--;
            waterBlock = waterBlock.addVector(0, i, 0);
            Vec3 targetBlock = Vec3.createVectorHelper(entity.posX, waterBlock.yCoord, entity.posZ);
            MovingObjectPosition hit = entity.worldObj.rayTraceBlocks(waterBlock, targetBlock);
            task = taskFindAndDrinkWater;
            target = Vec3.createVectorHelper(hit.blockX, hit.blockY, hit.blockZ);
        }
    }

    @Override
    public void updateTask() {

        if (tickCounter % fleeInterruptTicks == 0 && task != taskFleeNear && task != taskFleeFar) {
            int chunkRadius = (int) Math.ceil(entity.getType().getMaxAwarenessRadius() / 16);

            possibleFleeTargets.clear();

            currentChunk = entity.worldObj.getChunkFromChunkCoords(entity.chunkCoordX, entity.chunkCoordY);

            for (int i = -chunkRadius; i <= chunkRadius; i++) {
                for (int j = -chunkRadius; j <= chunkRadius; j++) {
                    tempChunk = entity.worldObj.getChunkFromChunkCoords(currentChunk.xPosition + i, currentChunk.zPosition + j);
                    for (int k = (int) ((entity.posY - 4) / 16); k < (int) ((entity.posY + 4) / 16) && k >= 0 && k < tempChunk.entityLists.length; k++) {
                        for (Object tempEntity : tempChunk.entityLists[k]) {
                            if (tempEntity.getClass().isAssignableFrom(EntityPrehistoric.class) && entity.getType().shouldRunFromEntity((EntityPrehistoric) tempEntity)) {
                                if (entity.canFindEntity((Entity) tempEntity)) {
                                    possibleFleeTargets.add((EntityPrehistoric) tempEntity);
                                }
                            } else if (entity.isChild() && tempEntity.getClass().isAssignableFrom(EntityPlayer.class)) {
                                if (entity.canFindEntity((Entity) tempEntity)) {
                                    possibleFleeTargetPlayers.add((EntityPlayer) tempEntity);
                                }
                            }
                        }
                    }
                }
            }
            if (!possibleFleeTargets.isEmpty()) {
                Collections.sort(possibleFleeTargets, new Comparator<EntityPrehistoric>() {
                    @Override
                    public int compare(EntityPrehistoric o1, EntityPrehistoric o2) {
                        return (int) (o1.getStrength() - o2.getStrength());
                    }
                });
                switch (entity.distanceStatus(possibleFleeTargets.get(0))) {
                    case 1:
                        task = taskFleeFar;
                        break;
                    case 2:
                        task = taskFleeNear;
                        break;
                }
                target = possibleFleeTargets.get(0);
            } else if (!possibleFleeTargetPlayers.isEmpty()) {
                Collections.sort(possibleFleeTargetPlayers, new Comparator<EntityPlayer>() {
                    @Override
                    public int compare(EntityPlayer arg0, EntityPlayer arg1) {
                        return (int) (entity.distanceToEntity(arg0) - entity.distanceToEntity(arg1));
                    }
                });
                switch (entity.distanceStatus(possibleFleeTargetPlayers.get(0))) {
                    case 1:
                        task = taskFleeFar;
                        break;
                    case 2:
                        task = taskFleeNear;
                        break;
                }
                target = possibleFleeTargetPlayers.get(0);
            }
        }
        tickCounter++;
        switch (task) {
            case taskFleeNear:
                fleeFromEntity((Entity) target, true);
                break;
            case taskFleeFar:
                fleeFromEntity((Entity) target, false);
                break;
            case taskAvoid:
                avoidEntity((Entity) target, radiusDimension);
                break;
            case taskWanderFast:
                wander(true);
                break;
            case taskWanderSlow:
                wander(false);
                break;
            case taskFollow:
                followEntity((Entity) target, radiusDimension);
                break;
            case taskIdle:
                idle();
                break;
            case taskTrackAndAttack:
                trackAndAttackEntity((EntityLiving) target);
                break;
            case taskFeedFromFeeder:
                feedFromFeeder((TileEntityFeeder) target);
                break;
            case taskPickupItem:
                pickupItem((EntityItem) target);
                break;
            case taskEatBlock:
                eatBlock((Vec3) target);
                break;
            case taskFormHerd:
                formHerd((EntityPrehistoric) target);
                break;
            case taskAskJoinHerd:
                askToJoinHerd((EntityPrehistoric) target);
                break;
            case taskFindAndDrinkWater:
                findAndDrinkWater((Vec3) target);
                break;
        }
    }

    private void taskDone() {
        taskDone = true;
    }

    abstract void setWanderTarget(boolean fast);

    abstract void fleeFromEntity(Entity fleeFrom, boolean isClose);

    abstract void avoidEntity(Entity avoid, float avoidanceRadius);

    abstract void wander(boolean toRunning);

    abstract void followEntity(Entity follow, float followDistance);

    abstract void idle();

    abstract void trackAndAttackEntity(EntityLiving target);

    abstract void feedFromFeeder(TileEntityFeeder feeder);

    abstract void eatBlock(Vec3 target);

    abstract void pickupItem(EntityItem item);

    abstract void formHerd(EntityPrehistoric entity);

    abstract void askToJoinHerd(EntityPrehistoric entity);

    abstract void findAndDrinkWater(Vec3 water);
}
