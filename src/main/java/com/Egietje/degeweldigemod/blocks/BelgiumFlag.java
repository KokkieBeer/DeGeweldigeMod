package com.Egietje.degeweldigemod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BelgiumFlag extends Block {

	public BelgiumFlag() {
		super(Material.CLOTH);
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
}
