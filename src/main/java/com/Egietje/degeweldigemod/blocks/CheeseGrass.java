package com.Egietje.degeweldigemod.blocks;

import java.util.Random;

import com.Egietje.degeweldigemod.init.CheeseBlocks;
import com.Egietje.degeweldigemod.init.CheeseItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class CheeseGrass extends Block implements IGrowable {

	public CheeseGrass() {
		super(Material.GRASS);
		this.setSoundType(SoundType.PLANT);
		this.setTickRandomly(true);
	}
	
	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
		if(plantable.getPlantType(world, pos) != null) {
			if(plantable.getPlantType(world, pos) == EnumPlantType.Plains) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return CheeseBlocks.CHEESE_DIRT.getItemDropped(state, rand, fortune);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing heldItem, float hitX, float hitY, float hitZ) {
		if(hand.equals(EnumHand.MAIN_HAND)) {
			if(!worldIn.isRemote) {
				ItemStack item = playerIn.getHeldItem(hand);
				if(heldItem != null) {
					if(item.getItem() instanceof ItemSpade) {
						if(worldIn.getBlockState(pos.up()).getMaterial().equals(Material.AIR)) {
							if(!playerIn.isCreative()) item.setItemDamage(item.getItemDamage() - 1);
							worldIn.setBlockState(pos, CheeseBlocks.CHEESE_GRASS_PATH.getDefaultState());
							return true;
						}
					} else if(item.getItem() instanceof ItemHoe) {
						if(worldIn.getBlockState(pos.up()).getMaterial().equals(Material.AIR)) {
							if(!playerIn.isCreative()) item.setItemDamage(item.getItemDamage() - 1);
							worldIn.setBlockState(pos, CheeseBlocks.CHEESE_FARM_LAND.getDefaultState());
							return true;
						}
					}
				}
			}
			if(worldIn.isRemote) {
				ItemStack item = playerIn.getHeldItem(hand);
				if(heldItem != null) {
					if(item.getItem() instanceof ItemSpade) {
						if(worldIn.getBlockState(pos.up()).getMaterial().equals(Material.AIR)) {
			                worldIn.playSound(playerIn, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
							return true;
						}
					} else if(item.getItem() instanceof ItemHoe) {
						if(worldIn.getBlockState(pos.up()).getMaterial().equals(Material.AIR)) {
			                worldIn.playSound(playerIn, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			if (worldIn.getLightFromNeighbors(pos.up()) < 4
					&& worldIn.getBlockState(pos.up()).getLightOpacity(worldIn, pos.up()) > 2) {
				worldIn.setBlockState(pos, CheeseBlocks.CHEESE_DIRT.getDefaultState());
			} else {
				if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
					for (int i = 0; i < 4; ++i) {
						BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

						if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos)) {
							return;
						}

						IBlockState iblockstate = worldIn.getBlockState(blockpos.up());
						IBlockState iblockstate1 = worldIn.getBlockState(blockpos);

						if (iblockstate1.getBlock() == CheeseBlocks.CHEESE_DIRT
								&& worldIn.getLightFromNeighbors(blockpos.up()) >= 4
								&& iblockstate.getLightOpacity(worldIn, pos.up()) <= 2) {
							worldIn.setBlockState(blockpos, CheeseBlocks.CHEESE_GRASS.getDefaultState());
						}
					}
				}
			}
		}
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		BlockPos blockpos = pos.up();

		for (int i = 0; i < 128; ++i) {
			BlockPos blockpos1 = blockpos;
			int j = 0;

			while (true) {
				if (j >= i / 16) {
					if (worldIn.isAirBlock(blockpos1)) {
						if (rand.nextInt(8) == 0) {
							BlockFlower.EnumFlowerType blockflower$enumflowertype = worldIn
									.getBiomeForCoordsBody(blockpos1).pickRandomFlower(rand, blockpos1);
							BlockFlower blockflower = blockflower$enumflowertype.getBlockType().getBlock();
							IBlockState iblockstate = blockflower.getDefaultState()
									.withProperty(blockflower.getTypeProperty(), blockflower$enumflowertype);

							if (blockflower.canBlockStay(worldIn, blockpos1, iblockstate)) {
								worldIn.setBlockState(blockpos1, iblockstate, 3);
							}
						} else {
							IBlockState iblockstate1 = Blocks.TALLGRASS.getDefaultState()
									.withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS);

							if (Blocks.TALLGRASS.canBlockStay(worldIn, blockpos1, iblockstate1)) {
								worldIn.setBlockState(blockpos1, iblockstate1, 3);
							}
						}
					}

					break;
				}

				blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2,
						rand.nextInt(3) - 1);

				if (worldIn.getBlockState(blockpos1.down()).getBlock() != CheeseBlocks.CHEESE_GRASS
						|| worldIn.getBlockState(blockpos1).isNormalCube()) {
					break;
				}

				++j;
			}
		}
	}

}
