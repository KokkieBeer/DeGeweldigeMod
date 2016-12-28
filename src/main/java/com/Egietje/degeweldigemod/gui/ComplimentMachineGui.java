package com.Egietje.degeweldigemod.gui;

import java.io.IOException;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class ComplimentMachineGui extends GuiScreen {
	Random random = new Random();
	World worldIn = Minecraft.getMinecraft().world;
	EntityPlayer playerIn = Minecraft.getMinecraft().player;
	public static int compliment;
	
	@Override
	public void initGui() {
		buttonList.add(new GuiButtonExt(1, width / 2 - 100, height / 2 + 20, 200, 20, "Ok."));
		compliment = random.nextInt(8);
		super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch(button.id) {
		case 1 :
			buttonList.removeAll(buttonList);
			this.mc.displayGuiScreen((GuiScreen)null);
			break;
		}
		super.actionPerformed(button);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawWorldBackground(compliment);
		switch(compliment) {
		case 0:
			drawCenteredString(fontRendererObj, TextFormatting.YELLOW + playerIn.getDisplayNameString() + TextFormatting.GOLD +" is AWESOME." + TextFormatting.RESET, width / 2, height / 2 - 20, 0xFFFF00);
			break;
		case 1:
			drawCenteredString(fontRendererObj, TextFormatting.GOLD + "I love " + TextFormatting.YELLOW + playerIn.getDisplayNameString() + TextFormatting.GOLD +  "." + TextFormatting.RESET, width / 2, height / 2 - 20, 0xFFFF00);
			break;
		case 2:
			drawCenteredString(fontRendererObj, TextFormatting.YELLOW + playerIn.getDisplayNameString() + TextFormatting.GOLD + " is nice." + TextFormatting.RESET, width / 2, height / 2 - 20, 0xFFFF00);
			break;
		case 3:
			drawCenteredString(fontRendererObj, TextFormatting.YELLOW + playerIn.getDisplayNameString() + TextFormatting.GOLD + " is my BFF." + TextFormatting.RESET, width / 2, height / 2 - 20, 0xFFFF00);
			break;
		case 4:
			drawCenteredString(fontRendererObj, TextFormatting.YELLOW + playerIn.getDisplayNameString() + TextFormatting.GOLD + " is good at everything." + TextFormatting.RESET, width / 2, height / 2 - 20, 0xFFFF00);
			break;
		case 5:
			drawCenteredString(fontRendererObj, TextFormatting.YELLOW + playerIn.getDisplayNameString() + TextFormatting.GOLD + " is perfect." + TextFormatting.RESET, width / 2, height / 2 - 20, 0xFFFF00);
			break;
		case 6:
			drawCenteredString(fontRendererObj, TextFormatting.YELLOW + playerIn.getDisplayNameString() + TextFormatting.GOLD + " will win every contest." + TextFormatting.RESET, width / 2, height / 2 - 20, 0xFFFF00);
			break;
		case 7:
			drawCenteredString(fontRendererObj, TextFormatting.YELLOW + playerIn.getDisplayNameString() + TextFormatting.GOLD + " is strong." + TextFormatting.RESET, width / 2, height / 2 - 20, 0xFFFF00);
			break;
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

}
