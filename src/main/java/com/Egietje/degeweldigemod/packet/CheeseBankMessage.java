package com.Egietje.degeweldigemod.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class CheeseBankMessage implements IMessage {

	public CheeseBankMessage() {
	}

	private int value;

	public CheeseBankMessage(int value) {
		this.value = value;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(value);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		value = buf.readInt();
	}

	public int getValue() {
		return value;
	}
}