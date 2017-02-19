package com.Egietje.degeweldigemod.capability.money;

import com.Egietje.degeweldigemod.capability.haditems.IHadItems;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class MoneyProvider implements ICapabilitySerializable<NBTBase> {
	
	@CapabilityInject(IMoney.class)
	public static final Capability<IMoney> MONEY_CAP = null;
	
	private IMoney instance = MONEY_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == MONEY_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == MONEY_CAP ? MONEY_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return MONEY_CAP.getStorage().writeNBT(MONEY_CAP, instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		MONEY_CAP.getStorage().readNBT(MONEY_CAP, instance, null, nbt);
	}

}
