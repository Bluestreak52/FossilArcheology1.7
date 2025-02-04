package com.github.revival.server.entity.mob;

import com.github.revival.server.enums.EnumPrehistoric;
import net.minecraft.block.material.Material;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public abstract class EntitySwimmingDino extends EntityDinosaur {
    public Random rand;

    public EntitySwimmingDino(World world, EnumPrehistoric dinotype) {
        super(world, dinotype);

        this.rand = new Random();
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    /**
     * Moves the entity based on the specified heading.  Args: strafe, forward
     */
    @Override
    public void moveEntityWithHeading(float par1, float par2) {
        double d0;

        if (this.isInWater()) {
            this.moveFlying(par1, par2, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.900000011920929D;
            this.motionY *= 0.900000011920929D;
            this.motionZ *= 0.900000011920929D;
        } else if (this.handleLavaMovement()) {
            this.moveFlying(par1, par2, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
        } else {
            float f2 = 0.91F;

            if (this.onGround) {
                f2 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)).slipperiness * 0.91F;

            }

            float f3 = 0.16277136F / (f2 * f2 * f2);
            float f4;

            if (this.onGround) {
                f4 = this.getAIMoveSpeed() * f3;
            } else {
                f4 = this.jumpMovementFactor;
            }

            this.moveFlying(par1, par2, f4);
            f2 = 0.91F;

            if (this.onGround) {
                f2 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)).slipperiness * 0.91F;

            }


            this.moveEntity(this.motionX, this.motionY, this.motionZ);

            if (this.isInsideOfMaterial(Material.water)) {
                this.motionY = 0.0D;
                this.motionX *= (double) f2;
                this.motionY *= (double) f2;
                this.motionZ *= (double) f2;
            } else {
                this.motionY -= 0.08D;
                this.motionY *= 0.9800000190734863D;
                this.motionX *= (double) f2;
                this.motionZ *= (double) f2;
            }
        }

        this.prevLimbSwingAmount = this.limbSwingAmount;
        d0 = this.posX - this.prevPosX;
        double d1 = this.posZ - this.prevPosZ;
        float f6 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

        if (f6 > 1.0F) {
            f6 = 1.0F;
        }

        this.limbSwingAmount += (f6 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }


    /**
     * returns true if this entity is by a ladder, false otherwise
     */
    @Override
    public boolean isOnLadder() {
        return false;
    }
}
