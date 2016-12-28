package com.Egietje.degeweldigemod.entities.tileentities.blocks;

import com.Egietje.degeweldigemod.init.CheeseItems;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCheeseBoard extends TileEntity {
	
	public int CHEESE_COUNT = 0;
	
	public boolean addCheese() {
		if(this.CHEESE_COUNT < 8) {
			this.CHEESE_COUNT++;
			markDirty();
			IBlockState state = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 3);
			return true;
		}
		return false;
	}
	
	public void removeCheese() {
		if(this.CHEESE_COUNT > 0) {
			world.spawnEntity(new EntityItem(world, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, new ItemStack(CheeseItems.CHEESE)));
			this.CHEESE_COUNT--;
			markDirty();
			IBlockState state = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 3);
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		writeUpdateTag(compound);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		readUpdateTag(compound);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound compound = pkt.getNbtCompound();
		readUpdateTag(compound);
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound compound = new NBTTagCompound();
		writeUpdateTag(compound);
		return new SPacketUpdateTileEntity(pos, getBlockMetadata(), compound);
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound compound = super.getUpdateTag();
		writeUpdateTag(compound);
		return compound;
	}
	
	public void writeUpdateTag(NBTTagCompound compound) {
		compound.setInteger("CheeseCount", this.CHEESE_COUNT);
	}
	
	public void readUpdateTag(NBTTagCompound compound) {
		this.CHEESE_COUNT =  compound.getInteger("CheeseCount");
	}

}
