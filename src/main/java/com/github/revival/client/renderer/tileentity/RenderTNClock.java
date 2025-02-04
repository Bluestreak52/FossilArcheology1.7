package com.github.revival.client.renderer.tileentity;

import com.github.revival.client.model.ModelTNClock;
import com.github.revival.server.block.entity.TileEntityTimeMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Calendar;

@SideOnly(Side.CLIENT)
public class RenderTNClock extends TileEntitySpecialRenderer {
    private static final ResourceLocation loc = new ResourceLocation("fossil:textures/blocks/TNClock.png");
    public final float RndRound = ((float) Math.PI * 2F);
    private final int TickReset = 600;
    private ModelTNClock MainModel = new ModelTNClock();
    private int UpdateTick = 0;
    private float Hour = 0.0F;
    private float Minute = 0.0F;
    private Calendar TimeCheck;

    public void renderTileEntityEnchantmentTableAt(TileEntityTimeMachine var1, double var2, double var4, double var6, float var8) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) var2 + 0.5F, (float) var4 + 0.75F, (float) var6 + 0.5F);
        float var9 = (float) var1.field_40068_a + var8;
        GL11.glTranslatef(0.0F, 0.5F + MathHelper.sin(var9 * 0.1F) * 0.01F, 0.0F);
        float var10;

        for (var10 = var1.CurrectFacingAngle - var1.SendingCurrentFacing; var10 >= (float) Math.PI; var10 -= ((float) Math.PI * 2F)) {
        }

        while (var10 < -(float) Math.PI) {
            var10 += ((float) Math.PI * 2F);
        }

        float var11 = var1.SendingCurrentFacing + var10 * var8;
        GL11.glRotatef(-var11 * 180.0F / (float) Math.PI, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        this.bindTexture(loc);
        float var12 = 0.0F;
        float var13 = 0.0F;
        var12 = (var12 - (float) MathHelper.truncateDoubleToInt((double) var12)) * 1.6F - 0.3F;
        var13 = (var13 - (float) MathHelper.truncateDoubleToInt((double) var13)) * 1.6F - 0.3F;

        if (var12 < 0.0F) {
            var12 = 0.0F;
        }

        if (var13 < 0.0F) {
            var13 = 0.0F;
        }

        if (var12 > 1.0F) {
            var12 = 1.0F;
        }

        if (var13 > 1.0F) {
            var13 = 1.0F;
        }

        float var14 = 0.0F;
        ModelTNClock var15 = this.MainModel;

        if (!var1.isRestoring) {
            this.showRealTime(var15);
        } else {
            this.showProgress(var15, var1);
        }

        if (var1.PlayerClosing) {
            var15.EdgeRotate(var9, 0.5F, var13, var14, 0.0F, 0.0625F);
        } else {
            var15.EdgePullBack();
        }
        if (var1.isClockInPlace()) {
            this.MainModel.render((Entity) null, var9, 0.5F, var13, var14, 0.0F, 0.0625F);
        }
        GL11.glPopMatrix();
    }

    private void showProgress(ModelTNClock var1, TileEntityTimeMachine var2) {
        float var10002 = (float) var2.getChargeLevel();
        var2.getClass();
        var1.UpdateTime(0.0F, var10002 / 1000.0F * ((float) Math.PI * 2F));
    }

    @Override
    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
        this.renderTileEntityEnchantmentTableAt((TileEntityTimeMachine) var1, var2, var4, var6, var8);
    }

    public void showRealTime(ModelTNClock var1) {
        if (this.UpdateTick == 0) {
            this.TimeCheck = Calendar.getInstance();
            this.Hour = (float) this.TimeCheck.get(10);
            this.Minute = (float) this.TimeCheck.get(12);
            this.UpdateTick = 600;
            var1.UpdateTime(this.Hour / 12.0F * ((float) Math.PI * 2F), this.Minute / 60.0F * ((float) Math.PI * 2F));
        } else {
            --this.UpdateTick;
        }
    }
}
