package com.Egietje.degeweldigemod.gui.button;

import com.Egietje.degeweldigemod.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiButtonWithImage extends GuiButton {
	private final ResourceLocation BUTTON_TEXTURES = new ResourceLocation(
			Reference.MODID + ":textures/gui/button_images.png");
	private int textureOffsetX;
	private int textureOffsetY;
	private int type;

	public GuiButtonWithImage(int buttonID, int xPos, int yPos, int xOff,int yOff, int type, boolean bank) {
		super(buttonID, xPos, yPos, 20, 20, "");
		textureOffsetX = xOff;
		textureOffsetY = yOff;
		if (bank) {
			this.type = type;
		}
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			boolean flag = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width
					&& mouseY < this.yPosition + this.height;
			int i = 0;

			if (flag) {
				i += this.height;
			}

			this.drawTexturedModalRect(this.xPosition, this.yPosition, textureOffsetX, i + textureOffsetY, 20,20);
			if (this.type == 0) {
				this.drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "Sell", this.xPosition + 10,
						this.yPosition - 10, 0xffffff);
			} else if (this.type == 1) {
				this.drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "Buy", this.xPosition + 10,
						this.yPosition - 10, 0xffffff);
			}
		}
	}
}