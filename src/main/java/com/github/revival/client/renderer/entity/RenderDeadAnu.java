package com.github.revival.client.renderer.entity;

import com.github.revival.server.entity.mob.EntityAnuDead;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderDeadAnu extends RenderLiving {
    private static final ResourceLocation anu = new ResourceLocation("fossil:textures/model/PigBoss.png");

    public RenderDeadAnu(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    public void renderDodo(EntityAnuDead par1Entity, double par2, double par4, double par6, float par8, float par9) {
        super.doRender(par1Entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110919_a(EntityAnuDead par1Entity) {
        return anu;

    }

    @Override
    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        this.renderDodo((EntityAnuDead) par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.func_110919_a((EntityAnuDead) par1Entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderDodo((EntityAnuDead) par1Entity, par2, par4, par6, par8, par9);
    }
}
