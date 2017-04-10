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

	public GuiButtonWithImage(int buttonID, int xPos, int yPos, String type, boolean bank) {
		super(buttonID, xPos, yPos, 20, 20, "");
		if (type == "sell_coin" || type == "buy_coin") {
			this.textureOffsetX = 0;
			this.textureOffsetY = 0;
		} else if (type == "sell_gold" || type == "buy_gold") {
			this.textureOffsetX = 20;
			this.textureOffsetY = 0;
		} else if (type == "buy_diamond") {
			this.textureOffsetX = 40;
			this.textureOffsetY = 0;
		} else if (type == "buy_emerald") {
			this.textureOffsetX = 60;
			this.textureOffsetY = 0;
		} else if (type == "buy_iron") {
			this.textureOffsetX = 80;
			this.textureOffsetY = 0;
		} else if (type == "buy_bread") {
			this.textureOffsetX = 100;
			this.textureOffsetY = 0;
		} else if (type == "buy_carrot") {
			this.textureOffsetX = 120;
			this.textureOffsetY = 0;
		} else if (type == "buy_cookie") {
			this.textureOffsetX = 140;
			this.textureOffsetY = 0;
		} else if (type == "buy_cake") {
			this.textureOffsetX = 160;
			this.textureOffsetY = 0;
		} else if (type == "buy_melon") {
			this.textureOffsetX = 180;
			this.textureOffsetY = 0;
		} else if (type == "buy_cheese") {
			this.textureOffsetX = 200;
			this.textureOffsetY = 0;
		} else if (type == "buy_chicken") {
			this.textureOffsetX = 220;
			this.textureOffsetY = 0;
		} else if (type == "buy_pork") {
			this.textureOffsetX = 240;
			this.textureOffsetY = 40;
		} else if (type == "buy_beef") {
			this.textureOffsetX = 260;
			this.textureOffsetY = 40;
		} else if (type == "buy_mutton") {
			this.textureOffsetX = 280;
			this.textureOffsetY = 40;
		}
		if (bank && (type == "sell_coin" || type == "sell_gold")) {
			this.type = 0;
		} else if (bank && (type == "buy_coin" || type == "buy_gold")) {
			this.type = 1;
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