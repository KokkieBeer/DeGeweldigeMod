package com.Egietje.degeweldigemod.capability.shouldgiveitems;

public class ShouldGiveItems implements IShouldGiveItems {
	
	private boolean shouldGive = false;
	public static boolean worldCreateGive = false;

	@Override
	public void set(boolean set) {
		shouldGive = set;
	}

	@Override
	public boolean get() {
		return shouldGive;
	}

}
