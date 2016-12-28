package com.Egietje.degeweldigemod.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.Egietje.degeweldigemod.DeGeweldigeMod;
import com.Egietje.degeweldigemod.init.CheeseItems;

import net.minecraft.block.Block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class QuickCheese extends Block {

	public QuickCheese() {
		super(Material.WEB);
	}

	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		entityIn.setInWeb();
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return CheeseItems.CHEESE;
	}

	protected boolean canSilkHarvest() {
		return true;
	}
	
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	public boolean isFullCube(IBlockState state) {
        return false;
    }
	
	public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack) {
		if (!worldIn.isRemote && stack != null && stack.getItem() instanceof ItemSpade) {
			spawnAsEntity(worldIn, pos, new ItemStack(Item.getItemFromBlock(this), 1));
		} else {
			super.harvestBlock(worldIn, player, pos, state, te, stack);
		}
	}
}
