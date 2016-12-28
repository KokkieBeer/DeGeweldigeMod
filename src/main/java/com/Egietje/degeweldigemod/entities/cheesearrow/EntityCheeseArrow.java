package com.Egietje.degeweldigemod.entities.cheesearrow;

import com.Egietje.degeweldigemod.init.CheeseItems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;

public class EntityCheeseArrow extends EntityArrow {
	private int duration = 200;

	public EntityCheeseArrow(World worldIn) {
		super(worldIn);
	}

	public EntityCheeseArrow(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	public EntityCheeseArrow(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (this.world.isRemote && !this.inGround) {
			this.world.spawnParticle(EnumParticleTypes.END_ROD, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
		} else if (!this.world.isRemote && this.inGround) {
			world.createExplosion(shootingEntity, this.posX, this.posY, this.posZ, 1.0F, true);
			this.setDead();
		}
	}
	
	@Override
	public ItemStack getArrowStack() {
		return new ItemStack(CheeseItems.CHEESE_ARROW);
	}
	
	@Override
	public void arrowHit(EntityLivingBase living) {
		super.arrowHit(living);
		World world = living.getEntityWorld();
		if(living != shootingEntity) {
			world.createExplosion(shootingEntity, living.posX, living.posY, living.posZ, 1.0F, true);
		}
	}
}