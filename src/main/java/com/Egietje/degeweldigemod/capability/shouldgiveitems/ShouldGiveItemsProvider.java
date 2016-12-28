package com.Egietje.degeweldigemod.capability.shouldgiveitems;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ShouldGiveItemsProvider implements ICapabilitySerializable<NBTBase> {

	@CapabilityInject(IShouldGiveItems.class)
	public static final Capability<IShouldGiveItems> SHOULD_GIVE_CAP = null;
	
	private IShouldGiveItems instance = SHOULD_GIVE_CAP.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == SHOULD_GIVE_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == SHOULD_GIVE_CAP ? SHOULD_GIVE_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return SHOULD_GIVE_CAP.getStorage().writeNBT(SHOULD_GIVE_CAP, instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		SHOULD_GIVE_CAP.getStorage().readNBT(SHOULD_GIVE_CAP, instance, null, nbt);
	}

}
