package com.Egietje.degeweldigemod.init;

import com.Egietje.degeweldigemod.DeGeweldigeMod;
import com.Egietje.degeweldigemod.Reference;
import com.Egietje.degeweldigemod.blocks.*;
import com.Egietje.degeweldigemod.blocks.CheeseFluid.CheeseFluid2;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import scala.math.LowPriorityOrderingImplicits;

public class CheeseBlocks {
	
	public static Block CHEESE_BLOCK;
	public static Block CHEESE_ORE;
	public static Block CHEESE_ORE_NETHER;
	public static Block CHEESE_ORE_END;
	public static Block QUICK_CHEESE;
	public static Block COMPLIMENT_MACHINE;
	public static Block BELGIUM_FLAG;
	public static Block CHEESE_PLANT;
	public static Block CHEESE_FURNACE;
	public static Block LIT_CHEESE_FURNACE;
	public static Block CHEESE_CRAFTING_TABLE;
	public static Block CHEESE_COOKIE_BLOCK;
	public static Block CHEESE_BOARD;
	public static Block CHEESE_GRASS;
	public static Block CHEESE_DIRT;
	public static Block CHEESE_GRASS_PATH;
	public static Block CHEESE_FARM_LAND;
	public static Block CHEESE_STAIRS;
	public static CheesePortal CHEESE_PORTAL;
	public static Block CHEESE_STONE;
	public static CheeseFire CHEESE_FIRE;
	public static Block CHEESE_MIRROR;
	public static Block CHEESE_LOG;
	public static Block CHEESE_PLANKS;
	public static Block CHEESE_CHAIR;
	public static Block CHEESE_BANK;
	
	public CheeseBlocks() {
		init();
		register();
	}
	
	public static void init() {
		CHEESE_ORE = new CheeseOre().setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(2F);
		CHEESE_ORE_NETHER = new CheeseOreNether().setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(2F);
		CHEESE_ORE_END = new CheeseOreEnd().setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(2F);
		CHEESE_BLOCK = new Block(Material.ROCK).setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(3F);
		QUICK_CHEESE = new QuickCheese().setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(4F);
		COMPLIMENT_MACHINE = new ComplimentMachine().setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(3F);
		BELGIUM_FLAG = new BelgiumFlag().setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(2F);
		CHEESE_PLANT = new CheesePlant();
		CHEESE_FURNACE = new CheeseFurnace(false).setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(2F);
		LIT_CHEESE_FURNACE = new CheeseFurnace(true).setHardness(2F);
		CHEESE_CRAFTING_TABLE = new CheeseWorkbench().setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(2F);
		CHEESE_COOKIE_BLOCK = new CheeseCookieBlock().setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(2F);
		CHEESE_BOARD = new CheeseBoard().setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(2F);
		CHEESE_GRASS = new CheeseGrass().setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(0.5F).setResistance(0.5F);
		CHEESE_DIRT = new CheeseDirt().setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(0.75F).setResistance(0.5F);
		CHEESE_GRASS_PATH = new CheeseGrassPath().setHardness(0.625F).setResistance(0.5F);
		CHEESE_FARM_LAND = new CheeseFarmLand().setHardness(0.625F).setResistance(0.5F);
		CHEESE_STAIRS = new CheeseStairs().setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(2.5F);
		CHEESE_PORTAL = (CheesePortal) new CheesePortal().setBlockUnbreakable();
		CHEESE_STONE = new CheeseStone().setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(1.0F);
		CHEESE_FIRE = (CheeseFire) new CheeseFire().setHardness(0.0F).setLightLevel(15.0F);
		CHEESE_MIRROR = new CheeseMirror().setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(1.5F);
		CHEESE_LOG = new CheeseLog().setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(2.0F);
		CHEESE_PLANKS = new CheesePlanks().setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(1.25F);
		CHEESE_CHAIR = new CheeseChair().setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(1.25F);
		CHEESE_BANK = new CheeseBank().setCreativeTab(CheeseTabs.CHEESE_BLOCKS).setHardness(3F);
		
		CheeseUtils.setNames(CHEESE_ORE, "cheese_ore");
		CheeseUtils.setNames(CHEESE_ORE_NETHER, "cheese_ore_nether");
		CheeseUtils.setNames(CHEESE_ORE_END, "cheese_ore_end");
		CheeseUtils.setNames(CHEESE_BLOCK, "cheese_block");
		CheeseUtils.setNames(QUICK_CHEESE, "quick_cheese");
		CheeseUtils.setNames(COMPLIMENT_MACHINE, "compliment_machine");
		CheeseUtils.setNames(BELGIUM_FLAG, "belgium_flag");
		CheeseUtils.setNames(CHEESE_PLANT, "cheese_plant");
		CheeseUtils.setNames(CHEESE_FURNACE, "cheese_furnace");
		CheeseUtils.setNames(LIT_CHEESE_FURNACE, "lit_cheese_furnace");
		CheeseUtils.setNames(CHEESE_CRAFTING_TABLE, "cheese_crafting_table");
		CheeseUtils.setNames(CHEESE_COOKIE_BLOCK, "cheese_cookie_block");
		CheeseUtils.setNames(CHEESE_BOARD, "cheese_board");
		CheeseUtils.setNames(CHEESE_GRASS, "cheese_grass");
		CheeseUtils.setNames(CHEESE_DIRT, "cheese_dirt");
		CheeseUtils.setNames(CHEESE_GRASS_PATH, "cheese_grass_path");
		CheeseUtils.setNames(CHEESE_FARM_LAND, "cheese_farm_land");
		CheeseUtils.setNames(CHEESE_STAIRS, "cheese_stairs");
		CheeseUtils.setNames(CHEESE_PORTAL, "cheese_portal");
		CheeseUtils.setNames(CHEESE_STONE, "cheese_stone");
		CheeseUtils.setNames(CHEESE_FIRE, "cheese_fire");
		CheeseUtils.setNames(CHEESE_MIRROR, "cheese_mirror");
		CheeseUtils.setNames(CHEESE_LOG, "cheese_log");
		CheeseUtils.setNames(CHEESE_PLANKS, "cheese_planks");
		CheeseUtils.setNames(CHEESE_CHAIR, "cheese_chair");
		CheeseUtils.setNames(CHEESE_BANK, "cheese_bank");
	}	
	
	public void register() {
		this.registerBlock(CHEESE_ORE);
		this.registerBlock(CHEESE_ORE_NETHER);
		this.registerBlock(CHEESE_ORE_END);
		this.registerBlock(CHEESE_BLOCK);
		this.registerBlock(QUICK_CHEESE);
		this.registerBlock(COMPLIMENT_MACHINE);
		this.registerBlock(BELGIUM_FLAG);
		this.registerBlock(CHEESE_PLANT);
		this.registerBlock(CHEESE_FURNACE);
		this.registerBlock(LIT_CHEESE_FURNACE);
		this.registerBlock(CHEESE_CRAFTING_TABLE);
		this.registerBlock(CHEESE_COOKIE_BLOCK);
		this.registerBlock(CHEESE_BOARD);
		this.registerBlock(CHEESE_GRASS);
		this.registerBlock(CHEESE_DIRT);
		this.registerBlock(CHEESE_GRASS_PATH);
		this.registerBlock(CHEESE_FARM_LAND);
		this.registerBlock(CHEESE_STAIRS);
		this.registerBlock(CHEESE_PORTAL);
		this.registerBlock(CHEESE_STONE);
		this.registerBlock(CHEESE_FIRE);
		this.registerBlock(CHEESE_MIRROR);
		FluidRegistry.registerFluid(CheeseFluid2.INSTANCE);
		FluidRegistry.addBucketForFluid(CheeseFluid2.INSTANCE);
		this.registerBlock(CheeseFluid.INSTANCE);
		this.registerBlock(CHEESE_LOG);
		this.registerBlock(CHEESE_PLANKS);
		this.registerBlock(CHEESE_CHAIR);
		this.registerBlock(CHEESE_BANK);
	}	
	
	private void registerBlock(Block block) {
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block).setUnlocalizedName(block.getUnlocalizedName()).setRegistryName(block.getRegistryName()));
	}
}
