package com.Egietje.degeweldigemod.blocks;

import java.util.Random;

import com.Egietje.degeweldigemod.init.CheeseBlocks;
import com.Egietje.degeweldigemod.init.CheeseItems;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class CheeseDirt extends Block {

	public CheeseDirt() {
		super(Material.GROUND);
		this.setSoundType(SoundType.GROUND);
	}

}
