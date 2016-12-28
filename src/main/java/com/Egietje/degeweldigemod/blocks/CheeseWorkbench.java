package com.Egietje.degeweldigemod.blocks;

import com.Egietje.degeweldigemod.containers.ContainerCheeseWorkbench;
import com.Egietje.degeweldigemod.init.CheeseBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;

public class CheeseWorkbench extends Block {
	public CheeseWorkbench() {
		super(Material.WOOD);
		this.setCreativeTab(CreativeTabs.DECORATIONS);
	}

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing heldItem, float side, float hitX, float hitY) {
		if (worldIn.isRemote) {
			return false;
		} else {
			playerIn.displayGui(new CheeseWorkbench.InterfaceCheeseCraftingTable(worldIn, pos));
			return true;
		}
	}

	public static class InterfaceCheeseCraftingTable implements IInteractionObject {
		private final World world;
		private final BlockPos position;

		public InterfaceCheeseCraftingTable(World worldIn, BlockPos pos) {
			this.world = worldIn;
			this.position = pos;
		}

		/**
		 * Get the name of this object. For players this returns their username
		 */
		public String getName() {
			return "crafting_table";
		}

		/**
		 * Returns true if this thing is named
		 */
		public boolean hasCustomName() {
			return false;
		}

		/**
		 * Get the formatted ChatComponent that will be used for the sender's
		 * username in chat
		 */
		public ITextComponent getDisplayName() {
			return new TextComponentTranslation(CheeseBlocks.CHEESE_CRAFTING_TABLE.getUnlocalizedName() + ".name", new Object[0]);
		}

		public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
			return new ContainerCheeseWorkbench(playerInventory, this.world, this.position);
		}

		public String getGuiID() {
			return "minecraft:crafting_table";
		}
	}
}