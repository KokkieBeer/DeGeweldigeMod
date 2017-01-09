package com.Egietje.degeweldigemod.blocks;

import java.util.ArrayList;
import java.util.List;

import com.Egietje.degeweldigemod.entities.tileentities.blocks.TileEntityCheeseBoard;
import com.Egietje.degeweldigemod.init.CheeseBlocks;
import com.Egietje.degeweldigemod.init.CheeseItems;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CheeseBoard extends Block implements ITileEntityProvider {
	
	public static final AxisAlignedBB CHEESE_BOARD_BLOCK_AABB = new AxisAlignedBB(3.0D / 16, 0.0D, 1.0D / 16, 13.0D / 16, 1.0D / 16, 15.0D / 16);
	public static List<ItemStack> DROPS = new ArrayList<ItemStack>();

	public CheeseBoard() {
		super(Material.WOOD);
		this.setSoundType(SoundType.WOOD);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return CHEESE_BOARD_BLOCK_AABB;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return CHEESE_BOARD_BLOCK_AABB;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing heldItem, float side, float hitX, float hitY) {
		if(hand.equals(EnumHand.MAIN_HAND)) {
			if(!worldIn.isRemote) {
				TileEntity tileEntity = worldIn.getTileEntity(pos);
				if(tileEntity instanceof TileEntityCheeseBoard) {
					TileEntityCheeseBoard cheeseBoard = (TileEntityCheeseBoard) tileEntity;
					ItemStack item = playerIn.getHeldItem(hand);
					if(item != null) {
						if(item.getItem() == CheeseItems.CHEESE) {
							if(cheeseBoard.addCheese()) {
								playerIn.inventory.decrStackSize(playerIn.inventory.currentItem, 1);
								return true;
							}
						}
					}
					cheeseBoard.removeCheese();
				}
			}
		}
		return false;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TileEntityCheeseBoard) {
        	TileEntityCheeseBoard cheese = (TileEntityCheeseBoard) tileentity;
        	while (cheese.CHEESE_COUNT > 0) {
        		cheese.removeCheese();
        	}
        }

        super.breakBlock(worldIn, pos, state);
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

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCheeseBoard();
	}

}
