package com.Egietje.degeweldigemod.gui;

import java.io.IOException;

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
		BUY_GOLD = new GuiButtonWithImage(6, this.width / 2 - 70, this.height / 2 - 30, "buy_gold", false);
		BUY_DIAMOND = new GuiButtonWithImage(7, this.width / 2 - 30, this.height / 2 - 30, "buy_diamond", false);
		BUY_EMERALD = new GuiButtonWithImage(8, this.width / 2 + 10, this.height / 2 - 30, "buy_emerald", false);
		BUY_IRON = new GuiButtonWithImage(9, this.width / 2 + 50, this.height / 2 - 30, "buy_iron", false);
		BUY_BREAD = new GuiButtonWithImage(10, this.width / 2 - 110, this.height / 2 - 30, "buy_bread", false);
		BUY_CARROT = new GuiButtonWithImage(11, this.width / 2 - 70, this.height / 2 - 30, "buy_carrot", false);
		BUY_COOKIE = new GuiButtonWithImage(12, this.width / 2 - 30, this.height / 2 - 30, "buy_cookie", false);
		BUY_CAKE = new GuiButtonWithImage(13, this.width / 2 + 10, this.height / 2 - 30, "buy_cake", false);
		BUY_MELON = new GuiButtonWithImage(14, this.width / 2 + 50, this.height / 2 - 30, "buy_cake", false);
		BUY_CHEESE = new GuiButtonWithImage(15, this.width / 2 + 90, this.height / 2 - 30, "buy_cheese", false);
		BUY_CHICKEN = new GuiButtonWithImage(16, this.width / 2 - 70, this.height / 2 - 30, "buy_chicken", false);
		BUY_PORK = new GuiButtonWithImage(17, this.width / 2 - 30, this.height / 2 - 30, "buy_pork", false);
		BUY_BEEF = new GuiButtonWithImage(18, this.width / 2 + 10, this.height / 2 - 30, "buy_beef", false);
		BUY_MUTTON = new GuiButtonWithImage(19, this.width / 2 + 50, this.height / 2 - 30, "buy_mutton", false);
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

	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
