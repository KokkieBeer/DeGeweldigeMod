package com.Egietje.degeweldigemod.gui;

import java.io.IOException;

import com.Egietje.degeweldigemod.capability.money.IMoney;
import com.Egietje.degeweldigemod.capability.money.MoneyProvider;
import com.Egietje.degeweldigemod.gui.button.GuiButtonBank;
import com.Egietje.degeweldigemod.handler.CheesePacketHandler;
import com.Egietje.degeweldigemod.init.CheeseItems;
import com.Egietje.degeweldigemod.packet.CheeseBankMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class CheeseBankGui extends GuiScreen {
	private GuiButtonBank COIN_SELL;
	private GuiButtonBank GOLD_SELL;
	private GuiButtonBank COIN_BUY;
	private GuiButtonBank GOLD_BUY;

	@Override
	public void initGui() {
		buttonList.add(new GuiButtonExt(1, this.width / 2 - 100, this.height / 2 - 10, 200, 20,
				"Welcome in the " + TextFormatting.YELLOW + "Cheese Bank" + TextFormatting.RESET));
		COIN_SELL = new GuiButtonBank(2, this.width / 2 - 30, this.height / 2 - 30, 0);
		GOLD_SELL = new GuiButtonBank(3, this.width / 2 + 10, this.height / 2 - 30, 1);
		COIN_BUY = new GuiButtonBank(4, this.width / 2 - 30, this.height / 2 + 10, 2);
		GOLD_BUY = new GuiButtonBank(5, this.width / 2 + 10, this.height / 2 + 10, 3);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
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
			for (int i = 0; i < this.mc.player.inventory.getSizeInventory(); i++) {
				Item item = this.mc.player.inventory.getStackInSlot(i).getItem();
				if (item == CheeseItems.CHEESE_MONEY) {
					containsCheese = true;
				}
			}
			if (containsCheese) {
				CheesePacketHandler.INSTANCE.sendToServer(new CheeseBankMessage(10));
				buttonList.add(new GuiButtonExt(6, this.width / 2 - 100, this.height / 2 - 10, 200, 20,
						"Succesfully added " + 10 + " C-coins"));
			} else {
				buttonList.add(new GuiButtonExt(6, this.width / 2 - 100, this.height / 2 - 10, 200, 20,
						"Not enough cheese"));
			}
			break;
		case 3:
			boolean containsGold = false;
			for (int i = 0; i < this.mc.player.inventory.getSizeInventory(); i++) {
				Item item = this.mc.player.inventory.getStackInSlot(i).getItem();
				if (item == Items.GOLD_INGOT) {
					containsGold = true;
				}
			}
			if (containsGold) {
				CheesePacketHandler.INSTANCE.sendToServer(new CheeseBankMessage(50));
				buttonList.add(new GuiButtonExt(6, this.width / 2 - 100, this.height / 2 - 10, 200, 20,
						"Succesfully added " + 50 + " C-coins"));
			} else {
				buttonList.add(new GuiButtonExt(6, this.width / 2 - 100, this.height / 2 - 10, 200, 20,
						"Not enough gold"));
			}
			break;
		case 4:
			boolean moneyCheese = false;
			IMoney moneyCapCheese = this.mc.player.getCapability(MoneyProvider.MONEY_CAP, null);
			if (moneyCapCheese.get() >= 10) {
				moneyCheese = true;
			} else {
				moneyCheese = false;
			}
			if (moneyCheese) {
				CheesePacketHandler.INSTANCE.sendToServer(new CheeseBankMessage(-10));
				buttonList.add(new GuiButtonExt(6, this.width / 2 - 100, this.height / 2 - 10, 200, 20,
						"Succesfully bought a cheese coin"));
			} else {
				buttonList.add(new GuiButtonExt(6, this.width / 2 - 100, this.height / 2 - 10, 200, 20,
						"Not enough money"));
			}
			break;
		case 5:
			boolean moneyGold = false;
			IMoney moneyCapGold = this.mc.player.getCapability(MoneyProvider.MONEY_CAP, null);
			if (moneyCapGold.get() >= 50) {
				moneyGold = true;
			} else {
				moneyGold = false;
			}
			if (moneyGold) {
				CheesePacketHandler.INSTANCE.sendToServer(new CheeseBankMessage(-50));
				buttonList.add(new GuiButtonExt(6, this.width / 2 - 100, this.height / 2 - 10, 200, 20,
						"Succesfully bought gold"));
			} else {
				buttonList.add(new GuiButtonExt(6, this.width / 2 - 100, this.height / 2 - 10, 200, 20,
						"Not enough money"));
			}
			break;
		case 6:
			this.mc.displayGuiScreen((GuiScreen) null);
			break;
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
