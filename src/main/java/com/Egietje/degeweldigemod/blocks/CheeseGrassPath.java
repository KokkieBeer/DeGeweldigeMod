package com.Egietje.degeweldigemod.blocks;

import java.util.Random;
import javax.annotation.Nullable;

import com.Egietje.degeweldigemod.init.CheeseBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CheeseGrassPath extends Block {
	protected static final AxisAlignedBB GRASS_PATH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);

	public CheeseGrassPath() {
		super(Material.GROUND);
		this.setLightOpacity(255);
		this.setSoundType(SoundType.PLANT);
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		switch (side) {
		case UP:
			return true;
		case NORTH:
		case SOUTH:
		case WEST:
		case EAST:
			IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
			Block block = iblockstate.getBlock();
			return !iblockstate.isOpaqueCube() && block != Blocks.FARMLAND && block != CheeseBlocks.CHEESE_GRASS_PATH;
		default:
			return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
		}
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return GRASS_PATH_AABB;
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks
	 * for render
	 */
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	/**
	 * Get the Item that this Block should drop when harvested.
	 */
	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return CheeseBlocks.CHEESE_DIRT.getItemDropped(state, rand, fortune);
	}

	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(this);
	}

	/**
	 * Called when a neighboring block was changed and marks that this state
	 * should perform any checks during a neighbor change. Cases may include
	 * when redstone power is updated, cactus blocks popping off due to a
	 * neighboring solid block, etc.
	 */
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos p_189540_5_) {
		super.neighborChanged(state, worldIn, pos, blockIn, p_189540_5_);

		if (worldIn.getBlockState(pos.up()).getMaterial().isSolid()) {
			worldIn.setBlockState(pos, CheeseBlocks.CHEESE_DIRT.getDefaultState());
		}
	}
}