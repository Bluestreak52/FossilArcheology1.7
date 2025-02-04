package com.github.revival.client.renderer.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class TarBubblesFX extends EntityFX {
    private float lavaParticleScale;

    public TarBubblesFX(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        this.motionX *= 0.800000011920929D;
        this.motionY *= 0.800000011920929D;
        this.motionZ *= 0.800000011920929D;
        this.motionY = (double) (this.rand.nextFloat() * 0.4F + 0.05F);
        this.particleRed = 0.0F;
        this.particleAlpha = 0.7F;
        this.particleGreen = this.particleBlue = 0.0F;
        this.particleScale *= this.rand.nextFloat() + 0.2F;
        this.lavaParticleScale = this.particleScale;
        this.particleMaxAge = (int) (16.0D / (Math.random() * 0.8D + 0.2D));
        this.noClip = false;
        this.setParticleTextureIndex(49);
    }

    @Override
    public int getBrightnessForRender(float par1) {
        float f1 = ((float) this.particleAge + par1) / (float) this.particleMaxAge;

        if (f1 < 0.0F) {
            f1 = 0.0F;
        }

        if (f1 > 1.0F) {
            f1 = 1.0F;
        }

        int i = super.getBrightnessForRender(par1);
        short short1 = 240;
        int j = i >> 16 & 255;
        return short1 | j << 16;
    }

    /**
     * Gets how bright this entity is.
     */
    @Override
    public float getBrightness(float par1) {
        return 0.0F;
    }

    @Override
    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
        float f6 = ((float) this.particleAge + par2) / (float) this.particleMaxAge;
        this.particleScale = this.lavaParticleScale * (2.0F - f6 * f6);
        super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
        }

        float f = (float) this.particleMaxAge / 4;

        if (this.rand.nextFloat() > f) {
            this.setDead();
        }

        this.motionY -= 0.0490000128746033D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.0990000128746033D;
        this.motionY *= 0.0990000128746033D;
        this.motionZ *= 0.0990000128746033D;

        if (this.onGround) {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
}
