package com.Egietje.degeweldigemod.capability.cheese;

import com.Egietje.degeweldigemod.capability.haditems.IHadItems;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CheeseProvider implements ICapabilitySerializable<NBTBase> {
	
	@CapabilityInject(ICheese.class)
	public static final Capability<ICheese> CHEESE_CAP = null;
	
	private ICheese instance = CHEESE_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CHEESE_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CHEESE_CAP ? CHEESE_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return CHEESE_CAP.getStorage().writeNBT(CHEESE_CAP, instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		CHEESE_CAP.getStorage().readNBT(CHEESE_CAP, instance, null, nbt);
	}

}
