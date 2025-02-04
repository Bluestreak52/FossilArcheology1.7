package com.github.revival.server.entity.mob;

import com.github.revival.server.entity.mob.test.EntityFishBase;
import com.github.revival.server.enums.EnumPrehistoric;
import com.github.revival.server.item.FAItemRegistry;

import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.Random;

public class EntityAlligatorGar extends EntityFishBase {
    public EntityAlligatorGar(World par1World) {
        super(par1World, EnumPrehistoric.Alligator_Gar);
		this.setSize(1.9F, 1F);
    }
    
    @Override
    public String getTexture() {
        return "fossil:textures/model/fish/alligator_gar.png";
    }

	@Override
    protected double getSwimSpeed() {
		return 0.45D;
    }
}
