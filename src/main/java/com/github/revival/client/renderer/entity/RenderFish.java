package com.github.revival.client.renderer.entity;

import org.lwjgl.opengl.GL11;

import com.github.revival.server.entity.mob.test.EntityFishBase;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderFish extends RenderLiving {


    public RenderFish(ModelBase model) {
        super(model, 0.3F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof EntityFishBase) {
            EntityFishBase prehistoric = (EntityFishBase) entity;
            return new ResourceLocation(prehistoric.getTexture());
        } else {
            return null;
        }
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f) {
    	if(((EntityFishBase)entity).isChild()){
    		GL11.glScalef(0.5F, 0.5F, 0.5F);
    	}
    }

}
