package com.Egietje.degeweldigemod.blocks;

import java.util.List;

import com.Egietje.degeweldigemod.entities.tileentities.blocks.TileEntityCheeseMirror;
import com.Egietje.degeweldigemod.init.CheeseTabs;
import com.Egietje.degeweldigemod.init.CheeseUtils;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CheeseMirror extends Block implements ITileEntityProvider {
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public CheeseMirror() {
		super(Material.GLASS);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setSoundType(SoundType.METAL);
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
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		IBlockState state = super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
		return state.withProperty(FACING, placer.getHorizontalFacing());
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	private static final AxisAlignedBB BOUNDING_BOX_NORTH = CheeseUtils.getBlockBounds(EnumFacing.NORTH, 0.9375,
			0.0, 0.0, 1.0, 1.0, 1.0);
	private static final AxisAlignedBB BOUNDING_BOX_EAST = CheeseUtils.getBlockBounds(EnumFacing.EAST, 0.9375, 0.0,
			0.0, 1.0, 1.0, 1.0);
	private static final AxisAlignedBB BOUNDING_BOX_SOUTH = CheeseUtils.getBlockBounds(EnumFacing.SOUTH, 0.9375,
			0.0, 0.0, 1.0, 1.0, 1.0);
	private static final AxisAlignedBB BOUNDING_BOX_WEST = CheeseUtils.getBlockBounds(EnumFacing.WEST, 0.9375, 0.0,
			0.0, 1.0, 1.0, 1.0);
	private static final AxisAlignedBB[] BOUNDING_BOX = { BOUNDING_BOX_SOUTH, BOUNDING_BOX_WEST, BOUNDING_BOX_NORTH,
			BOUNDING_BOX_EAST };

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(FACING);
		return BOUNDING_BOX[facing.getHorizontalIndex()];
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_) {
		EnumFacing facing = state.getValue(FACING);
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, BOUNDING_BOX[facing.getHorizontalIndex()]);
		super.addCollisionBoxToList(state, worldIn, pos, entityBox, collidingBoxes, entityIn, p_185477_7_);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCheeseMirror();
	}
}