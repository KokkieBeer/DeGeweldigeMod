package com.Egietje.degeweldigemod.blocks;

import com.Egietje.degeweldigemod.DeGeweldigeMod;
import com.Egietje.degeweldigemod.gui.CheeseCookieGui;
import com.Egietje.degeweldigemod.handler.CheeseGuiHandler;
import com.Egietje.degeweldigemod.init.CheeseAchievements;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CheeseCookieBlock extends Block {
	public static EntityPlayer player;

	public CheeseCookieBlock() {
		super(Material.ROCK);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing heldItem, float hitX, float hitY, float hitZ) {
		player = playerIn;
		playerIn.openGui(DeGeweldigeMod.DGMInstance, CheeseGuiHandler.COOKIEGUIID, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, hitX, hitY, hitZ);
	}
}
