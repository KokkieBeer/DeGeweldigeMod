package com.Egietje.degeweldigemod.entities.tileentities.blocks;

import com.Egietje.degeweldigemod.blocks.CheeseMirror;
import com.Egietje.degeweldigemod.entities.EntityCheeseMirror;
import com.Egietje.degeweldigemod.entities.tileentities.render.RenderCheeseMirror;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityCheeseMirror extends TileEntity {
	private EntityCheeseMirror bindedMirror = null;

	@SideOnly(Side.CLIENT)
	public EntityCheeseMirror getMirror() {
		if (bindedMirror == null) {
			if (getBlockType() instanceof CheeseMirror) {
				EnumFacing facing = (EnumFacing) world.getBlockState(pos).getValue(CheeseMirror.FACING);
				bindedMirror = new EntityCheeseMirror(world, pos.getX(), pos.getY(), pos.getZ(), facing);
				world.spawnEntity(bindedMirror);
			}
		}
		return bindedMirror;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onChunkUnload() {
		if (bindedMirror != null) {
			RenderCheeseMirror.removeRegisteredMirror(bindedMirror);
			bindedMirror.setDead();
			bindedMirror = null;
		}
	}
}