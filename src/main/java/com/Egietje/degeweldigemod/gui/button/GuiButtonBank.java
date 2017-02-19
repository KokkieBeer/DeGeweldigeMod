package com.Egietje.degeweldigemod.gui.button;

import com.Egietje.degeweldigemod.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiButtonBank extends GuiButton {
	private final ResourceLocation BANK_BUTTON_TEXTURE = new ResourceLocation(
			Reference.MODID + ":textures/gui/bank_button.png");
	private int textureOffsetX;

	public GuiButtonBank(int buttonID, int xPos, int yPos, int type) {
		super(buttonID, xPos, yPos, 20, 20, "");
		if (type == 0 || type == 2) {
			this.textureOffsetX = 0;
		} else if (type == 1 || type == 3) {
			this.textureOffsetX = 20;
		}
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			mc.getTextureManager().bindTexture(BANK_BUTTON_TEXTURE);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			boolean flag = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width
					&& mouseY < this.yPosition + this.height;
			int i = 0;

			if (flag) {
				i += this.height;
			}

			this.drawModalRectWithCustomSizedTexture(this.xPosition, this.yPosition, textureOffsetX, i, this.width, this.height, 40, 40);
		}
	}
}