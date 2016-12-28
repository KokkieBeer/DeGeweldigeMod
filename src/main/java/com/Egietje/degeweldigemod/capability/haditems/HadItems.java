package com.Egietje.degeweldigemod.capability.haditems;

public class HadItems implements IHadItems {
	
	private boolean hadItems = false;

	@Override
	public void set(boolean set) {
		hadItems = set;
	}

	@Override
	public boolean get() {
		return hadItems;
	}

}
