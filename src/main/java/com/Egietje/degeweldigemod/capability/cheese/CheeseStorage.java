package com.Egietje.degeweldigemod.capability.cheese;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CheeseStorage implements IStorage<ICheese> {

	@Override
	public NBTBase writeNBT(Capability<ICheese> capability, ICheese instance, EnumFacing side) {
		NBTTagInt comp = new NBTTagInt(instance.get());
		return comp;
	}

	@Override
	public void readNBT(Capability<ICheese> capability, ICheese instance, EnumFacing side, NBTBase nbt) {
		NBTTagInt comp = (NBTTagInt) nbt;
		instance.set(comp.getInt());
	}

}
