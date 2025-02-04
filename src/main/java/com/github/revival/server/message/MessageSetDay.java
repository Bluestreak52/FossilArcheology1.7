package com.github.revival.server.message;

import com.github.revival.server.entity.mob.test.EntityNewPrehistoric;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.server.network.AbstractMessage;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityBlockDustFX;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;

import java.util.Random;

public class MessageSetDay extends AbstractMessage<MessageSetDay> {

    public int dinosaurID;
    public boolean isDay;

    public MessageSetDay(int dinosaurID, boolean isDay) {
        this.dinosaurID = dinosaurID;
        this.isDay = isDay;
    }

    public MessageSetDay() {}

    @Override
    @SideOnly(Side.CLIENT)
    public void onClientReceived(Minecraft client, MessageSetDay message, EntityPlayer player, MessageContext messageContext) {
        Entity entity = player.worldObj.getEntityByID(message.dinosaurID);

        if (entity instanceof EntityNewPrehistoric) {
            EntityNewPrehistoric prehistoric = (EntityNewPrehistoric) entity;
            prehistoric.isDaytime = message.isDay;
        }
    }

    @Override
    public void onServerReceived(MinecraftServer server, MessageSetDay message, EntityPlayer player, MessageContext messageContext) {}

    @Override
    public void fromBytes(ByteBuf buf) {
        dinosaurID = buf.readInt();
        isDay = buf.readBoolean();

    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(dinosaurID);
        buf.writeBoolean(isDay);
    }
}