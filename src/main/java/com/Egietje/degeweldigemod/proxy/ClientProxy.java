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
import com.Egietje.degeweldigemod.blocks.CheeseFluid;
import com.Egietje.degeweldigemod.entities.cheesearrow.*;
import com.Egietje.degeweldigemod.entities.cheeseball.EntityCheeseBall;
import com.Egietje.degeweldigemod.entities.cheeseball.RenderingHandlerCheeseBall;
import com.Egietje.degeweldigemod.entities.cheeseboss.EntityCheeseBoss;
import com.Egietje.degeweldigemod.entities.cheeseboss.RenderingHandlerCheeseBoss;
import com.Egietje.degeweldigemod.entities.cheesecow.*;
import com.Egietje.degeweldigemod.entities.tileentities.blocks.*;
import com.Egietje.degeweldigemod.entities.tileentities.render.*;
import com.Egietje.degeweldigemod.handler.CheeseClientHandler;
import com.Egietje.degeweldigemod.handler.CheeseCommonHandler;
import com.Egietje.degeweldigemod.init.CheeseBlocks;
import com.Egietje.degeweldigemod.init.CheeseItems;
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
		//Food
		registerItemModel(CheeseItems.CHEESE, 0);
		registerItemModel(CheeseItems.CHEESE_COOKED, 0);
		registerItemModel(CheeseItems.BREAD_CHEESE, 0);
		registerItemModel(CheeseItems.CHEESE_APPLE, 0);
		registerItemModel(CheeseItems.CHEESE_BUCKET, 0);
		registerItemModel(CheeseItems.CHEESE_SEEDS, 0);

		//Other
		registerItemModel(CheeseItems.CHEESE_INGOT, 0);
		registerItemModel(CheeseItems.CHEESE_ARROW, 0);

		//Tools
		registerItemModel(CheeseItems.CHEESE_SWORD, 0);
		registerItemModel(CheeseItems.CHEESE_PICKAXE, 0);
		registerItemModel(CheeseItems.CHEESE_AXE, 0);
		registerItemModel(CheeseItems.CHEESE_SHOVEL, 0);
		registerItemModel(CheeseItems.CHEESE_HOE, 0);
		registerItemModel(CheeseItems.CHEESE_FLY_STICK, 0);
		registerItemModel(CheeseItems.CHEESE_BOW, 0);
		registerItemModel(CheeseItems.CHEESE_MULTITOOL, 0);
		registerItemModel(CheeseItems.FLINT_AND_CHEESE, 0);
		registerItemModel(CheeseItems.CHEESE_SLOW_WAND, 0);
		registerItemModel(CheeseItems.CHEESE_POISON_WAND, 0);
		registerItemModel(CheeseItems.CHEESE_DAMAGE_WAND, 0);
		registerItemModel(CheeseItems.CHEESE_FIRE_WAND, 0);

		//Armor
		registerItemModel(CheeseItems.CHEESE_HELMET, 0);
		registerItemModel(CheeseItems.CHEESE_CHESTPLATE, 0);
		registerItemModel(CheeseItems.CHEESE_LEGGINGS, 0);
		registerItemModel(CheeseItems.CHEESE_BOOTS, 0);

		//Tool heads
		registerItemModel(CheeseItems.CHEESE_PICKAXE_HEAD, 0);
		registerItemModel(CheeseItems.CHEESE_AXE_HEAD, 0);
		registerItemModel(CheeseItems.CHEESE_SHOVEL_HEAD, 0);

		registerBlockModel(CheeseBlocks.CHEESE_ORE, 0);
		registerBlockModel(CheeseBlocks.CHEESE_ORE_NETHER, 0);
		registerBlockModel(CheeseBlocks.CHEESE_ORE_END, 0);
		registerBlockModel(CheeseBlocks.CHEESE_BLOCK, 0);
		registerBlockModel(CheeseBlocks.QUICK_CHEESE, 0);
		registerBlockModel(CheeseBlocks.COMPLIMENT_MACHINE, 0);
		registerBlockModel(CheeseBlocks.BELGIUM_FLAG, 0);
		registerBlockModel(CheeseBlocks.CHEESE_PLANT, 0);
		registerBlockModel(CheeseBlocks.CHEESE_FURNACE, 0);
		registerBlockModel(CheeseBlocks.LIT_CHEESE_FURNACE, 0);
		registerBlockModel(CheeseBlocks.CHEESE_CRAFTING_TABLE, 0);
		registerBlockModel(CheeseBlocks.CHEESE_COOKIE_BLOCK, 0);
		registerBlockModel(CheeseBlocks.CHEESE_BOARD, 0);
		registerBlockModel(CheeseBlocks.CHEESE_GRASS, 0);
		registerBlockModel(CheeseBlocks.CHEESE_DIRT, 0);
		registerBlockModel(CheeseBlocks.CHEESE_GRASS_PATH, 0);
		registerBlockModel(CheeseBlocks.CHEESE_FARM_LAND, 0);
		registerBlockModel(CheeseBlocks.CHEESE_STAIRS, 0);
		registerBlockModel(CheeseBlocks.CHEESE_PORTAL, 0);
		registerBlockModel(CheeseBlocks.CHEESE_STONE, 0);
		registerBlockModel(CheeseBlocks.CHEESE_FIRE, 0);
		registerBlockModel(CheeseBlocks.CHEESE_MIRROR, 0);

		//Fluids
		final Item MOLTEN_CHEESE = Item.getItemFromBlock(CheeseFluid.INSTANCE);
		ModelBakery.registerItemVariants(MOLTEN_CHEESE);
		ModelLoader.setCustomMeshDefinition(MOLTEN_CHEESE, new ItemMeshDefinition() {
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return new ModelResourceLocation(MOLTEN_CHEESE.getRegistryName(), "inventory");
			}
		});
		ModelLoader.setCustomStateMapper(CheeseFluid.INSTANCE, new StateMapperBase() {
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(CheeseFluid.INSTANCE.getRegistryName(), "molten_cheese");
			}
		});
	}

	public void registerClientEventHandler() {
		MinecraftForge.EVENT_BUS.register(new CheeseClientHandler());
		MinecraftForge.EVENT_BUS.register(new RenderCheeseMirror());
	}

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityCheeseBoard.class, Reference.MODID + "TileEntityCheeseBoard");
		GameRegistry.registerTileEntity(TileEntityCheeseMirror.class, Reference.MODID + "TileEntityMirror");
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
