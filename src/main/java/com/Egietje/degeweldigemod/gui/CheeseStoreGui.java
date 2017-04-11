package com.Egietje.degeweldigemod.gui;

import java.io.IOException;

import com.Egietje.degeweldigemod.capability.money.IMoney;
import com.Egietje.degeweldigemod.capability.money.MoneyProvider;
import com.Egietje.degeweldigemod.gui.button.GuiButtonWithImage;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class CheeseStoreGui extends GuiScreen {
	private GuiButton BUY_GOLD;
	private GuiButton BUY_DIAMOND;
	private GuiButton BUY_EMERALD;
	private GuiButton BUY_IRON;
	private GuiButton BUY_BREAD;
	private GuiButton BUY_CARROT;
	private GuiButton BUY_COOKIE;
	private GuiButton BUY_CAKE;
	private GuiButton BUY_MELON;
	private GuiButton BUY_CHEESE;
	private GuiButton BUY_CHICKEN;
	private GuiButton BUY_PORK;
	private GuiButton BUY_BEEF;
	private GuiButton BUY_MUTTON;

	@Override
	public void initGui() {
		buttonList.add(new GuiButtonExt(1, this.width / 2 - 100, this.height / 2 - 10, 200, 20,
				"Welcome in the " + TextFormatting.YELLOW + "Cheese Store" + TextFormatting.RESET));
		BUY_GOLD = new GuiButtonWithImage(6, this.width / 2 - 70, this.height / 2 - 30, 20, 0, -1);
		BUY_DIAMOND = new GuiButtonWithImage(7, this.width / 2 - 30, this.height / 2 - 30, 40, 0, -1);
		BUY_EMERALD = new GuiButtonWithImage(8, this.width / 2 + 10, this.height / 2 - 30, 60, 0, -1);
		BUY_IRON = new GuiButtonWithImage(9, this.width / 2 + 50, this.height / 2 - 30, 80, 0, -1);
		BUY_BREAD = new GuiButtonWithImage(10, this.width / 2 - 110, this.height / 2 - 30, 100, 0, -1);
		BUY_CARROT = new GuiButtonWithImage(11, this.width / 2 - 70, this.height / 2 - 30, 120, 0, -1);
		BUY_COOKIE = new GuiButtonWithImage(12, this.width / 2 - 30, this.height / 2 - 30, 140, 0 , -1);
		BUY_CAKE = new GuiButtonWithImage(13, this.width / 2 + 10, this.height / 2 - 30, 160, 0, -1);
		BUY_MELON = new GuiButtonWithImage(14, this.width / 2 + 50, this.height / 2 - 30, 180, 0, -1);
		BUY_CHEESE = new GuiButtonWithImage(15, this.width / 2 + 90, this.height / 2 - 30, 200, 0, -1);
		BUY_CHICKEN = new GuiButtonWithImage(16, this.width / 2 - 70, this.height / 2 - 30, 220, 0, -1);
		BUY_PORK = new GuiButtonWithImage(17, this.width / 2 - 30, this.height / 2 - 30, 0, 40, -1);
		BUY_BEEF = new GuiButtonWithImage(18, this.width / 2 + 10, this.height / 2 - 30, 20, 40, -1);
		BUY_MUTTON = new GuiButtonWithImage(19, this.width / 2 + 50, this.height / 2 - 30, 40, 40, -1);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		buttonList.removeAll(buttonList);
		switch (button.id) {
		case 1:
			buttonList.add(new GuiButtonExt(2, this.width / 2 - 100, this.height / 2 - 30, 200, 20, "Materials"));
			buttonList.add(new GuiButtonExt(3, this.width / 2 - 100, this.height / 2 + 10, 200, 20, "Food"));
			break;
		case 2:
			buttonList.add(BUY_GOLD);
			buttonList.add(BUY_DIAMOND);
			buttonList.add(BUY_EMERALD);
			buttonList.add(BUY_IRON);
			buttonList.add(new GuiButtonExt(1, this.width / 2 - 100, this.height / 2 + 30, 200, 20, "Back"));
			break;
		case 3:
			buttonList.add(new GuiButtonExt(4, this.width / 2 - 100, this.height / 2 - 50, 200, 20, "Fruit and Baked food"));
			buttonList.add(new GuiButtonExt(5, this.width / 2 - 100, this.height / 2 - 10, 200, 20, "Meat"));
			buttonList.add(new GuiButtonExt(1, this.width / 2 - 100, this.height / 2 + 30, 200, 20, "Back"));
			break;
		case 4:
			buttonList.add(BUY_BREAD);
			buttonList.add(BUY_CARROT);
			buttonList.add(BUY_COOKIE);
			buttonList.add(BUY_CAKE);
			buttonList.add(BUY_MELON);
			buttonList.add(BUY_CHEESE);
			buttonList.add(new GuiButtonExt(1, this.width / 2 - 100, this.height / 2 + 30, 200, 20, "Back"));
			break;
		case 5:
			buttonList.add(BUY_CHICKEN);
			buttonList.add(BUY_PORK);
			buttonList.add(BUY_BEEF);
			buttonList.add(BUY_MUTTON);
			buttonList.add(new GuiButtonExt(1, this.width / 2 - 100, this.height / 2 + 30, 200, 20, "Back"));
			break;
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		IMoney moneyCap = this.mc.player.getCapability(MoneyProvider.MONEY_CAP, null);
		this.drawCenteredString(fontRendererObj, "Balance: " + moneyCap.get(), this.width / 2, this.height / 2 - 50, 0xffffff);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
