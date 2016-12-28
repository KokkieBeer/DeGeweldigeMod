package com.Egietje.degeweldigemod.packet;

import com.Egietje.degeweldigemod.init.CheeseAchievements;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CheeseMessage implements IMessage {
	
	public CheeseMessage(){
	}
	
	private boolean creative;
	private int amount;
	
	public CheeseMessage(boolean isCreative, int cookieAmount) {
		this.amount = cookieAmount;
		this.creative = isCreative;
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(creative);
		buf.writeInt(amount);
	}
	
	@Override 
	public void fromBytes(ByteBuf buf) {
		creative = buf.readBoolean();
		amount = buf.readInt();
	}
	
	public int getAmount() {
		return amount;
	}
	
	public boolean isCreative() {
		return creative;
	}
}
