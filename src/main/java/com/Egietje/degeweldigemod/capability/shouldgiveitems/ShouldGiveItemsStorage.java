package com.Egietje.degeweldigemod.capability.shouldgiveitems;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ShouldGiveItemsStorage implements IStorage<IShouldGiveItems> {

	@Override
	public NBTBase writeNBT(Capability<IShouldGiveItems> capability, IShouldGiveItems instance, EnumFacing side) {
		NBTTagCompound comp = new NBTTagCompound();
		comp.setBoolean("shouldGive", instance.get());
		return comp;
	}

	@Override
	public void readNBT(Capability<IShouldGiveItems> capability, IShouldGiveItems instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound comp = (NBTTagCompound) nbt;
		instance.set(comp.getBoolean("shouldGive"));
	}

}
