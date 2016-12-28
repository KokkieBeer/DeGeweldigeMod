package com.Egietje.degeweldigemod.items;

import com.Egietje.degeweldigemod.init.CheeseBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FlintAndCheese extends Item {
	public FlintAndCheese() {
		this.maxStackSize = 1;
		this.setMaxDamage(128);
	}

	/**
	 * Called when a Block is right-clicked with this Item
	 */
	public EnumActionResult onItemUse(EntityPlayer stack, World playerIn, BlockPos worldIn, EnumHand pos,
			EnumFacing hand, float facing, float hitX, float hitY) {
		worldIn = worldIn.offset(hand);
		ItemStack itemstack = stack.getHeldItem(pos);

		if (!stack.canPlayerEdit(worldIn, hand, itemstack)) {
			return EnumActionResult.FAIL;
		} else {
			if (playerIn.isAirBlock(worldIn)) {
				playerIn.playSound(stack, worldIn, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F,
						itemRand.nextFloat() * 0.4F + 0.8F);
				playerIn.setBlockState(worldIn, CheeseBlocks.CHEESE_FIRE.getDefaultState(), 11);
			}

			itemstack.damageItem(1, stack);
			return EnumActionResult.SUCCESS;
		}
	}
}