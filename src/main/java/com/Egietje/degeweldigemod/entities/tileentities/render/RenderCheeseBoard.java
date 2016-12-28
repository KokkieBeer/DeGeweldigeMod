package com.Egietje.degeweldigemod.entities.tileentities.render;

import com.Egietje.degeweldigemod.entities.tileentities.blocks.TileEntityCheeseBoard;
import com.Egietje.degeweldigemod.init.CheeseItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class RenderCheeseBoard extends TileEntitySpecialRenderer<TileEntityCheeseBoard> {
	
	private static final EntityItem ITEM = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0, new ItemStack(CheeseItems.CHEESE));
	
	@Override
	public void renderTileEntityAt(TileEntityCheeseBoard te, double x, double y, double z, float partialTicks, int destroyStage) {
		ITEM.hoverStart = 0F;
		GlStateManager.pushMatrix(); {
			GlStateManager.translate(x + 0.5, y + 0.066, z + 0.75);
			GlStateManager.rotate(90F, 1.0F, 0.0F, 0.0F);
			GlStateManager.scale(0.25F, 0.25F, 0.25F);
			for (int i = 0; i < te.CHEESE_COUNT; i++) {
				Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0, 0, 0, 0F, 0F, true);
				GlStateManager.translate(0, -0.4F, 0);
			}
		}
		GlStateManager.popMatrix();
	}

}
