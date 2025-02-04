package com.github.revival.server.entity.ai;

import com.github.revival.Revival;
import com.github.revival.server.entity.mob.test.EntityNewPrehistoric;
import com.github.revival.server.enums.EnumPrehistoric;
import com.github.revival.server.enums.EnumSituation;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;

public class DinoAIGrowup extends EntityAIBase {
    protected EntityNewPrehistoric AITarget;

    public DinoAIGrowup(EntityNewPrehistoric var1) {
        this.AITarget = var1;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {

        if (/*fossilOptions.DinoGrows && */this.AITarget.getDinoAge() < this.AITarget.selfType.MaxAge) {
            this.AITarget.increaseDinoAgeTick();
            return this.AITarget.getDinoAgeTick() >= this.AITarget.selfType.AgingTicks;
        }

        return false;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting() {
        return this.shouldExecute();
    }


    /**
     * Updates the task
     */
    @Override
    public void startExecuting() {
        if (!this.AITarget.worldObj.isRemote) {
            this.AITarget.setPosition(this.AITarget.posX, this.AITarget.posY + 1, this.AITarget.posZ);
            if ((!this.AITarget.isEntityInsideOpaqueBlock()
                    && this.AITarget.selfType != EnumPrehistoric.Mosasaurus
                    && this.AITarget.selfType != EnumPrehistoric.Liopleurodon)
                    || (this.AITarget.isInWater() && this.AITarget.selfType == EnumPrehistoric.Mosasaurus)
                    || (this.AITarget.isInWater() && this.AITarget.selfType == EnumPrehistoric.Liopleurodon)
                    || (this.AITarget.isInWater() && this.AITarget.selfType == EnumPrehistoric.Plesiosaur)) {
                this.AITarget.setDinoAgeTick(0);
                this.AITarget.increaseDinoAge();
                this.AITarget.worldObj.setEntityState(this.AITarget, EntityNewPrehistoric.AGING_MESSAGE);
                //this.AITarget.CheckSkin();
                this.AITarget.updateSize();

                if (this.AITarget.getMaxHealth() < this.AITarget.getHealth()) {
                    //the dino heals itself 15% when growing up
                    if (Revival.CONFIG.healingDinos) {
                        this.AITarget.heal(MathHelper.ceiling_double_int(this.AITarget.getHealth() * 0.15f));
                    }
                }

                /*this.AITarget.setDinoAge(this.AITarget.getDinoAge()-1);
                this.AITarget.worldObj.setEntityState(this.AITarget, this.AITarget.AGING_MESSAGE);
                //this.AITarget.CheckSkin();

                if (this.AITarget.getHealth() > MathHelper.ceiling_double_int(this.AITarget.getMaxHealth()*0.05f))
                {
                	if(Revival.fossilOptions.healingDinos)
                		this.AITarget.attackEntityFrom(DamageSource.generic, MathHelper.ceiling_double_int(this.AITarget.getMaxHealth()*0.05f));
                }

                this.AITarget.updateSize();
                //this.AITarget.setPosition(this.AITarget.posX, this.AITarget.posY, this.AITarget.posZ);

                if (this.AITarget.isTamed())
                {
                    this.AITarget.SendStatusMessage(EnumSituation.NoSpace);//, this.AITarget.selfType);
                }*/
            } else {
                this.AITarget.sendStatusMessage(EnumSituation.NoSpace);    //, this.AITarget.selfType);

            }
        }
    }
}
