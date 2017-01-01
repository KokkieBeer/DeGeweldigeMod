package com.Egietje.degeweldigemod.render;

import com.Egietje.degeweldigemod.Reference;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerCheeseEars implements LayerRenderer<AbstractClientPlayer> {
	private final RenderPlayer playerRenderer;

	public LayerCheeseEars(RenderPlayer playerRendererIn) {
		this.playerRenderer = playerRendererIn;
	}

	public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (!"deadmau5".equals(entitylivingbaseIn.getName()) && !entitylivingbaseIn.isInvisible()) {
			if (!"degeweldigekaas".equals(entitylivingbaseIn.getName().toLowerCase())
					&& !"egietje".equals(entitylivingbaseIn.getName().toLowerCase())) {
				this.playerRenderer
						.bindTexture(new ResourceLocation(Reference.MODID + ":textures/entity/cheese_ears_normal.png"));
			} else {
				this.playerRenderer.bindTexture(
						new ResourceLocation(Reference.MODID + ":textures/entity/cheese_ears_special.png"));
			}

			for (int i = 0; i < 2; ++i) {
				float f = entitylivingbaseIn.prevRotationYaw
						+ (entitylivingbaseIn.rotationYaw - entitylivingbaseIn.prevRotationYaw) * partialTicks
						- (entitylivingbaseIn.prevRenderYawOffset
								+ (entitylivingbaseIn.renderYawOffset - entitylivingbaseIn.prevRenderYawOffset)
										* partialTicks);
				float f1 = entitylivingbaseIn.prevRotationPitch
						+ (entitylivingbaseIn.rotationPitch - entitylivingbaseIn.prevRotationPitch) * partialTicks;
				GlStateManager.pushMatrix();
				GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(f1, 1.0F, 0.0F, 0.0F);
				if (!entitylivingbaseIn.isSneaking()) {
					GlStateManager.translate(0.375F * (float) (i * 2 - 1), 0.0F, 0.0F);
				} else {
					GlStateManager.translate(0.375F * (float) (i * 2 - 1), 0.25F, 0.0F);
				}
				GlStateManager.translate(0.0F, -0.375F, 0.0F);
				GlStateManager.rotate(-f1, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(-f, 0.0F, 1.0F, 0.0F);
				float f2 = 1.3333334F;
				GlStateManager.scale(1.3333334F, 1.3333334F, 1.3333334F);
				this.playerRenderer.getMainModel().renderDeadmau5Head(0.0625F);
				GlStateManager.popMatrix();
			}
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return true;
	}
}