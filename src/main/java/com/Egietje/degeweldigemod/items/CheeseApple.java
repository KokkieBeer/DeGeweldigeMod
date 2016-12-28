package com.Egietje.degeweldigemod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class CheeseApple extends ItemFood{

	public CheeseApple() {
		super(6, 1.5F, false);
		this.setAlwaysEdible();
	}
	
	@Override
	public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if(!worldIn.isRemote) {
			player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 600, 0, false, false));
			player.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 10, 19, false, false));
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 50, 39, false, false));
		}
	}
	
}
