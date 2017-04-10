package com.Egietje.degeweldigemod.gui;

import java.io.IOException;

import com.Egietje.degeweldigemod.capability.money.IMoney;
import com.Egietje.degeweldigemod.capability.money.MoneyProvider;
import com.Egietje.degeweldigemod.gui.button.GuiButtonWithImage;
import com.Egietje.degeweldigemod.handler.CheesePacketHandler;
import com.Egietje.degeweldigemod.init.CheeseItems;
import com.Egietje.degeweldigemod.packet.CheeseBankMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class CheeseBankGui extends GuiScreen {
	private GuiButtonWithImage COIN_SELL;
	private GuiButtonWithImage GOLD_SELL;
	private GuiButtonWithImage COIN_BUY;
	private GuiButtonWithImage GOLD_BUY;

	@Override
	public void initGui() {
		buttonList.add(new GuiButtonExt(1, this.width / 2 - 100, this.height / 2 - 10, 200, 20,
				"Welcome in the " + TextFormatting.YELLOW + "Cheese Bank" + TextFormatting.RESET));
		COIN_SELL = new GuiButtonWithImage(2, this.width / 2 - 30, this.height / 2 - 30, "sell_coin", true);
		GOLD_SELL = new GuiButtonWithImage(3, this.width / 2 + 10, this.height / 2 - 30, "sell_gold", true);
		COIN_BUY = new GuiButtonWithImage(4, this.width / 2 - 30, this.height / 2 + 10, "buy_coin", true);
		GOLD_BUY = new GuiButtonWithImage(5, this.width / 2 + 10, this.height / 2 + 10, "buy_gold", true);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		buttonList.removeAll(buttonList);
		switch (button.id) {
		case 1:
			buttonList.add(COIN_SELL);
			buttonList.add(GOLD_SELL);
			buttonList.add(COIN_BUY);
			buttonList.add(GOLD_BUY);
			break;
		case 2:
			boolean containsCheese = false;
			IMoney moneyCapCheese1 = this.mc.player.getCapability(MoneyProvider.MONEY_CAP, null);
			for (int i = 0; i < this.mc.player.inventory.getSizeInventory(); i++) {
				Item item = this.mc.player.inventory.getStackInSlot(i).getItem();
				if (item == CheeseItems.CHEESE_MONEY) {
					containsCheese = true;
				}
			}
			if (containsCheese) {
				CheesePacketHandler.INSTANCE.sendToServer(new CheeseBankMessage(10));
				moneyCapCheese1.add(10);
				buttonList.add(new GuiButtonExt(6, this.width / 2 - 100, this.height / 2 - 10, 200, 20,
						"Succesfully added " + 10 + " C-coins"));
			} else {
				buttonList.add(
						new GuiButtonExt(6, this.width / 2 - 100, this.height / 2 - 10, 200, 20, "Not enough cheese"));
			}
			break;
		case 3:
			boolean containsGold = false;
			IMoney moneyCapGold1 = this.mc.player.getCapability(MoneyProvider.MONEY_CAP, null);
			for (int i = 0; i < this.mc.player.inventory.getSizeInventory(); i++) {
				Item item = this.mc.player.inventory.getStackInSlot(i).getItem();
				if (item == Items.GOLD_INGOT) {
					containsGold = true;
				}
			}
			if (containsGold) {
				CheesePacketHandler.INSTANCE.sendToServer(new CheeseBankMessage(50));
				moneyCapGold1.add(50);
				buttonList.add(new GuiButtonExt(6, this.width / 2 - 100, this.height / 2 - 10, 200, 20,
						"Succesfully added " + 50 + " C-coins"));
			} else {
				buttonList.add(
						new GuiButtonExt(6, this.width / 2 - 100, this.height / 2 - 10, 200, 20, "Not enough gold"));
			}
			break;
		case 4:
			boolean moneyCheese = false;
			IMoney moneyCapCheese2 = this.mc.player.getCapability(MoneyProvider.MONEY_CAP, null);
			if (moneyCapCheese2.get() >= 10) {
				moneyCheese = true;
			} else {
				moneyCheese = false;
			}
			if (moneyCheese) {
				CheesePacketHandler.INSTANCE.sendToServer(new CheeseBankMessage(-10));
				buttonList.add(new GuiButtonExt(6, this.width / 2 - 100, this.height / 2 - 10, 200, 20,
						"Succesfully bought a cheese coin"));
				moneyCapCheese2.remove(10);
			} else {
				buttonList.add(
						new GuiButtonExt(6, this.width / 2 - 100, this.height / 2 - 10, 200, 20, "Not enough money"));
			}
			break;
		case 5:
			boolean moneyGold = false;
			IMoney moneyCapGold2 = this.mc.player.getCapability(MoneyProvider.MONEY_CAP, null);
			if (moneyCapGold2.get() >= 50) {
				moneyGold = true;
			} else {
				moneyGold = false;
			}
			if (moneyGold) {
				CheesePacketHandler.INSTANCE.sendToServer(new CheeseBankMessage(-50));
				moneyCapGold2.remove(50);
				buttonList.add(new GuiButtonExt(6, this.width / 2 - 100, this.height / 2 - 10, 200, 20,
						"Succesfully bought gold"));
			} else {
				buttonList.add(
						new GuiButtonExt(6, this.width / 2 - 100, this.height / 2 - 10, 200, 20, "Not enough money"));
			}
			break;
		case 6:
			buttonList.add(new GuiButtonExt(1, this.width / 2 - 100, this.height / 2 - 22, 200, 20, "Back"));
			buttonList.add(new GuiButtonExt(7, this.width / 2 - 100, this.height / 2 + 2, 200, 20, "Done"));
			break;
		case 7:
			this.mc.displayGuiScreen((GuiScreen) null);
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
