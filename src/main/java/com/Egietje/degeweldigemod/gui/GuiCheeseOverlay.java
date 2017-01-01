package com.Egietje.degeweldigemod.gui;

import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.FOOD;

import java.util.Random;

import com.Egietje.degeweldigemod.Reference;
import com.Egietje.degeweldigemod.capability.cheese.CheeseProvider;
import com.Egietje.degeweldigemod.capability.cheese.ICheese;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;

public class GuiCheeseOverlay extends GuiIngameForge {

	public GuiCheeseOverlay(Minecraft mc) {
		super(mc);
	}

	public void renderCheese(int width, int height) {
		mc.mcProfiler.startSection("cheese");

		EntityPlayer player = (EntityPlayer) mc.getRenderViewEntity();
		GlStateManager.enableBlend();
		int left = width / 2 + 91;
		int top = height - right_height;
		right_height += 10;
		ICheese cheese = player.getCapability(CheeseProvider.CHEESE_CAP, null);
		int level = cheese.get();
		int y = top;
		int icon = 16;
		byte background = 0;
		mc.renderEngine.bindTexture(new ResourceLocation(Reference.MODID + ":textures/cheese.png"));

		for (int i = 0; i < 10; ++i) {
			int idx = i * 2 + 1;
			int x = left - i * 8 - 9;

			background = 0;
			
			drawModalRectWithCustomSizedTexture(x, y, 0, 0, 9, 9, 27, 9);

			if (idx < level)
				drawModalRectWithCustomSizedTexture(x, y, 18, 0, 9, 9, 27, 9);
			else if (idx == level)
				drawModalRectWithCustomSizedTexture(x, y, 9, 0, 9, 9, 27, 9);
		}
		GlStateManager.disableBlend();
		mc.mcProfiler.endSection();
	}
}
