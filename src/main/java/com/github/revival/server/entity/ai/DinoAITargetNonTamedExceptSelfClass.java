package com.github.revival.server.entity.ai;

import com.github.revival.server.entity.mob.EntityDinosaur;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.passive.EntityTameable;

public class DinoAITargetNonTamedExceptSelfClass extends EntityAITargetNonTamed {
    private EntityTameable theTameable;

    public DinoAITargetNonTamedExceptSelfClass(EntityTameable tameable, Class targetclass, int var4, boolean var5) {
        super(tameable, targetclass, var4, var5);
        this.theTameable = tameable;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        boolean var1 = super.shouldExecute();
        return !(var1 && this.taskOwner instanceof EntityDinosaur && ((EntityDinosaur) this.taskOwner).SelfType == ((EntityDinosaur) this.taskOwner).SelfType) && var1;
    }
}
