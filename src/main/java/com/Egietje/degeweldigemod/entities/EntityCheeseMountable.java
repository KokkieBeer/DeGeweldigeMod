package com.Egietje.degeweldigemod.entities;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityCheeseMountable extends Entity {

	public int posX;
	public int posY;
	public int posZ;

	public EntityCheeseMountable(World worldIn) {
		super(worldIn);
		this.noClip = true;
		this.height = 0.01F;
		this.width = 0.01F;
	}

	public EntityCheeseMountable(World worldIn, double x, double y, double z, double yOffset) {
		this(worldIn);
		posX = (int) x;
		posY = (int) y;
		posZ = (int) z;
		this.setPosition(x + 0.5, y + yOffset, z + 0.5);
	}

	public EntityCheeseMountable(World worldIn, double x, double y, double z, double yOffset, int rot, int rotOffset) {
		this(worldIn);
		posX = (int) x;
		posY = (int) y;
		posZ = (int) z;
		this.setPositionConsideringRotation(x + 0.5, y + yOffset, z + 0.5, rot, rotOffset);
	}

	public void setPositionConsideringRotation(double x, double y, double z, int rot, int rotOffset) {
		switch (rot) {
		case 2:
			z += rotOffset;
			break;
		case 0:
			z -= rotOffset;
			break;
		case 1:
			x += rotOffset;
			break;
		case 3:
			x -= rotOffset;
			break;
		}
		this.setPosition(x, y, z);
	}

	@Override
	public double getMountedYOffset() {
		return 0;
	}

	@Override
	protected boolean shouldSetPosAfterLoading() {
		return false;
	}

	@Override
	public void onEntityUpdate() {
		if (!this.world.isRemote) {
			if (!isBeingRidden() || this.world.isAirBlock(new BlockPos(posX, posY, posZ))) {
				this.setDead();
				world.updateComparatorOutputLevel(getPosition(), world.getBlockState(getPosition()).getBlock());
				
			}
		}
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
	}

}
