package com.Egietje.degeweldigemod.items;

import com.Egietje.degeweldigemod.DeGeweldigeMod;
import com.Egietje.degeweldigemod.capability.cheese.CheeseProvider;
import com.Egietje.degeweldigemod.capability.cheese.ICheese;
import com.Egietje.degeweldigemod.capability.haditems.HadItemsProvider;
import com.Egietje.degeweldigemod.capability.haditems.IHadItems;
import com.Egietje.degeweldigemod.init.CheeseAchievements;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class Cheese extends ItemFood {

	public Cheese(int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		this.setAlwaysEdible();
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			player.addStat(CheeseAchievements.EAT);
			ICheese cheese = player.getCapability(CheeseProvider.CHEESE_CAP, null);
			cheese.add(2);
		}
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
}
