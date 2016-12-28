package com.Egietje.degeweldigemod.capability.cheese;

public class Cheese implements ICheese {
	
	private int cheese = 20;

	@Override
	public void set(int set) {
		cheese = set;
		if(cheese > 20) cheese = 20;
		if(cheese < 0) cheese = 0;
	}

	@Override
	public void add(int add) {
		cheese += add;
		if(cheese > 20) cheese = 20;
		if(cheese < 0) cheese = 0;
	}

	@Override
	public void remove(int remove) {
		cheese -= remove;
		if(cheese > 20) cheese = 20;
		if(cheese < 0) cheese = 0;
	}

	@Override
	public int get() {
		return cheese;
	}

}
