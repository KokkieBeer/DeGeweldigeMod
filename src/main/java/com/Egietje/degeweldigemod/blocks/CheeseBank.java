package com.Egietje.degeweldigemod.blocks;

import com.Egietje.degeweldigemod.DeGeweldigeMod;
import com.Egietje.degeweldigemod.handler.CheeseGuiHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CheeseBank extends Block {
	public CheeseBank() {
		super(Material.ROCK);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		playerIn.openGui(DeGeweldigeMod.DGMInstance, CheeseGuiHandler.BANKID, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
}
