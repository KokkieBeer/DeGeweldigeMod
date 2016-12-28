package com.Egietje.degeweldigemod.init;

import com.Egietje.degeweldigemod.capability.cheese.*;
import com.Egietje.degeweldigemod.capability.haditems.*;
import com.Egietje.degeweldigemod.capability.shouldgiveitems.*;

import net.minecraftforge.common.capabilities.CapabilityManager;

public class CheeseCapabilitys {

	public CheeseCapabilitys() {
		register();
	}
	
	public void register() {
		CapabilityManager.INSTANCE.register(IHadItems.class, new HadItemsStorage(), HadItems.class);
		CapabilityManager.INSTANCE.register(IShouldGiveItems.class, new ShouldGiveItemsStorage(), ShouldGiveItems.class);
		CapabilityManager.INSTANCE.register(ICheese.class, new CheeseStorage(), Cheese.class);
	}
	
}
