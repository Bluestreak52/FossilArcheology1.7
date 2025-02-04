package com.github.revival.server.entity.mob;

import com.github.revival.Revival;
import com.github.revival.server.config.FossilConfig;
import com.github.revival.server.enums.EnumPigmenSpeaks;
import com.github.revival.server.handler.FossilAchievementHandler;
import com.github.revival.server.item.FAItemRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class EntityPigBoss extends EntityMob implements IBossDisplayData, IRangedAttackMob {

    private final int Melee = 0;
    private final int Ranged = 1;
    private final ItemStack defaultHeldItem = null;
    public int AttackType = 1;
    public int FireballCount = 0;
    private int angerLevel = 0;
    private int randomSoundDelay = 0;
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.2D, 20, 30, 10.0F);
    private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false);

    public EntityPigBoss(World world) {
        super(world);

        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));

        if (world != null && !world.isRemote) {
            this.setCombatTask();
        }

        this.isImmuneToFire = true;
        this.experienceValue = 50;
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void entityInit() {
        super.entityInit();

        this.dataWatcher.addObject(18, "");
        this.setSkin("fossil:textures/mob/PigBoss.png");
    }

    @Override
    public void onDeath(DamageSource p_70645_1_) {

    }

    /**
     * sets this entity's combat AI.
     */
    public void setCombatTask() {
        this.tasks.removeTask(this.aiAttackOnCollide);
        this.tasks.removeTask(this.aiArrowAttack);
        ItemStack itemstack = this.getHeldItem();

        if (itemstack != null) {
            this.tasks.addTask(4, this.aiAttackOnCollide);
        } else {
            this.tasks.addTask(4, this.aiArrowAttack);
        }
    }

    /**
     * Makes entity wear random armor based on difficulty
     */
    @Override
    protected void addRandomArmor() {
        super.addRandomArmor();
        this.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
    }

    public boolean checkGround() {
        if (!this.onGround) {
            return this.worldObj.isAirBlock((int) this.posX, (int) this.posY - 1, (int) this.posZ);
        } else {
            return false;
        }
    }

    public String getSkin() {
        return this.dataWatcher.getWatchableObjectString(18);
    }

    public void setSkin(String string) {
        this.dataWatcher.updateObject(18, string);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(300.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.35D);

    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        for (int var7 = 0; var7 < worldObj.playerEntities.size(); ++var7) {
            EntityPlayer P = (EntityPlayer) worldObj.playerEntities.get(var7);

            if (Math.pow(this.posX - P.posX, 2D) + Math.pow(this.posY - P.posY, 2D) + Math.pow(this.posZ - P.posZ, 2D) < 100) {
                P.addStat(FossilAchievementHandler.anuAttack, 1);
            }

        }
        if (this.randomSoundDelay > 0 && --this.randomSoundDelay == 0) {
            this.worldObj.playSoundAtEntity(this, "mob.zombiepig.zpigangry", this.getSoundVolume() * 2.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        if (Revival.CONFIG.anuAllowedOverworld && !this.worldObj.provider.isHellWorld) {
            this.BlockTimeInteract();
        }

        super.onUpdate();
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!worldObj.isRemote) {
            if (!Revival.CONFIG.anuSpawn) {
                this.setDead();
            }
            if (!Revival.CONFIG.anuAllowedOverworld && !this.worldObj.provider.isHellWorld) {
                this.setDead();
            }
        }

        if (this.FireballCount < 50) {
            if (this.getSkin() != "fossil:textures/mob/PigBoss.png") {
                this.setSkin("fossil:textures/mob/PigBoss.png");
            }
        }

        if (this.FireballCount > 50 && this.getAttackMode() == 1 && this.getAITarget() != null) {
            if (this.getSkin() != "fossil:textures/mob/PigBoss_Charging.png") {
                this.setSkin("fossil:textures/mob/PigBoss_Charging.png");
            }

            this.setPathToEntity((PathEntity) null);
            this.faceEntity(this.getAITarget(), 30.0F, 30.0F);
            this.setCombatTask();
        }

        if (this.getAttackMode() != 1) {
            this.FireballCount = 0;

            if ((new Random()).nextInt(5000) <= 15 && this.worldObj.getClosestPlayerToEntity(this, 16.0D) != null) {
                this.SkillSwordQi();
            }
        }

        List entityList;

        //Zombiepig Searching
        if (this.getAITarget() != null && (new Random()).nextInt(100) <= 25) {
            entityList = this.worldObj.getEntitiesWithinAABB(EntityPigZombie.class, AxisAlignedBB.getBoundingBox(this.posX, this.posY, this.posZ, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D).expand(16.0D, 4.0D, 16.0D));

            if (!entityList.isEmpty() && entityList.size() >= 5) {
                this.CallSoldiers(entityList, this.getAITarget());
            }
        }

        //pig searching
        if (this.getAITarget() == null && (new Random()).nextInt(100) <= 20 && !this.worldObj.provider.isHellWorld) {
            entityList = this.worldObj.getEntitiesWithinAABB(EntityPig.class, AxisAlignedBB.getBoundingBox(this.posX, this.posY, this.posZ, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D).expand(16.0D, 4.0D, 16.0D));

            if (entityList.size() >= 3) {
                this.TranferPigs(entityList);
            }
        }

        if (this.FireballCount < 101) {
            ++this.FireballCount;
        }
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float var2) {
        Entity targetEntity = damageSource.getEntity();

        AxisAlignedBB chatDistance = this.boundingBox.expand(30.0D, 30.0D, 30.0D);
        List playerList = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, chatDistance);

        if (targetEntity instanceof EntityGhast) {
            return false;
        } else {
            if (targetEntity instanceof EntityPlayer) {
                //           this.becomeAngryAt(targetEntity);

                if (var2 != 0) {
                    ItemStack itemstack = ((EntityPlayer) targetEntity).inventory.getCurrentItem();

                    if (itemstack == null) {
                        if (this.getAttackMode() != 0) {
                            if (!this.worldObj.isRemote) {
                                Revival.showMessage(StatCollector.translateToLocal("entity.fossil.PigBoss.name") + ": "
                                        + StatCollector.translateToLocal("anuSpeaker.draw"), (EntityPlayer) targetEntity);
                            }

                            this.SetAttackMode(0);
                            this.setCombatTask();
                            return super.attackEntityFrom(damageSource, var2);
                        }
                    } else {
                        if (itemstack.getItem() == FAItemRegistry.INSTANCE.ancientSword && this.getAttackMode() != 0) {

                            if (!this.worldObj.isRemote) {
                                Revival.showMessage(StatCollector.translateToLocal("entity.fossil.PigBoss.name") + ": " +
                                        StatCollector.translateToLocal("anuSpeaker.mySword"), (EntityPlayer) targetEntity);
                            }

                            this.SetAttackMode(0);
                            this.setCombatTask();
                            return super.attackEntityFrom(damageSource, var2);
                        }


                        if (itemstack.getItem() != FAItemRegistry.INSTANCE.ancientSword && itemstack.getItem() instanceof ItemSword && this.getAttackMode() != 0) {

                            if (!this.worldObj.isRemote) {
                                Revival.showMessage(StatCollector.translateToLocal("entity.fossil.PigBoss.name") + ": " +
                                        StatCollector.translateToLocal("anuSpeaker.draw"), (EntityPlayer) targetEntity);
                            }

                            this.SetAttackMode(0);
                            this.setCombatTask();
                            return super.attackEntityFrom(damageSource, var2);
                        }

                        if (damageSource.damageType == "arrow" && this.getAttackMode() != 1) {
                            if (!this.worldObj.isRemote) {
                                Revival.showMessage(StatCollector.translateToLocal("entity.fossil.PigBoss.name") + ": " +
                                        StatCollector.translateToLocal("anuSpeaker.coward"), (EntityPlayer) targetEntity);
                            }

                            this.SetAttackMode(1);
                            this.setCombatTask();
                            return super.attackEntityFrom(damageSource, var2);
                        }

                        if (!(itemstack.getItem() instanceof ItemBow) && !(itemstack.getItem() instanceof ItemSword)) {
                            double var5 = Math.sqrt(this.getDistanceSqToEntity(this.worldObj.getClosestPlayerToEntity(this, 24.0D)));

                            if (var5 > 6.0D && this.getAttackMode() != 1) {

                                if (this.worldObj.provider.isHellWorld) {
                                    if (!this.worldObj.isRemote) {
                                        Revival.showMessage(StatCollector.translateToLocal("entity.fossil.PigBoss.name") + ": " +
                                                StatCollector.translateToLocal("anuSpeaker.leartHere"), (EntityPlayer) targetEntity);
                                    }
                                } else {
                                    if (!this.worldObj.isRemote) {
                                        Revival.showMessage(StatCollector.translateToLocal("entity.fossil.PigBoss.name") + ": " +
                                                StatCollector.translateToLocal("anuSpeaker.leartThere"), (EntityPlayer) targetEntity);
                                    }
                                }


                                this.SetAttackMode(1);
                                this.setCombatTask();
                                return super.attackEntityFrom(damageSource, var2);
                            }

                            if (var5 < 6.0D && this.getAttackMode() != 0) {
                                if (!this.worldObj.isRemote) {
                                    Revival.showMessage(StatCollector.translateToLocal("entity.fossil.PigBoss.name") + ": " +
                                            StatCollector.translateToLocal("anuSpeaker.unknownRanged"), (EntityPlayer) targetEntity);
                                }

                                this.SetAttackMode(0);
                                this.setCombatTask();
                                return super.attackEntityFrom(damageSource, var2);
                            }
                        }
                    }
                } else if (this.getAttackMode() != 1) {

                    if (!this.worldObj.isRemote) {
                        Revival.showMessage(StatCollector.translateToLocal("entity.fossil.PigBoss.name") + ": " +
                                StatCollector.translateToLocal("anuSpeaker.unknownMelee"), (EntityPlayer) targetEntity);
                    }

                    this.SetAttackMode(1);
                    this.setCombatTask();
                    return super.attackEntityFrom(damageSource, var2);
                }
            }

            return super.attackEntityFrom(damageSource, var2);
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound var1) {
        super.writeEntityToNBT(var1);
        var1.setString("AnuSkin", this.getSkin());
        var1.setShort("Anger", (short) this.angerLevel);
        var1.setInteger("AttackMode", this.getAttackMode());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound var1) {
        super.readEntityFromNBT(var1);
        this.setSkin(var1.getString("AnuSkin"));
        this.angerLevel = var1.getShort("Anger");

        if (var1.hasKey("AttackMode")) {
            this.SetAttackMode(var1.getInteger("AttackMode"));
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return "mob.zombiepig.zpig";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return "mob.zombiepig.zpighurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound() {
        return "mob.zombiepig.zpigdeath";
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    @Override
    protected Item getDropItem() {
        return Items.cooked_porkchop;
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    @Override
    protected boolean canDespawn() {

        return false;// !this.worldObj.provider.isHellWorld;
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    @Override
    protected Entity findPlayerToAttack() {
        EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);

        if (entityplayer != null && this.canEntityBeSeen(entityplayer)) {
            if (this.getRNG().nextInt(1) == 0) {
                Revival.showMessage(StatCollector.translateToLocal("entity.fossil.PigBoss.name") + ": " +
                        StatCollector.translateToLocal("anuSpeaker.hello"), (EntityPlayer) entityplayer);
            } else {
                Revival.showMessage(StatCollector.translateToLocal("entity.fossil.PigBoss.name") + ": " +
                        StatCollector.translateToLocal("anuSpeaker.fewBeaten"), (EntityPlayer) entityplayer);
            }


            if (!this.worldObj.provider.isHellWorld) {
                //((EntityPlayer)entityplayer).triggerAchievement(FossilAchievementHandler.pigBossOnEarth);
            }

            return entityplayer;
        }

        return null;
    }

    @Override
    public void updateAITasks() {

        if (this.ticksExisted % 20 == 0) {
            this.heal(5.0F);
        }
        super.updateAITasks();
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase targetEntity, float par2) {

        if (this.FireballCount > 30) {
            if ((new Random()).nextInt(1000) <= 750) {

                double var3 = targetEntity.posX - this.posX;
                double var5 = targetEntity.boundingBox.minY + (double) (targetEntity.height / 2.0F) - (this.posY + (double) (this.height / 2.0F));
                double var7 = targetEntity.posZ - this.posZ;
                this.renderYawOffset = this.rotationYaw = -((float) Math.atan2(var3, var7)) * 180.0F / (float) Math.PI;

                this.worldObj.playSoundAtEntity(this, "mob.ghast.fireball", 10.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                EntityLargeFireball largeFireball = new EntityLargeFireball(this.worldObj, this, var3, var5, var7);
                double speed = 12.0D;
                Vec3 lookVec = this.getLook(1.0F);
                largeFireball.posX = this.posX + lookVec.xCoord * speed;
                largeFireball.posY = this.posY + 0.8D;
                largeFireball.posZ = this.posZ + lookVec.zCoord * speed;
                this.worldObj.spawnEntityInWorld(largeFireball);

                this.FireballCount = 0;
            } else {
                if ((this.FireballCount >= 99)) {
                    AxisAlignedBB chatDistance = this.boundingBox.expand(30.0D, 30.0D, 30.0D);
                    List playerList = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, chatDistance);

                    if (playerList != null && !playerList.isEmpty()) {
                        Iterator iterator = playerList.iterator();

                        while (iterator.hasNext()) {
                            EntityPlayer player = (EntityPlayer) iterator.next();
                            double distance = this.getDistanceSqToEntity(player);

                            if (distance < 30.0D) {
                                Revival.showMessage(
                                        StatCollector.translateToLocal("entity.fossil.PigBoss.name") + ": " +
                                                StatCollector.translateToLocal("anuSpeaker.fireRain"), player);
                            }
                        }
                    }

                    this.SkillFireballRain(targetEntity);
                    this.FireballCount = 0;
                }
            }

        }




		/*
        EntityArrow entityarrow = new EntityArrow(this.worldObj, this, par1EntityLivingBase, 1.6F, (float)(14 - this.worldObj.difficultySetting * 4));
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
        int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
        entityarrow.setDamage((double)(par2 * 2.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.difficultySetting * 0.11F));

        if (i > 0)
        {
            entityarrow.setDamage(entityarrow.getDamage() + (double)i * 0.5D + 0.5D);
        }

        if (j > 0)
        {
            entityarrow.setKnockbackStrength(j);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0)
        {
            entityarrow.setFire(100);
        }

        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
		 */
    }

    /**
     * Sets the held item, or an armor slot. Slot 0 is held item. Slot 1-4 is armor. Params: Item, slot
     */
    @Override
    public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack) {
        super.setCurrentItemOrArmor(par1, par2ItemStack);
    }

    /**
     * Returns the item that this EntityLiving is holding, if any.
     */

    @Override
    public ItemStack getHeldItem() {
        if (this.getAttackMode() == Melee) {
            return new ItemStack(FAItemRegistry.INSTANCE.ancientSword);
        }
        return defaultHeldItem;
    }


    public int getAttackMode() {
        return this.AttackType;
    }

    public void SetAttackMode(int newmode) {
        if (newmode < 2) {
            this.AttackType = newmode;
        } else {
            this.AttackType = 0;
        }
    }

    public void SwitchAttackMode() {
        this.SetAttackMode((this.AttackType + 1) % 2);
    }

    private void BlockTimeInteract() {
        if (!this.worldObj.provider.isHellWorld) {
            for (int var1 = (int) (Math.round(this.posX) - 1L); var1 <= (int) (Math.round(this.posX) + 1L); ++var1) {
                for (int var2 = (int) (Math.round(this.posZ) - 1L); var2 <= (int) (Math.round(this.posZ) + 1L); ++var2) {
                    Material var3 = this.worldObj.getBlock(var1, (int) (Math.round(this.posY) - 1L), var2).getMaterial();

                    if (var3 == Material.rock) {
                        this.worldObj.setBlock(var1, (int) (Math.round(this.posY) - 1L), var2, Blocks.netherrack);
                    }

                    if (var3 == Material.ground) {
                        this.worldObj.setBlock(var1, (int) (Math.round(this.posY) - 1L), var2, Blocks.soul_sand);
                    }

                    if (var3 == Material.ice || var3 == Material.water) {
                        this.worldObj.setBlock(var1, (int) (Math.round(this.posY) - 1L), var2, Blocks.obsidian);
                    }

                    if (var3 == Material.clay) {
                        this.worldObj.setBlock(var1, (int) (Math.round(this.posY) - 1L), var2, Blocks.glowstone);
                    }

                    if ((long) var1 != Math.round(this.posX) && (long) var2 != Math.round(this.posZ) && (new Random()).nextInt(2000) <= 1 && this.worldObj.isAirBlock(var1, (int) Math.round(this.posY), var2)) {
                        this.worldObj.setBlock(var1, (int) Math.round(this.posY), var2, Blocks.fire);
                    }
                }
            }

            if (this.worldObj.getWorldTime() > 19000L || this.worldObj.getWorldTime() < 17000L) {
                this.worldObj.setWorldTime(17000L);
            }
        }
    }

    private void SkillSwordQi() {
        List entityList = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(this.posX, this.posY, this.posZ, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D).expand(32.0D, 4.0D, 32.0D));


        if (!entityList.isEmpty()) {
            AxisAlignedBB chatDistance = this.boundingBox.expand(30.0D, 30.0D, 30.0D);
            List playerList = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, chatDistance);

            if (playerList != null && !playerList.isEmpty()) {
                Iterator iterator = playerList.iterator();

                while (iterator.hasNext()) {
                    EntityPlayer player = (EntityPlayer) iterator.next();
                    double distance = this.getDistanceSqToEntity(player);

                    if (distance < 30.0D) {
                        Revival.showMessage(
                                StatCollector.translateToLocal("entity.fossil.PigBoss.name") + ": " +
                                        StatCollector.translateToLocal("anuSpeaker.qi"), player);
                    }
                }
            }

            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 6.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
            this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);

            for (int var2 = 0; var2 < entityList.size(); ++var2) {
                EntityLivingBase victem = (EntityLiving) entityList.get(var2);
                double var4 = this.posX - victem.posX;
                double var6;

                for (var6 = this.posZ - victem.posZ; var4 * var4 + var6 * var6 < 1.0E-4D; var6 = (Math.random() - Math.random()) * 0.01D) {
                    var4 = (Math.random() - Math.random()) * 0.01D;
                }

                if (victem != this) {
                    victem.knockBack(this, 0, var4 * 5.0D, var6 * 5.0D);
                }

                if (victem instanceof EntityPlayer && (new Random()).nextInt(1000) >= 850) {
                    ((EntityPlayer) victem).inventory.dropAllItems();
                }
            }
        }
    }

    private void CallSoldiers(List var1, Entity var2) {

        AxisAlignedBB chatDistance = this.boundingBox.expand(30.0D, 30.0D, 30.0D);
        List playerList = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, chatDistance);

        if (playerList != null && !playerList.isEmpty()) {
            Iterator iterator = playerList.iterator();

            while (iterator.hasNext()) {
                EntityPlayer player = (EntityPlayer) iterator.next();
                double distance = this.getDistanceSqToEntity(player);

                if (distance < 30.0D) {
                    Revival.showMessage(
                            StatCollector.translateToLocal("entity.fossil.PigBoss.name") + ": " +
                                    StatCollector.translateToLocal("anuSpeaker.summon"), player);
                }
            }
        }

        for (int var3 = 0; var3 < var1.size(); ++var3) {
            Entity var4 = (Entity) var1.get(var3);

            if (var4 instanceof EntityPigZombie && ((EntityPigZombie) var4).getAITarget() == null) {
                EntityPigZombie var5 = (EntityPigZombie) var4;
                var5.setAttackTarget((EntityLivingBase) var2);
                (new PigmenSpeaker((EntityFriendlyPigZombie) null)).SendSpeech(EnumPigmenSpeaks.AnuSommon);
            }
        }
    }

    private void TranferPigs(List var1) {

        AxisAlignedBB chatDistance = this.boundingBox.expand(30.0D, 30.0D, 30.0D);
        List playerList = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, chatDistance);

        if (playerList != null && !playerList.isEmpty()) {
            Iterator iterator = playerList.iterator();

            while (iterator.hasNext()) {
                EntityPlayer player = (EntityPlayer) iterator.next();
                double distance = this.getDistanceSqToEntity(player);

                if (distance < 30.0D) {
                    Revival.showMessage(
                            StatCollector.translateToLocal("entity.fossil.PigBoss.name") + ": " +
                                    StatCollector.translateToLocal("anuSpeaker.trans"), player);
                }
            }
        }

        for (int var2 = 0; var2 < var1.size(); ++var2) {
            Entity var3 = (Entity) var1.get(var2);

            if (var3 instanceof EntityPig) {
                var3.onStruckByLightning(new EntityLightningBolt(this.worldObj, var3.posX, var3.posY, var3.posZ));
            }
        }
    }

    public void SkillFireballRain(Entity targetEntity) {

        double var3 = targetEntity.posX - this.posX;
        double var5 = targetEntity.boundingBox.minY + (double) (targetEntity.height / 2.0F) - (this.posY + (double) (this.height / 2.0F));
        double var7 = targetEntity.posZ - this.posZ;
        this.renderYawOffset = this.rotationYaw = -((float) Math.atan2(var3, var7)) * 180.0F / (float) Math.PI;

        for (int var9 = 1; var9 <= 16; ++var9) {
            this.worldObj.playSoundAtEntity(this, "mob.ghast.fireball", 10.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            EntityLargeFireball var2 = new EntityLargeFireball(this.worldObj, this, var3, var5, var7);
            double speed = 4.0D;
            Vec3 lookVec = this.getLook(1.0F);
            var2.posX += (double) ((new Random()).nextInt(30) - 10);
            var2.posY = this.posY + 0.8D;
            var2.posZ += (double) ((new Random()).nextInt(30) - 10);
            this.worldObj.spawnEntityInWorld(var2);
        }
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData par1EntityLivingData) {
        par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);

        this.tasks.addTask(4, this.aiAttackOnCollide);
        this.setCurrentItemOrArmor(0, new ItemStack(FAItemRegistry.INSTANCE.ancientSword));
        this.tasks.addTask(4, this.aiArrowAttack);
        this.enchantEquipment();

        return par1EntityLivingData;
    }
}
