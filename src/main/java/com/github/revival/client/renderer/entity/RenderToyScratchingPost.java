package com.github.revival.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.github.revival.client.model.ModelToyScratchingPost;

public class RenderToyScratchingPost extends RenderLiving {

	public RenderToyScratchingPost() {
		super(new ModelToyScratchingPost(), 0.4F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("fossil:textures/model/toy/scratching_post.png");
	}
}
