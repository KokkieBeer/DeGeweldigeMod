package com.Egietje.degeweldigemod.handler.packet;

import com.Egietje.degeweldigemod.capability.cheese.CheeseProvider;
import com.Egietje.degeweldigemod.capability.cheese.ICheese;
import com.Egietje.degeweldigemod.init.CheeseAchievements;
import com.Egietje.degeweldigemod.packet.CheeseFillMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CheeseFillMessageHandler implements IMessageHandler<CheeseFillMessage, IMessage> {
	@Override
    public IMessage onMessage(final CheeseFillMessage message, final MessageContext ctx) {
		final EntityPlayerSP player = Minecraft.getMinecraft().player;
        IThreadListener mainThread = Minecraft.getMinecraft().getIntegratedServer();
        mainThread.addScheduledTask(new Runnable() {
            @Override
            public void run() {
                ICheese cheese = player.getCapability(CheeseProvider.CHEESE_CAP, null);
                cheese.set(20);
            }
        });
        return null;
    }
}