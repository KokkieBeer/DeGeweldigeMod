package com.Egietje.degeweldigemod.entities.tileentities.blocks;

import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Iterables;

import net.minecraft.block.BlockSkull;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityCheeseSkull extends TileEntity {
	private int skullRotation;

	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setByte("Rot", (byte) (this.skullRotation & 255));
		return compound;
	}

	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.skullRotation = compound.getByte("Rot");
	}

	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 4, this.getUpdateTag());
	}

	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@SideOnly(Side.CLIENT)
	public int getSkullRotation() {
		return this.skullRotation;
	}

	public void setSkullRotation(int rotation) {
		this.skullRotation = rotation;
	}

	public void mirror(Mirror mirrorIn) {
		if (this.world != null
				&& this.world.getBlockState(this.getPos()).getValue(BlockSkull.FACING) == EnumFacing.UP) {
			this.skullRotation = mirrorIn.mirrorRotation(this.skullRotation, 16);
		}
	}

	public void rotate(Rotation rotationIn) {
		if (this.world != null
				&& this.world.getBlockState(this.getPos()).getValue(BlockSkull.FACING) == EnumFacing.UP) {
			this.skullRotation = rotationIn.rotate(this.skullRotation, 16);
		}
	}
}