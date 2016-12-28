package com.Egietje.degeweldigemod.items.tools;

import java.util.Set;

import com.Egietje.degeweldigemod.DeGeweldigeMod;
import com.Egietje.degeweldigemod.init.CheeseBlocks;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;

public class CheeseMultitool extends ItemTool {

	private static final Set<Block> effectiveBlocks = Sets.newHashSet(new Block[] { Blocks.PLANKS, Blocks.BOOKSHELF,
			Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK,
			Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE, Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE,
			Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE,
			Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE,
			Blocks.IRON_BLOCK, CheeseBlocks.CHEESE_BLOCK, CheeseBlocks.CHEESE_ORE, CheeseBlocks.CHEESE_ORE_END,
			CheeseBlocks.CHEESE_ORE_NETHER, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE,
			Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL,
			Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB,
			Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS,
			Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND,
			Blocks.GRASS_PATH });

	public CheeseMultitool(Item.ToolMaterial materialIn, int maxUses) {
		super(materialIn, effectiveBlocks);
		this.damageVsEntity = 1.0F;
		this.attackSpeed = -3.0F;
		this.setMaxDamage(maxUses);
	}

	public CheeseMultitool(Item.ToolMaterial materialIn, float damage, float speed, int maxUses) {
		super(materialIn, effectiveBlocks);
		this.damageVsEntity = damage;
		this.attackSpeed = speed;
		this.setMaxDamage(maxUses);
	}

	public boolean canHarvestBlock(IBlockState blockIn) {
		Block block = blockIn.getBlock();
		if (block == Blocks.OBSIDIAN) {
			return this.toolMaterial.getHarvestLevel() == 3;
		} else if (block != Blocks.DIAMOND_BLOCK && block != Blocks.DIAMOND_ORE) {
			if (block != Blocks.EMERALD_ORE && block != Blocks.EMERALD_BLOCK) {
				if (block != Blocks.GOLD_BLOCK && block != Blocks.GOLD_ORE) {
					if (block != Blocks.IRON_BLOCK && block != Blocks.IRON_ORE) {
						if (block != Blocks.LAPIS_BLOCK && block != Blocks.LAPIS_ORE) {
							if (block != Blocks.REDSTONE_ORE && block != Blocks.LIT_REDSTONE_ORE) {
								Material material = blockIn.getMaterial();
								return material == Material.ROCK ? true
										: (material == Material.IRON ? true
												: material == Material.ANVIL ? true
														: material == Material.WOOD ? true
																: material == Material.PLANTS ? true
																		: material == Material.VINE ? true
																				: block == Blocks.SNOW_LAYER ? true
																						: block == Blocks.SNOW);
							} else {
								return this.toolMaterial.getHarvestLevel() >= 2;
							}
						} else {
							return this.toolMaterial.getHarvestLevel() >= 1;
						}
					} else {
						return this.toolMaterial.getHarvestLevel() >= 1;
					}
				} else {
					return this.toolMaterial.getHarvestLevel() >= 2;
				}
			} else {
				return this.toolMaterial.getHarvestLevel() >= 2;
			}
		} else {
			return this.toolMaterial.getHarvestLevel() >= 2;
		}
	}

	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!playerIn.canPlayerEdit(pos.offset(facing), facing, stack)) {
			return EnumActionResult.FAIL;
		} else {
			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if (facing != EnumFacing.DOWN && worldIn.getBlockState(pos.up()).getMaterial() == Material.AIR
					&& block == Blocks.GRASS) {
				IBlockState iblockstate1 = Blocks.GRASS_PATH.getDefaultState();
				worldIn.playSound(playerIn, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

				if (!worldIn.isRemote) {
					worldIn.setBlockState(pos, iblockstate1, 11);
					stack.damageItem(1, playerIn);
				}

				return EnumActionResult.SUCCESS;
			} else if (facing != EnumFacing.DOWN && worldIn.getBlockState(pos.up()).getMaterial() == Material.AIR
					&& block == CheeseBlocks.CHEESE_GRASS) {
				IBlockState iblockstate1 = CheeseBlocks.CHEESE_GRASS_PATH.getDefaultState();
				worldIn.playSound(playerIn, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

				if (!worldIn.isRemote) {
					worldIn.setBlockState(pos, iblockstate1, 11);
					stack.damageItem(1, playerIn);
				}

				return EnumActionResult.SUCCESS;
			} else {
				return EnumActionResult.PASS;
			}
		}
	}

	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE
				&& material != Material.IRON && material != Material.ANVIL && material != Material.ROCK
						? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
	}

	// public CheeseMultitool(ToolMaterial materialIn) {
	// super(8.0F, -3.0F, materialIn, effectiveBlocks);
	// }

}
