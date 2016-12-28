package com.Egietje.degeweldigemod.capability.haditems;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagEnd;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class HadItemsStorage implements IStorage<IHadItems> {

	@Override
	public NBTBase writeNBT(Capability<IHadItems> capability, IHadItems instance, EnumFacing side) {
		NBTTagCompound comp = new NBTTagCompound();
		comp.setBoolean("hadItems", instance.get());
		return comp;
	}

	@Override
	public void readNBT(Capability<IHadItems> capability, IHadItems instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound comp = (NBTTagCompound) nbt;
		instance.set(comp.getBoolean("hadItems"));
	}

}
