package com.Egietje.degeweldigemod.handler;

import com.Egietje.degeweldigemod.Reference;
import com.Egietje.degeweldigemod.handler.packet.*;
import com.Egietje.degeweldigemod.init.CheeseAchievements;
import com.Egietje.degeweldigemod.packet.*;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class CheesePacketHandler {
	
	public CheesePacketHandler() {
		registerPackets();
	}
	
	public static SimpleNetworkWrapper INSTANCE;
	public static int ID = 0;
	
	public void registerPackets() {
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("dgmChannel");
		INSTANCE.registerMessage(CheeseMessageHandler.class, CheeseMessage.class, ID++, Side.SERVER);
		INSTANCE.registerMessage(CheeseFillMessageHandler.class, CheeseFillMessage.class, ID++, Side.CLIENT);
		INSTANCE.registerMessage(CheeseBankMessageHandler.class, CheeseBankMessage.class, ID++, Side.SERVER);
	}
}
