package com.Egietje.degeweldigemod.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.Egietje.degeweldigemod.entities.tileentities.blocks.TileEntityCheeseSkull;
import com.Egietje.degeweldigemod.init.CheeseBlocks;
import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMaterialMatcher;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CheeseHead extends BlockContainer {
	public static final PropertyDirection FACING = BlockDirectional.FACING;
	public static final PropertyBool NODROP = PropertyBool.create("nodrop");
	protected static final AxisAlignedBB DEFAULT_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
	protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.25D, 0.25D, 0.5D, 0.75D, 0.75D, 1.0D);
	protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.5D);
	protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.5D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
	protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0D, 0.25D, 0.25D, 0.5D, 0.75D, 0.75D);
	private BlockPattern witherBasePattern;
	private BlockPattern witherPattern;

	public CheeseHead() {
		super(Material.CIRCUITS);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(NODROP,
				Boolean.valueOf(false)));
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

	@SideOnly(Side.CLIENT)
	public boolean hasCustomBreakingProgress(IBlockState state) {
		return true;
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch ((EnumFacing) state.getValue(FACING)) {
		case UP:
		default:
			return DEFAULT_AABB;
		case NORTH:
			return NORTH_AABB;
		case SOUTH:
			return SOUTH_AABB;
		case WEST:
			return WEST_AABB;
		case EAST:
			return EAST_AABB;
		}
	}

	/**
	 * Called by ItemBlocks just before a block is actually set in the world, to
	 * allow for adjustments to the IBlockstate
	 */
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing()).withProperty(NODROP,
				Boolean.valueOf(false));
	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing
	 * the block.
	 */
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCheeseSkull();
	}

	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (player.capabilities.isCreativeMode) {
			state = state.withProperty(NODROP, Boolean.valueOf(true));
			worldIn.setBlockState(pos, state, 4);
		}
		this.dropBlockAsItem(worldIn, pos, state, 0);

		super.onBlockHarvested(worldIn, pos, state, player);
	}

	/**
	 * Called serverside after this block is replaced with another in Chunk, but
	 * before the Tile Entity is updated
	 */
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		super.breakBlock(worldIn, pos, state);
	}

	public boolean canDispenserPlace(World worldIn, BlockPos pos, ItemStack stack) {
		return stack.getMetadata() == 1 && pos.getY() >= 2 && worldIn.getDifficulty() != EnumDifficulty.PEACEFUL
				&& !worldIn.isRemote ? this.getWitherBasePattern().match(worldIn, pos) != null : false;
	}

	public void checkWitherSpawn(World worldIn, BlockPos pos, TileEntityCheeseSkull te) {
		if (pos.getY() >= 2 && worldIn.getDifficulty() != EnumDifficulty.PEACEFUL
				&& !worldIn.isRemote) {
			BlockPattern blockpattern = this.getWitherPattern();
			BlockPattern.PatternHelper blockpattern$patternhelper = blockpattern.match(worldIn, pos);

			if (blockpattern$patternhelper != null) {
				for (int i = 0; i < 3; ++i) {
					BlockWorldState blockworldstate = blockpattern$patternhelper.translateOffset(i, 0, 0);
					worldIn.setBlockState(blockworldstate.getPos(),
							blockworldstate.getBlockState().withProperty(NODROP, Boolean.valueOf(true)), 2);
				}

				for (int j = 0; j < blockpattern.getPalmLength(); ++j) {
					for (int k = 0; k < blockpattern.getThumbLength(); ++k) {
						BlockWorldState blockworldstate1 = blockpattern$patternhelper.translateOffset(j, k, 0);
						worldIn.setBlockState(blockworldstate1.getPos(), Blocks.AIR.getDefaultState(), 2);
					}
				}

				BlockPos blockpos = blockpattern$patternhelper.translateOffset(1, 0, 0).getPos();
				EntityWither entitywither = new EntityWither(worldIn);
				BlockPos blockpos1 = blockpattern$patternhelper.translateOffset(1, 2, 0).getPos();
				entitywither.setLocationAndAngles((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.55D,
						(double) blockpos1.getZ() + 0.5D,
						blockpattern$patternhelper.getForwards().getAxis() == EnumFacing.Axis.X ? 0.0F : 90.0F, 0.0F);
				entitywither.renderYawOffset = blockpattern$patternhelper.getForwards().getAxis() == EnumFacing.Axis.X
						? 0.0F : 90.0F;
				entitywither.ignite();

				for (EntityPlayer entityplayer : worldIn.getEntitiesWithinAABB(EntityPlayer.class,
						entitywither.getEntityBoundingBox().expandXyz(50.0D))) {
					entityplayer.addStat(AchievementList.SPAWN_WITHER);
				}

				worldIn.spawnEntity(entitywither);

				for (int l = 0; l < 120; ++l) {
					worldIn.spawnParticle(EnumParticleTypes.SNOWBALL,
							(double) blockpos.getX() + worldIn.rand.nextDouble(),
							(double) (blockpos.getY() - 2) + worldIn.rand.nextDouble() * 3.9D,
							(double) blockpos.getZ() + worldIn.rand.nextDouble(), 0.0D, 0.0D, 0.0D, new int[0]);
				}

				for (int i1 = 0; i1 < blockpattern.getPalmLength(); ++i1) {
					for (int j1 = 0; j1 < blockpattern.getThumbLength(); ++j1) {
						BlockWorldState blockworldstate2 = blockpattern$patternhelper.translateOffset(i1, j1, 0);
						worldIn.notifyNeighborsRespectDebug(blockworldstate2.getPos(), Blocks.AIR, false);
					}
				}
			}
		}
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7)).withProperty(NODROP,
				Boolean.valueOf((meta & 8) > 0));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing) state.getValue(FACING)).getIndex();

		if (((Boolean) state.getValue(NODROP)).booleanValue()) {
			i |= 8;
		}

		return i;
	}

	/**
	 * Returns the blockstate with the given rotation from the passed
	 * blockstate. If inapplicable, returns the passed blockstate.
	 */
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 */
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, NODROP });
	}

	protected BlockPattern getWitherBasePattern() {
		if (this.witherBasePattern == null) {
			this.witherBasePattern = FactoryBlockPattern.start().aisle(new String[] { "   ", "###", "~#~" })
					.where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(CheeseBlocks.CHEESE_BLOCK)))
					.where('~', BlockWorldState.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
		}

		return this.witherBasePattern;
	}

	protected BlockPattern getWitherPattern() {
		if (this.witherPattern == null) {
			this.witherPattern = FactoryBlockPattern.start().aisle(new String[] { "^^^", "###", "~#~" })
					.where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(CheeseBlocks.CHEESE_BLOCK)))
					.where('^', BlockWorldState.hasState(BlockStateMatcher.forBlock(CheeseBlocks.CHEESE_HEAD)))
					.where('~', BlockWorldState.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
		}

		return this.witherPattern;
	}
}