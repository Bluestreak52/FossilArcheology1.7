package com.github.revival.server.message;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.server.network.AbstractMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import com.github.revival.server.entity.toy.EntityToyBall;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MessageRollBall extends AbstractMessage<MessageRollBall> {

    public int ballID;
    public int rollTicks;

    public MessageRollBall(int ballID, int rollTicks) {
        this.ballID = ballID;
        this.rollTicks = rollTicks;
    }

    public MessageRollBall() {}

    @Override
    @SideOnly(Side.CLIENT)
    public void onClientReceived(Minecraft client, MessageRollBall message, EntityPlayer player, MessageContext messageContext) {
        Entity entity = player.worldObj.getEntityByID(message.ballID);
        if (entity instanceof EntityToyBall) {
        	EntityToyBall ball = (EntityToyBall) entity;
        	ball.rollValue = message.rollTicks;
        }
    }

    @Override
    public void onServerReceived(MinecraftServer server, MessageRollBall message, EntityPlayer player, MessageContext messageContext) {}

    @Override
    public void fromBytes(ByteBuf buf) {
        ballID = buf.readInt();
        rollTicks = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(ballID);
        buf.writeInt(rollTicks);
    }
}