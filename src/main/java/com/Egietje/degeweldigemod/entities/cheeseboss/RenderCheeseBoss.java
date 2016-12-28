package com.Egietje.degeweldigemod.entities.cheeseboss;

import com.Egietje.degeweldigemod.Reference;
import com.Egietje.degeweldigemod.entities.cheeseboss.EntityCheeseBoss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCheeseBoss extends RenderLiving<EntityCheeseBoss> {
	private static final ResourceLocation CHEESE_BOSS_TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/cheese_boss.png");

	public RenderCheeseBoss(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
		super(renderManagerIn, modelBaseIn, shadowSizeIn);
	}

	protected ResourceLocation getEntityTexture(EntityCheeseBoss entity) {
		return CHEESE_BOSS_TEXTURES;
	}
}