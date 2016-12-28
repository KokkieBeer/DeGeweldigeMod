package com.Egietje.degeweldigemod.capability.haditems;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class HadItemsProvider implements ICapabilitySerializable<NBTBase> {
	
	@CapabilityInject(IHadItems.class)
	public static final Capability<IHadItems> HAD_ITEMS_CAP = null;
	
	private IHadItems instance = HAD_ITEMS_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == HAD_ITEMS_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == HAD_ITEMS_CAP ? HAD_ITEMS_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return HAD_ITEMS_CAP.getStorage().writeNBT(HAD_ITEMS_CAP, instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		HAD_ITEMS_CAP.getStorage().readNBT(HAD_ITEMS_CAP, instance, null, nbt);
	}

}
