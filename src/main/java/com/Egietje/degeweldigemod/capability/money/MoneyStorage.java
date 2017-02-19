package com.Egietje.degeweldigemod.capability.money;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class MoneyStorage implements IStorage<IMoney> {

	@Override
	public NBTBase writeNBT(Capability<IMoney> capability, IMoney instance, EnumFacing side) {
		NBTTagInt comp = new NBTTagInt(instance.get());
		return comp;
	}

	@Override
	public void readNBT(Capability<IMoney> capability, IMoney instance, EnumFacing side, NBTBase nbt) {
		NBTTagInt comp = (NBTTagInt) nbt;
		instance.set(comp.getInt());
	}

}
