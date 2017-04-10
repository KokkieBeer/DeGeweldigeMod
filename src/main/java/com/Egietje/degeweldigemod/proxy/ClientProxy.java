package com.Egietje.degeweldigemod.proxy;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.Display;

import com.Egietje.degeweldigemod.DeGeweldigeMod;
import com.Egietje.degeweldigemod.Reference;
import com.Egietje.degeweldigemod.entities.cheesearrow.*;
import com.Egietje.degeweldigemod.entities.cheeseball.*;
import com.Egietje.degeweldigemod.entities.cheeseboss.*;
import com.Egietje.degeweldigemod.entities.cheesecow.*;
import com.Egietje.degeweldigemod.entities.tileentities.blocks.*;
import com.Egietje.degeweldigemod.entities.tileentities.render.*;
import com.Egietje.degeweldigemod.handler.CheeseClientHandler;
import com.Egietje.degeweldigemod.handler.CheeseCommonHandler;
import com.Egietje.degeweldigemod.init.CheeseBlocks;
import com.Egietje.degeweldigemod.init.CheeseItems;
import com.Egietje.degeweldigemod.init.CheeseUtils;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

	public void registerModels() {
		int meta = 0;
		
		//Food
		registerItemModel(CheeseItems.CHEESE, meta);
		registerItemModel(CheeseItems.CHEESE_COOKED, meta);
		registerItemModel(CheeseItems.BREAD_CHEESE, meta);
		registerItemModel(CheeseItems.CHEESE_APPLE, meta);
		registerItemModel(CheeseItems.CHEESE_BUCKET, meta);
		registerItemModel(CheeseItems.CHEESE_SEEDS, meta);

		//Other
		registerItemModel(CheeseItems.CHEESE_INGOT, meta);
		registerItemModel(CheeseItems.CHEESE_ARROW, meta);
		registerItemModel(CheeseItems.CHEESE_MONEY, meta);

		//Tools
		registerItemModel(CheeseItems.CHEESE_SWORD, meta);
		registerItemModel(CheeseItems.CHEESE_PICKAXE, meta);
		registerItemModel(CheeseItems.CHEESE_AXE, meta);
		registerItemModel(CheeseItems.CHEESE_SHOVEL, meta);
		registerItemModel(CheeseItems.CHEESE_HOE, meta);
		registerItemModel(CheeseItems.CHEESE_FLY_STICK, meta);
		registerItemModel(CheeseItems.CHEESE_BOW, meta);
		registerItemModel(CheeseItems.CHEESE_MULTITOOL, meta);
		registerItemModel(CheeseItems.FLINT_AND_CHEESE, meta);
		registerItemModel(CheeseItems.CHEESE_SLOW_WAND, meta);
		registerItemModel(CheeseItems.CHEESE_POISON_WAND, meta);
		registerItemModel(CheeseItems.CHEESE_DAMAGE_WAND, meta);
		registerItemModel(CheeseItems.CHEESE_FIRE_WAND, meta);
		registerItemModel(CheeseItems.CHEESE_WAND, meta);

		//Armor
		registerItemModel(CheeseItems.CHEESE_HELMET, meta);
		registerItemModel(CheeseItems.CHEESE_CHESTPLATE, meta);
		registerItemModel(CheeseItems.CHEESE_LEGGINGS, meta);
		registerItemModel(CheeseItems.CHEESE_BOOTS, meta);

		//Tool heads
		registerItemModel(CheeseItems.CHEESE_PICKAXE_HEAD, meta);
		registerItemModel(CheeseItems.CHEESE_AXE_HEAD, meta);
		registerItemModel(CheeseItems.CHEESE_SHOVEL_HEAD, meta);

		registerBlockModel(CheeseBlocks.CHEESE_ORE, meta);
		registerBlockModel(CheeseBlocks.CHEESE_ORE_NETHER, meta);
		registerBlockModel(CheeseBlocks.CHEESE_ORE_END, meta);
		registerBlockModel(CheeseBlocks.CHEESE_BLOCK, meta);
		registerBlockModel(CheeseBlocks.QUICK_CHEESE, meta);
		registerBlockModel(CheeseBlocks.COMPLIMENT_MACHINE, meta);
		registerBlockModel(CheeseBlocks.BELGIUM_FLAG, meta);
		registerBlockModel(CheeseBlocks.CHEESE_PLANT, meta);
		registerBlockModel(CheeseBlocks.CHEESE_FURNACE, meta);
		registerBlockModel(CheeseBlocks.LIT_CHEESE_FURNACE, meta);
		registerBlockModel(CheeseBlocks.CHEESE_CRAFTING_TABLE, meta);
		registerBlockModel(CheeseBlocks.CHEESE_COOKIE_BLOCK, meta);
		registerBlockModel(CheeseBlocks.CHEESE_BOARD, meta);
		registerBlockModel(CheeseBlocks.CHEESE_GRASS, meta);
		registerBlockModel(CheeseBlocks.CHEESE_DIRT, meta);
		registerBlockModel(CheeseBlocks.CHEESE_GRASS_PATH, meta);
		registerBlockModel(CheeseBlocks.CHEESE_FARM_LAND, meta);
		registerBlockModel(CheeseBlocks.CHEESE_STAIRS, meta);
		registerBlockModel(CheeseBlocks.CHEESE_PORTAL, meta);
		registerBlockModel(CheeseBlocks.CHEESE_STONE, meta);
		registerBlockModel(CheeseBlocks.CHEESE_FIRE, meta);
		registerBlockModel(CheeseBlocks.CHEESE_MIRROR, meta);
		registerBlockModel(CheeseBlocks.CHEESE_LOG, meta);
		registerBlockModel(CheeseBlocks.CHEESE_PLANKS, meta);
		registerBlockModel(CheeseBlocks.CHEESE_CHAIR, meta);
		registerBlockModel(CheeseBlocks.CHEESE_BANK, meta);
		registerBlockModel(CheeseBlocks.CHEESE_STORE, meta);
	}

	public void registerClientEventHandler() {
		MinecraftForge.EVENT_BUS.register(new CheeseClientHandler());
		MinecraftForge.EVENT_BUS.register(new RenderCheeseMirror());
	}

	public void bindTileEntities() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCheeseBoard.class, new RenderCheeseBoard());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCheeseMirror.class, new RenderCheeseMirror());
	}

	public void setTitle(String title) {
		Display.setTitle(title);
	}

	public void renderEntities() {
		RenderingRegistry.registerEntityRenderingHandler(EntityCheeseCow.class, new RenderingHandlerCheeseCow());
		RenderingRegistry.registerEntityRenderingHandler(EntityCheeseArrow.class, new RenderingHandlerCheeseArrow());
		RenderingRegistry.registerEntityRenderingHandler(EntityCheeseBoss.class, new RenderingHandlerCheeseBoss());
		RenderingRegistry.registerEntityRenderingHandler(EntityCheeseBall.class, new RenderingHandlerCheeseBall());
	}

	private void registerItemModel(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	private void registerBlockModel(Block block, int meta) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta,
				new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
}
