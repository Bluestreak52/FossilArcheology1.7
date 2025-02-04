package com.github.revival.server.block;

import com.github.revival.server.handler.LocalizationStrings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockLimestone extends Block {

    public BlockLimestone(Material material) {
        super(Material.rock);
        setHardness(0.3F);
        setBlockName(LocalizationStrings.BLOCK_LIMESTONE_NAME);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon("fossil:Limestone");
    }

}
