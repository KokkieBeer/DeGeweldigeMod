package com.Egietje.degeweldigemod.capability.money;

public class Money implements IMoney {
	
	private int money = 20;

	@Override
	public void set(int set) {
		money = set;
		if(money < 0) money = 0;
	}

	@Override
	public void add(int add) {
		money += add;
		if(money < 0) money = 0;
	}

	@Override
	public void remove(int remove) {
		money -= remove;
		if(money < 0) money = 0;
	}

	@Override
	public int get() {
		return money;
	}

}
