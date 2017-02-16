package com.Egietje.degeweldigemod.blocks;

import java.util.List;
import java.util.Random;

import com.Egietje.degeweldigemod.entities.EntityCheeseMountable;
import com.Egietje.degeweldigemod.init.CheeseUtils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CheeseChair extends Block {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final AxisAlignedBB AABB = new AxisAlignedBB(1 / 16D, 0D, 1 / 16D, 15 / 16D, 20 / 16D, 15 / 16D);
	public static final AxisAlignedBB CHAIR = new AxisAlignedBB(1 / 16D, 0D, 1 / 16D, 15 / 16D, 20 / 16D, 15 / 16D);
	public static final AxisAlignedBB BACK_NORTH = CheeseUtils.getBlockBounds(EnumFacing.SOUTH, 14 / 16D, 9 / 16D, 1 / 16D, 15 / 16D, 20 / 16D, 15 / 16D);
	public static final AxisAlignedBB BACK_SOUTH = CheeseUtils.getBlockBounds(EnumFacing.NORTH, 14 / 16D, 9 / 16D, 1 / 16D, 15 / 16D, 20 / 16D, 15 / 16D);
	public static final AxisAlignedBB BACK_EAST = CheeseUtils.getBlockBounds(EnumFacing.WEST, 14 / 16D, 9 / 16D, 1 / 16D, 15 / 16D, 20 / 16D, 15 / 16D);
	public static final AxisAlignedBB BACK_WEST = CheeseUtils.getBlockBounds(EnumFacing.EAST, 14 / 16D, 9 / 16D, 1 / 16D, 15 / 16D, 20 / 16D, 15 / 16D);

	public CheeseChair() {
		super(Material.WOOD);
		this.setSoundType(SoundType.WOOD);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!CheeseUtils.Mountable.sitOnBlock(worldIn, pos.getX(), pos.getY(), pos.getZ(), playerIn, 8 / 16D)) {
			worldIn.updateComparatorOutputLevel(pos, this);
			return true;
		}
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_) {
		if (!(entityIn instanceof EntityCheeseMountable)) {
			EnumFacing facing = state.getValue(FACING);
			switch (facing) {
			case NORTH:
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, BACK_NORTH);
				break;
			case SOUTH:
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, BACK_SOUTH);
				break;
			case WEST:
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, BACK_WEST);
				break;
			default:
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, BACK_EAST);
				break;
			}
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, CHAIR);
		}
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, CHAIR);
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		IBlockState state = super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
		state = state.withProperty(FACING, placer.getHorizontalFacing().getOpposite());
		return state;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		return CheeseUtils.Mountable.isSomeoneSitting(worldIn, pos.getX(), pos.getY(), pos.getZ()) ? 1 : 0;
	}
}
