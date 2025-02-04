package com.github.revival.client.renderer.item;

import com.github.revival.client.model.ModelVaseAmphora;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

public class ItemVaseAmphoraRenderer implements IItemRenderer {
    private static final ResourceLocation damaged_amphora = new ResourceLocation("fossil:textures/blocks/vases/vase_damaged_amphora.png");
    private static final ResourceLocation restored_amphora = new ResourceLocation("fossil:textures/blocks/vases/vase_restored_amphora.png");
    private static final ResourceLocation redFigure_amphora = new ResourceLocation("fossil:textures/blocks/vases/vase_redFigure_amphora.png");
    private static final ResourceLocation blackFigure_amphora = new ResourceLocation("fossil:textures/blocks/vases/vase_blackFigure_amphora.png");
    private static final ResourceLocation porcelain_amphora = new ResourceLocation("fossil:textures/blocks/vases/vase_porcelain_amphora.png");
    private static ModelVaseAmphora model;

    public ItemVaseAmphoraRenderer() {
        model = new ModelVaseAmphora();

    }

    private static void bindTexture(ResourceLocation texture) {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        //return true;
        return type != ItemRenderType.INVENTORY;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        int meta = item.getItemDamage();
        glPushMatrix();
        GL11.glTranslatef(0.0F, 2.3F, 0.7F);
        GL11.glRotatef(-180.0F, 0F, 0F, 1F);
        GL11.glRotatef(-80.0F, 0F, 1F, 0F);

        switch (meta) {
            case 0:
            default:
                bindTexture(damaged_amphora);
                break;
            case 1:
                bindTexture(restored_amphora);
                break;
            case 2:
                bindTexture(redFigure_amphora);
                break;
            case 3:
                bindTexture(blackFigure_amphora);
                break;
            case 4:
                bindTexture(porcelain_amphora);
                break;
        }


        model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

        glPopMatrix();
    }
}