package com.Egietje.degeweldigemod.handler;

import java.lang.reflect.Field;
import java.util.List;

import com.Egietje.degeweldigemod.capability.shouldgiveitems.ShouldGiveItems;
import com.Egietje.degeweldigemod.entities.tileentities.render.RenderCheeseMirror;
import com.Egietje.degeweldigemod.gui.GuiCheeseOverlay;
import com.Egietje.degeweldigemod.init.CheeseAchievements;
import com.Egietje.degeweldigemod.init.CheeseItems;
import com.Egietje.degeweldigemod.init.CheeseUtils;
import com.Egietje.degeweldigemod.render.LayerCheeseCape;
import com.Egietje.degeweldigemod.render.LayerCheeseEars;
import com.google.common.base.Throwables;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CheeseClientHandler {
	@SubscribeEvent
	public void onPlayerJoin(PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		player.sendMessage(new TextComponentString(TextFormatting.GOLD + "Welcome " + TextFormatting.YELLOW
				+ player.getDisplayNameString() + TextFormatting.GOLD + ", have fun!" + TextFormatting.RESET));
		player.addStat(CheeseAchievements.JOIN);
	}

	private boolean air = false;
	
	@SubscribeEvent
	public void onPostRenderOverlay(RenderGameOverlayEvent.Post event) {
		if (event.getType() == ElementType.AIR) {
			
		}
		if (event.getType() == ElementType.FOOD) {
			(new GuiCheeseOverlay(Minecraft.getMinecraft())).renderCheese(event.getResolution().getScaledWidth(),
					event.getResolution().getScaledHeight());
		}
	}

	private static final Field inMoreWorldOptionsDisplay = ReflectionHelper.findField(GuiCreateWorld.class,
			"field_146344_y", "inMoreWorldOptionsDisplay");
	private static final Field gameMode = ReflectionHelper.findField(GuiCreateWorld.class, "field_146342_r",
			"gameMode");

	@SubscribeEvent
	public void onGuiPostInit(GuiScreenEvent.InitGuiEvent.Post event) {
		GuiScreen gui = event.getGui();
		if (gui instanceof GuiCreateWorld) {
			try {
				List<GuiButton> buttonList = event.getButtonList();
				GuiButton giveItem;
				giveItem = addButton(new GuiButton(11, gui.width / 2 + 5, 187, 150, 20, "Give Items: OFF"), buttonList);
				giveItem.visible = inMoreWorldOptionsDisplay.getBoolean(gui);
				ShouldGiveItems.worldCreateGive = false;
				for (int i = 0; i < buttonList.size(); i++) {
					GuiButton button = event.getButtonList().get(i);
					if (button.id == 3) {
						if (inMoreWorldOptionsDisplay.getBoolean(gui)) {
							button.xPosition = gui.width / 2 - 155;
						} else {
							button.xPosition = gui.width / 2 - 75;
						}
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	protected <T extends GuiButton> T addButton(T button, List<GuiButton> list) {
		list.add(button);
		return button;
	}

	@SubscribeEvent
	public void onGuiPreInteraction(GuiScreenEvent.ActionPerformedEvent.Pre event) {
		GuiScreen gu = event.getGui();
		if (gu instanceof GuiCreateWorld) {
			GuiCreateWorld gui = (GuiCreateWorld) gu;
			GuiButton button = event.getButton();
			if (button.id == 2) {
				for (int i = 0; i < event.getButtonList().size(); i++) {
					GuiButton button1 = event.getButtonList().get(i);
					if (button1.id == 11) {
						try {
							if ("survival".equals(gameMode.get(gui))) {
								ShouldGiveItems.worldCreateGive = false;
								button1.displayString = "Give Items: OFF";
								button1.enabled = false;
							} else if ("hardcore".equals(gameMode.get(gui))) {
								ShouldGiveItems.worldCreateGive = false;
								button1.displayString = "Give Items: OFF";
								button1.enabled = true;
							} else {
								ShouldGiveItems.worldCreateGive = false;
								button1.displayString = "Give Items: OFF";
								button1.enabled = true;
							}
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			} else if (button.id == 3) {
				for (int i = 0; i < event.getButtonList().size(); i++) {
					GuiButton button1 = event.getButtonList().get(i);
					try {
						if (button1.id == 11) {
							button1.visible = !inMoreWorldOptionsDisplay.getBoolean(gui);
						} else if (button1.id == 3) {
							if (!inMoreWorldOptionsDisplay.getBoolean(gui)) {
								button1.xPosition = gui.width / 2 - 155;
							} else {
								button1.xPosition = gui.width / 2 - 75;
							}
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			} else if (button.id == 11) {
				if (!ShouldGiveItems.worldCreateGive) {
					button.displayString = "Give Items: ON";
					ShouldGiveItems.worldCreateGive = true;
				} else {
					button.displayString = "Give Items: OFF";
					ShouldGiveItems.worldCreateGive = false;
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerPreRender(RenderPlayerEvent.Pre event) {
		RenderPlayer render = event.getRenderer();
		render.addLayer(new LayerCheeseEars(render));
		render.addLayer(new LayerCheeseCape(render));
	}

	@SubscribeEvent
	public void onFOVUpdate(FOVUpdateEvent event) {
		EntityPlayer player = event.getEntity();
		if (player.isHandActive() && player.getActiveItemStack() != null
				&& player.getActiveItemStack().getItem() == CheeseItems.CHEESE_BOW) {
			int i = player.getItemInUseMaxCount();
			float f1 = (float) i / 10.0F;
			if (f1 > 1.0F) {
				f1 = 1.0F;
			} else {
				f1 = f1 * f1;
			}
			event.setNewfov(event.getFov() * 1.0F - f1 * 0.15F);
		}
	}

	@SubscribeEvent
	public void onLoadWorld(WorldEvent.Load event) {
		if (event.getWorld() instanceof WorldClient) {
			RenderCheeseMirror.mirrorGlobalRenderer.setWorldAndLoadRenderers((WorldClient) event.getWorld());
		}
	}
}
