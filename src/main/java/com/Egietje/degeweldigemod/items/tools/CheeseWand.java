package com.Egietje.degeweldigemod.items.tools;

import com.Egietje.degeweldigemod.entities.cheeseball.EntityCheeseBall;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CheeseWand extends Item {

	public CheeseWand() {
		this.setMaxStackSize(1);
		this.setMaxDamage(73);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if (!worldIn.isRemote) {
			if (worldIn
					.spawnEntity(new EntityCheeseBall(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ,
							playerIn.getLookVec().xCoord, playerIn.getLookVec().yCoord, playerIn.getLookVec().zCoord, playerIn))
					&& !playerIn.isCreative()) {
				stack.damageItem(1, playerIn);
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
