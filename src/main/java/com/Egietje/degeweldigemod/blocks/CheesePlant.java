package com.Egietje.degeweldigemod.blocks;

import java.util.Random;

import com.Egietje.degeweldigemod.init.CheeseItems;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CheesePlant extends BlockCrops {
	@Override
	protected Item getSeed() {
		return CheeseItems.CHEESE_SEEDS;
	}

	@Override
	protected Item getCrop() {
		return CheeseItems.CHEESE;
	}
}