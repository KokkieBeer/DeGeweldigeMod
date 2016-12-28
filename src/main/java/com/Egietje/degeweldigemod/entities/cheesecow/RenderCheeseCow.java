package com.Egietje.degeweldigemod.entities.cheesecow;

import com.Egietje.degeweldigemod.Reference;
import com.Egietje.degeweldigemod.entities.cheesecow.EntityCheeseCow;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCheeseCow extends RenderLiving<EntityCheeseCow> {
	private static final ResourceLocation CHEESE_COW_TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/cheese_cow.png");

	public RenderCheeseCow(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
		super(renderManagerIn, modelBaseIn, shadowSizeIn);
	}

	protected ResourceLocation getEntityTexture(EntityCheeseCow entity) {
		return CHEESE_COW_TEXTURES;
	}
}