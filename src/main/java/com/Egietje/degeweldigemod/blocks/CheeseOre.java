package com.Egietje.degeweldigemod.blocks;

import java.util.Random;

import com.Egietje.degeweldigemod.init.CheeseBlocks;
import com.Egietje.degeweldigemod.init.CheeseItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CheeseOre extends Block {

	public CheeseOre() {
		this(Material.ROCK.getMaterialMapColor());
	}

	public CheeseOre(MapColor color) {
		super(Material.ROCK, color);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return this == CheeseBlocks.CHEESE_ORE ? CheeseItems.CHEESE : Item.getItemFromBlock(this);
	}

	public int quantityDroppedWithBonus(int fortune, Random random) {
		if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(
				(IBlockState) this.getBlockState().getValidStates().iterator().next(), random, fortune)) {
			int i = random.nextInt(fortune + 2) - 1;

			if (i < 0) {
				i = 0;
			}

			return this.quantityDropped(random) * (i + 1);
		} else {
			return this.quantityDropped(random);
		}
	}

	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
	}

	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
		Random rand = world instanceof World ? ((World) world).rand : new Random();
		if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this)) {
			int i = 0;

			if (this == CheeseBlocks.CHEESE_ORE) {
				i = MathHelper.getInt(rand, 5, 10);
			}

			return i;
		}
		return 0;
	}

	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(this);
	}

}
