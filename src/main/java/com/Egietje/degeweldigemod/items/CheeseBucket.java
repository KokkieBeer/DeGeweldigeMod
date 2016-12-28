package com.Egietje.degeweldigemod.items;

import java.util.List;

import javax.annotation.Nullable;

import com.Egietje.degeweldigemod.DeGeweldigeMod;
import com.Egietje.degeweldigemod.blocks.CheeseFluid;
import com.Egietje.degeweldigemod.init.CheeseBlocks;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;

public class CheeseBucket extends ItemFood {
	
	public CheeseBucket() {
		super(3, 2.3F, false);
		this.setMaxStackSize(1);
	}

	@Override
	@Nullable
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if (entityLiving instanceof EntityPlayer && !((EntityPlayer) entityLiving).capabilities.isCreativeMode) {
			stack.shrink(1);
			EntityPlayer entityplayer = (EntityPlayer) entityLiving;
			entityplayer.getFoodStats().addStats(this, stack);
		}
		return stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack;
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		List<EntityLivingBase> entities = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, playerIn.getEntityBoundingBox().expand(2, 2, 2));
		for (int i = 0; i < entities.size(); i++) {
			EntityLivingBase entity = entities.get(i);
		}
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn,
				itemstack, raytraceresult);
		if (ret != null)
			return ret;

		if (raytraceresult == null) {
			if(playerIn.canEat(false))
			playerIn.setActiveHand(handIn);
			return new ActionResult(EnumActionResult.FAIL, itemstack);
		} else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
			if(playerIn.canEat(false))
			playerIn.setActiveHand(handIn);
			return new ActionResult(EnumActionResult.FAIL, itemstack);
		} else {
			BlockPos blockpos = raytraceresult.getBlockPos();

			if (!worldIn.isBlockModifiable(playerIn, blockpos)) {
				if(playerIn.canEat(false))
				playerIn.setActiveHand(handIn);
				return new ActionResult(EnumActionResult.FAIL, itemstack);
			}
			boolean flag1 = worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos);
			BlockPos blockpos1 = flag1 && raytraceresult.sideHit == EnumFacing.UP ? blockpos
					: blockpos.offset(raytraceresult.sideHit);

			if (!playerIn.canPlayerEdit(blockpos1, raytraceresult.sideHit, itemstack)) {
				if(playerIn.canEat(false))
				playerIn.setActiveHand(handIn);
				return new ActionResult(EnumActionResult.FAIL, itemstack);
			} else if (this.tryPlaceContainedLiquid(playerIn, worldIn, blockpos1)) {
				return !playerIn.capabilities.isCreativeMode
						? new ActionResult(EnumActionResult.SUCCESS, new ItemStack(Items.BUCKET))
						: new ActionResult(EnumActionResult.SUCCESS, itemstack);
			} else {
				if(playerIn.canEat(false))
				playerIn.setActiveHand(handIn);
				return new ActionResult(EnumActionResult.FAIL, itemstack);
			}
		}
	}

	public boolean tryPlaceContainedLiquid(@Nullable EntityPlayer player, World worldIn, BlockPos posIn) {
		IBlockState iblockstate = worldIn.getBlockState(posIn);
		Material material = iblockstate.getMaterial();
		boolean flag = !material.isSolid();
		boolean flag1 = iblockstate.getBlock().isReplaceable(worldIn, posIn);

		if(player.isSneaking()) {
			return false;
		} else if (!worldIn.isAirBlock(posIn) && !flag && !flag1) {
			return false;
		} else {
			if (worldIn.provider.doesWaterVaporize()) {
				int l = posIn.getX();
				int i = posIn.getY();
				int j = posIn.getZ();
				worldIn.playSound(player, posIn, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F,
						2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

				for (int k = 0; k < 8; ++k) {
					worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double) l + Math.random(),
							(double) i + Math.random(), (double) j + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
				}
			} else {
				if (!worldIn.isRemote && (flag || flag1) && !material.isLiquid()) {
					worldIn.destroyBlock(posIn, true);
				}

				SoundEvent soundevent = SoundEvents.ITEM_BUCKET_EMPTY;
				worldIn.playSound(player, posIn, soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
				worldIn.setBlockState(posIn, CheeseFluid.INSTANCE.getDefaultState(), 11);
			}

			return true;
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack,
			NBTTagCompound nbt) {
		return new FluidBucketWrapper(stack);
	}
}